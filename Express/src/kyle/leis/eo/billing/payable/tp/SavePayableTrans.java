package kyle.leis.eo.billing.payable.tp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.payable.da.PayableColumns;
import kyle.leis.eo.billing.payable.dax.PayableDemand;
import kyle.leis.eo.billing.receivable.dax.IReceivableBasicData;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;

public class SavePayableTrans extends AbstractTransaction {
	private List m_listPyColumns;
	private List<PayableColumns> m_listOriginPyColumns;
	private String m_strCwcode;
	private String m_strOperId;
	private boolean m_isDelOriginSystemFee;
	
	public void setParam(List listPyColumns, 
			String strCwcode, 
			String strOperId,
			boolean isDelOriginSystemFee,
			List listOriginPyColumns) throws Exception {
		m_listPyColumns = listPyColumns;
		m_strCwcode = strCwcode;
		m_strOperId = strOperId;
		m_isDelOriginSystemFee = isDelOriginSystemFee;
		// 原始费用
		if (listOriginPyColumns != null && listOriginPyColumns.size() > 0) {
			m_listOriginPyColumns = new ArrayList<PayableColumns>();
			for (int i = 0; i < listOriginPyColumns.size(); i++) {
				m_listOriginPyColumns.add((PayableColumns)listOriginPyColumns.get(i));
			}
		}
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_listPyColumns == null || m_listPyColumns.size() < 1) return;		
		// 删除确定状态且系统计费的费用，且改变原费用的List以便后面检查状态
		// 万臣的创建日期比较特殊不需要改变原来的费用list
		List<PayableColumns> listRetainedOriginPy = null;		
		boolean isUseRetainedOrigin = false;
		if (m_isDelOriginSystemFee) {
			DeleltePayableTrans objDeletePyTrans = new DeleltePayableTrans();
			String strSystemPE = SystempropertyDemand.getEnterprise();
			if (!StringUtility.isNull(strSystemPE) && strSystemPE.startsWith("WC")) {
				listRetainedOriginPy = objDeletePyTrans.setAutoCalcParam(m_listOriginPyColumns);
				isUseRetainedOrigin = true;
			} else {
				m_listOriginPyColumns = objDeletePyTrans.setAutoCalcParam(m_listOriginPyColumns);
			}
			objDeletePyTrans.transaction(objSession);
		}
		for (int i = 0; i < m_listPyColumns.size(); i++) {
			PayableColumns objPayableColumns = (PayableColumns)m_listPyColumns.get(i);
			String strCwcode = objPayableColumns.getCwcwcode();
			if (!strCwcode.equals(m_strCwcode)) continue;
			// 查询在原始费用中的状态
			PayableColumns objOriginPyColumns = null;
			// 万臣
			if (isUseRetainedOrigin) {
				objOriginPyColumns = PayableDemand.getOriginPayable(objPayableColumns, 
						listRetainedOriginPy);
			}
			// 非万臣
			else {
				objOriginPyColumns = PayableDemand.getOriginPayable(objPayableColumns, 
						m_listOriginPyColumns);
			}			
			String strFscode = "";
			if (objOriginPyColumns != null)
				strFscode = objOriginPyColumns.getFsfscode();
			// 原始费用如果不是系统，新费用为自动计费则不修改费用
			if (objOriginPyColumns != null && 
					!objOriginPyColumns.getMopopid().equals(IReceivableBasicData.OPERID_SYSTEM) &&
					objPayableColumns.getMopopid().equals(IReceivableBasicData.OPERID_SYSTEM))
				continue;
			// 新增
			if (StringUtility.isNull(strFscode)) {
				AddPayableTrans objAddPayableTrans = new AddPayableTrans();
				if (isUseRetainedOrigin) {
					PayableColumns objActualOriginPyColumns = PayableDemand.getOriginPayable(objPayableColumns, 
							m_listOriginPyColumns);
					if (objActualOriginPyColumns != null)
						objAddPayableTrans.setParam(objPayableColumns, m_strOperId, false);
					else 
						objAddPayableTrans.setParam(objPayableColumns, m_strOperId, true);				
				} else {
					objAddPayableTrans.setParam(objPayableColumns, m_strOperId, true);
				}
				objAddPayableTrans.transaction(objSession);
				continue;
			}
			// 判断费用是否一致
			BigDecimal objOriginCurrencyrate = new BigDecimal(objOriginPyColumns.getPypycurrencyrate());
			BigDecimal objCurrencyrate = new BigDecimal(objPayableColumns.getPypycurrencyrate());
			
			BigDecimal objOriginPyactualtotal = new BigDecimal(objOriginPyColumns.getPypyactualtotal());
			BigDecimal objPyactualtotal = new BigDecimal(objPayableColumns.getPypyactualtotal());
			objPyactualtotal = objPyactualtotal.divide(new BigDecimal("1"), 2, 4);
			objOriginPyactualtotal = objOriginPyactualtotal.divide(new BigDecimal("1"), 2, 4);
			
			if (objOriginCurrencyrate.compareTo(objCurrencyrate) == 0 &&
					objOriginPyactualtotal.compareTo(objPyactualtotal) == 0 &&
					objOriginPyColumns.getCococode().equals(objPayableColumns.getCococode()) &&
					objOriginPyColumns.getPypyoccurdate().equals(objPayableColumns.getPypyoccurdate()))
				continue;
			// 预报或新建则直接修改
			if (strFscode.equals(IReceivableBasicData.FEE_STATUS_PRELERT) ||
					strFscode.equals(IReceivableBasicData.FEE_STATUS_DRAFT)) {
				ModifyDraftPayableTrans objModifyDPyTrans = new ModifyDraftPayableTrans();
				objModifyDPyTrans.setParam(objPayableColumns, 
						objOriginPyColumns.getPypyid(), 
						m_strOperId);
				objModifyDPyTrans.transaction(objSession);
			}
			// 确定
			else if (strFscode.equals(IReceivableBasicData.FEE_STATUS_CONFIRM)) {
				ModifyConfirmPayableTrans objModifyCPyTrans = new ModifyConfirmPayableTrans();
				objModifyCPyTrans.setParam(objPayableColumns, 
						objOriginPyColumns, 
						m_strOperId);
				objModifyCPyTrans.transaction(objSession);
			}
			// 出账、核销
			else if (strFscode.equals(IReceivableBasicData.FEE_STATUS_BILL) ||
					 strFscode.equals(IReceivableBasicData.FEE_STATUS_WRITEOFF)) {
				// 出账或核销的费用不再看费用日期
				if (objOriginCurrencyrate.compareTo(objCurrencyrate) == 0 &&
						objOriginPyactualtotal.compareTo(objPyactualtotal) == 0 &&
						objOriginPyColumns.getCococode().equals(objPayableColumns.getCococode()))
					continue;				
				ModifyBillPayableTrans objModifyBPyTrans = new ModifyBillPayableTrans();
				objModifyBPyTrans.setParam(objPayableColumns, 
						objOriginPyColumns.getPypyid(), 
						m_strOperId);
				objModifyBPyTrans.transaction(objSession);
			}
		}
	}
}
