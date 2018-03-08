package kyle.leis.eo.billing.receivable.tp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.receivable.da.ReceivableColumns;
import kyle.leis.eo.billing.receivable.dax.IReceivableBasicData;
import kyle.leis.eo.billing.receivable.dax.ReceivableDemand;
import kyle.leis.es.company.customer.dax.CustomerDemand;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;

public class SaveReceivableTrans extends AbstractTransaction {
	private List m_listRvColumns;
	private List<ReceivableColumns> m_listOriginRvColumns;
	private String m_strCwcode;
	private String m_strOperId;
	private boolean m_isDelOriginSystemFee;
	
	public void setParam(List listRvColumns, 
			String strCwcode, 
			String strOperId,
			boolean isDelOriginSystemFee,
			List listOriginRvColumns) throws Exception {
		m_listRvColumns = listRvColumns;
		m_strCwcode = strCwcode;
		m_strOperId = strOperId;
		m_isDelOriginSystemFee = isDelOriginSystemFee;
		// 原始费用
		if (listOriginRvColumns != null && listOriginRvColumns.size() > 0) {
			m_listOriginRvColumns = new ArrayList<ReceivableColumns>();
			for (int i = 0; i < listOriginRvColumns.size(); i++) {
				m_listOriginRvColumns.add((ReceivableColumns)listOriginRvColumns.get(i));
			}
		}
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_listRvColumns == null || m_listRvColumns.size() < 1) return;
		// 删除确定状态且系统计费的费用，且改变原费用的List以便后面检查状态
		// 万臣的创建日期比较特殊不需要改变原来的费用list
		List<ReceivableColumns> listRetainedOriginRv = null;
		boolean isUseRetainedOrigin = false;
		String strSystemPE = SystempropertyDemand.getEnterprise();
		if (m_isDelOriginSystemFee) {
			DelelteReceivableTrans objDeleteRvTrans = new DelelteReceivableTrans();
			if (!StringUtility.isNull(strSystemPE) && strSystemPE.startsWith("WC")) {
				listRetainedOriginRv = objDeleteRvTrans.setAutoCalcParam(m_listOriginRvColumns);
				isUseRetainedOrigin = true;
			} else {
				m_listOriginRvColumns = objDeleteRvTrans.setAutoCalcParam(m_listOriginRvColumns);
			}
			objDeleteRvTrans.transaction(objSession);
		}
		for (int i = 0; i < m_listRvColumns.size(); i++) {
			ReceivableColumns objRvColumns = (ReceivableColumns)m_listRvColumns.get(i);
			if (!StringUtility.isNull(strSystemPE) && strSystemPE.startsWith("SLYIM")) {
				String strCocode = objRvColumns.getCococode();
				objRvColumns.setCococode(CustomerDemand.getTopParentCustomer(strCocode));
			}
			String strCwcode = objRvColumns.getCwcwcode();
			if (!strCwcode.equals(m_strCwcode)) continue;
			// 查询在原始费用中的状态
			ReceivableColumns objOriginRvColumns = null;
			// 万臣
			if (isUseRetainedOrigin) {
				objOriginRvColumns = ReceivableDemand.getOriginReceivable(objRvColumns, 
						listRetainedOriginRv);
			}
			// 非万臣
			else {
				objOriginRvColumns = ReceivableDemand.getOriginReceivable(objRvColumns, 
						m_listOriginRvColumns);
			}
			String strFscode = "";
			if (objOriginRvColumns != null)
				strFscode = objOriginRvColumns.getFsfscode();
			// 原始费用如果不是系统，新费用为自动计费则不修改费用
			if (objOriginRvColumns != null &&
					!objOriginRvColumns.getMopopid().equals(IReceivableBasicData.OPERID_SYSTEM) &&
					objRvColumns.getMopopid().equals(IReceivableBasicData.OPERID_SYSTEM))
				continue;
			/*
			if ((strFscode.equals(IReceivableBasicData.FEE_STATUS_BILL) ||
					 strFscode.equals(IReceivableBasicData.FEE_STATUS_WRITEOFF)) &&
					 objRvColumns.getMopopid().equals(IReceivableBasicData.OPERID_SYSTEM))
				continue;
			*/
			// 新增
			if (StringUtility.isNull(strFscode)) {
				AddReceivableTrans objAddRvTrans = new AddReceivableTrans();
				if (isUseRetainedOrigin) {
					ReceivableColumns objActualOriginRvColumns = ReceivableDemand.getOriginReceivable(objRvColumns, 
							m_listOriginRvColumns);
					if (objActualOriginRvColumns != null)
						objAddRvTrans.setParam(objRvColumns, m_strOperId, false);
					else 
						objAddRvTrans.setParam(objRvColumns, m_strOperId, true);
				} else {
					objAddRvTrans.setParam(objRvColumns, m_strOperId, true);
				}
				objAddRvTrans.transaction(objSession);
				continue;
			}
			// 判断费用是否一致
			BigDecimal objOriginCurrencyrate = new BigDecimal(objOriginRvColumns.getRvrvcurrencyrate());
			BigDecimal objCurrencyrate = new BigDecimal(objRvColumns.getRvrvcurrencyrate());
			
			BigDecimal objOriginRvactualtotal = new BigDecimal(objOriginRvColumns.getRvrvactualtotal());
			BigDecimal objRvactualtotal = new BigDecimal(objRvColumns.getRvrvactualtotal());
			objRvactualtotal = objRvactualtotal.divide(new BigDecimal("1"), 1, 2);
			objOriginRvactualtotal = objOriginRvactualtotal.divide(new BigDecimal("1"), 1, 2);
			
			if (objOriginCurrencyrate.compareTo(objCurrencyrate) == 0 &&
					objOriginRvactualtotal.compareTo(objRvactualtotal) == 0 &&
					objOriginRvColumns.getCococode().equals(objRvColumns.getCococode()) &&
					objOriginRvColumns.getRvrvoccurdate().equals(objRvColumns.getRvrvoccurdate()))
				continue;	
			// 预报或新建则直接修改
			if (strFscode.equals(IReceivableBasicData.FEE_STATUS_PRELERT) ||
					strFscode.equals(IReceivableBasicData.FEE_STATUS_DRAFT)) {
				ModifyDraftReceivableTrans objModifyDRvTrans = new ModifyDraftReceivableTrans();
				objModifyDRvTrans.setParam(objRvColumns, 
						objOriginRvColumns.getRvrvid(), 
						m_strOperId);
				objModifyDRvTrans.transaction(objSession);
			}
			// 确定
			else if (strFscode.equals(IReceivableBasicData.FEE_STATUS_CONFIRM)) {
				ModifyConfirmReceivableTrans objModifyCRvTrans = new ModifyConfirmReceivableTrans();
				objModifyCRvTrans.setParam(objRvColumns, 
						objOriginRvColumns, 
						m_strOperId);
				objModifyCRvTrans.transaction(objSession);
			}
			// 出账、核销
			else if (strFscode.equals(IReceivableBasicData.FEE_STATUS_BILL) ||
					 strFscode.equals(IReceivableBasicData.FEE_STATUS_WRITEOFF)) {
				// 出账的费用不再看费用日期
				if (objOriginCurrencyrate.compareTo(objCurrencyrate) == 0 &&
						objOriginRvactualtotal.compareTo(objRvactualtotal) == 0 &&
						objOriginRvColumns.getCococode().equals(objRvColumns.getCococode()))
					continue;				
				ModifyBillReceivableTrans objModifyBRvTrans = new ModifyBillReceivableTrans();
				objModifyBRvTrans.setParam(objRvColumns, 
						objOriginRvColumns.getRvrvid(), 
						m_strOperId);
				objModifyBRvTrans.transaction(objSession);
			}
		}
	}
}
