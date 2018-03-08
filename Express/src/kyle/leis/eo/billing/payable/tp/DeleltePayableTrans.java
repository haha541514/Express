package kyle.leis.eo.billing.payable.tp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.calculate.feecalculate.dax.IFeeCalculateBasicData;
import kyle.leis.eo.billing.payable.da.PayableColumns;
import kyle.leis.eo.billing.payable.dax.PayableDemand;
import kyle.leis.eo.billing.receivable.dax.IReceivableBasicData;
import kyle.leis.hi.TblPayable;
import kyle.leis.hi.ThiPayable;

public class DeleltePayableTrans extends AbstractTransaction {
	private List<PayableColumns> m_listPayable;
	private String m_strOperId;
	private String m_strCwcode;
	
	public String getOriginDeleteCwcode() {
		return m_strCwcode;
	}
	
	/**
	 * 删除系统计算的费用
	 * @param strCwcode
	 * @param strOperId
	 * @throws Exception
	 */
	public void setParam(String strCwcode, 
			String strOperId) throws Exception {
		m_strOperId = strOperId;
		// 查询应收费用
		List listPayable = PayableDemand.load(strCwcode, "A0201");
		if (listPayable == null || listPayable.size() < 1)
			return;
		m_listPayable = new ArrayList<PayableColumns>();
		// 删除系统记录的应付费用
		for (int i = 0; i < listPayable.size(); i++) {
			PayableColumns objPayableColumns = (PayableColumns)listPayable.get(i);
			String strModifyOperId = objPayableColumns.getMopopid();
			if (strModifyOperId.equals(IReceivableBasicData.OPERID_SYSTEM))
				m_listPayable.add(objPayableColumns);
		}
	}
	
	/**
	 * 删除所有费用
	 * @param strCwcode
	 * @param strOperId
	 * @throws Exception
	 */
	public void setDelAllParam(String strCwcode,
			String strOperId) throws Exception {
		m_strOperId = strOperId;
		// 查询应收费用
		List listPayable = PayableDemand.load(strCwcode, "A0201");
		if (listPayable == null || listPayable.size() < 1)
			return;
		m_listPayable = new ArrayList<PayableColumns>();
		// 删除系统记录的应付费用
		for (int i = 0; i < listPayable.size(); i++) {
			PayableColumns objPayableColumns = (PayableColumns)listPayable.get(i);
			m_listPayable.add(objPayableColumns);
		}
	}
	
	/**
	 * 删除系统自动计算的杂费
	 * @param listOriginPyColumns
	 */
	public List<PayableColumns> setAutoCalcParam(List listOriginPyColumns) {
		if (listOriginPyColumns == null || listOriginPyColumns.size() < 1)
			return null;
		m_listPayable = new ArrayList<PayableColumns>();
		List<PayableColumns> listRetainedOriginPy= new ArrayList<PayableColumns>();
		
		for (int i = 0; i < listOriginPyColumns.size(); i++) {
			PayableColumns objPayableColumns = (PayableColumns)listOriginPyColumns.get(i);
			String strModifyOperId = objPayableColumns.getMopopid();
			String strFscode = objPayableColumns.getFsfscode();
			String strFkcode = objPayableColumns.getFkfkcode();
			String strSpyId = objPayableColumns.getSpyspyid();
			// 删除确定状态、系统记录且未对应服务商账单费用的杂费
			if (strFscode.equals(IReceivableBasicData.FEE_STATUS_CONFIRM) &&
					strModifyOperId.equals(IReceivableBasicData.OPERID_SYSTEM) &&
					(strFkcode.startsWith(IFeeCalculateBasicData.FEEKIND_INCIDENTAL) || strFkcode.equals(IFeeCalculateBasicData.FEEKIND_SURCHARGE_OIL)) &&
					StringUtility.isNull(strSpyId))
				m_listPayable.add(objPayableColumns);
			else
				listRetainedOriginPy.add(objPayableColumns);
		}
		m_strOperId = IReceivableBasicData.OPERID_SYSTEM;
		return listRetainedOriginPy;
	}
	
	/**
	 * 删除某项费用
	 * @param objDeletePayColumns
	 * @param strOperId
	 * @throws Exception
	 */
	public void setParam(PayableColumns objDeletePayColumns, 
			String strOperId) throws Exception {
		m_listPayable = new ArrayList<PayableColumns>();
		m_listPayable.add(objDeletePayColumns);
		m_strOperId = strOperId;
	}
	
	/**
	 * 删除费用集合
	 * @param astrPyid
	 * @param strOperId
	 * @throws Exception
	 */
	public void setParam(String[] astrPyid, 
			String strOperId) throws Exception {
		if (astrPyid == null || astrPyid.length < 1)
			return;
		m_strOperId = strOperId;
		m_listPayable = new ArrayList<PayableColumns>();
		for (int i = 0; i < astrPyid.length; i++) {
			PayableColumns objPayableColumns = PayableDemand.loadByPyid(astrPyid[i]);
			m_strCwcode = objPayableColumns.getCwcwcode();
			m_listPayable.add(objPayableColumns);
		}
	}
	
	public List<PayableColumns> getOriginPayable() {
		return m_listPayable;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_listPayable == null || m_listPayable.size() < 1)
			return;
		for (int i = 0; i < m_listPayable.size(); i++) {
			PayableColumns objPayableColumns = (PayableColumns)m_listPayable.get(i);
			if (objPayableColumns == null) continue;
			
			String strFscode = objPayableColumns.getFsfscode();
			String strPyid = objPayableColumns.getPypyid();
			// 不删除红冲蓝补的费用
			if (!StringUtility.isNull(objPayableColumns.getPypyidreference()))
				continue;
			// 草稿、预报、确定费用记录至历史表后删除原费用
			if (strFscode.equals(IReceivableBasicData.FEE_STATUS_PRELERT) ||
					strFscode.equals(IReceivableBasicData.FEE_STATUS_DRAFT) ||
					strFscode.equals(IReceivableBasicData.FEE_STATUS_CONFIRM) || 
					strFscode.equals(IReceivableBasicData.FEE_STATUS_ELIMINATE)) {
				// 先将原始数据移至历史数据库
				TblPayable objOriginTblPayable = (TblPayable)objSession.load(TblPayable.class, 
						Long.parseLong(strPyid));
				ThiPayable objThiPayable = PayableDemand.createHiPayable(objOriginTblPayable, 
						m_strOperId, 
						objSession);
				objSession.save(objThiPayable);
				// 删除之
				objSession.delete(" from TblPayable as py where py.pyId = " + strPyid);
			}
			// 出账或核销
			else if (strFscode.equals(IReceivableBasicData.FEE_STATUS_BILL) ||
					 strFscode.equals(IReceivableBasicData.FEE_STATUS_WRITEOFF)) {
				// 先新增反向费用
				PayableColumns objFlushPayableColumns = PayableDemand.loadByPyid(strPyid);
				objFlushPayableColumns.setPypyidreference(Long.parseLong(strPyid));
				objFlushPayableColumns.setPypyactualtotal(new BigDecimal("-1").multiply(new BigDecimal(objFlushPayableColumns.getPypyactualtotal())));
				objFlushPayableColumns.setPypytotal(new BigDecimal("-1").multiply(new BigDecimal(objFlushPayableColumns.getPypytotal())));
				objFlushPayableColumns.setFsfscode("C");
				
				AddPayableTrans objAddPayableTrans = new AddPayableTrans();
				objAddPayableTrans.setParam(objFlushPayableColumns, "0", true);
				objAddPayableTrans.transaction(objSession);
				Long lNewPyid = objAddPayableTrans.getNewPyid();
				// 修改原费用的指向
				TblPayable objOriginTblPayable = (TblPayable)objSession.load(TblPayable.class, 
						Long.parseLong(strPyid));
				objOriginTblPayable.setPyIdReference(lNewPyid);
				objSession.update(objOriginTblPayable);				
			}
		}
	}
}
