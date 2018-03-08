package kyle.leis.eo.finance.cashrecord.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.eo.finance.billrecord.tp.SaveBillRecordTrans;
import kyle.leis.eo.finance.cashrecord.da.CashrecordColumns;
import kyle.leis.eo.finance.writeoff.tp.AddWriteOffTrans;
import kyle.leis.hi.TfiBillrecord;
import kyle.leis.hi.TfiCashrecord;

public class SaveCashByHWBTrans extends AbstractTransaction {
	private String[] m_astrCwCode;
	private Long m_lCrid;
	private SaveCashRecordTrans m_objSaveCashRecordTrans;
	private SaveBillRecordTrans m_objSaveBillRecordTrans;
	private String m_strOperId;
	
	public void setParam(CashrecordColumns objCashrecordColumns, 
			String strOperId, 
			String[] astrCwCode) throws Exception {
		m_astrCwCode = astrCwCode;
		// 收款
		m_objSaveCashRecordTrans = new SaveCashRecordTrans();
		m_objSaveCashRecordTrans.setParam(objCashrecordColumns, strOperId);
		// 账单
		m_objSaveBillRecordTrans = new SaveBillRecordTrans();
		m_objSaveBillRecordTrans.setDCCashParam(objCashrecordColumns, 
				strOperId, 
				astrCwCode);
		m_strOperId = strOperId;
	}
	
	public Long getNewCrid() {
		return m_lCrid;
	}
	
	public TfiCashrecord getSavedCashrecord() {
		return m_objSaveCashRecordTrans.getSavedCashrecord();
	}
	
	public void transaction(Session objSession) throws Exception {
		// 对应的费用生成账单、核销
		if (m_astrCwCode == null || m_astrCwCode.length < 1) return;
		if (m_objSaveCashRecordTrans == null) return;
		// 生成账单
		m_objSaveBillRecordTrans.transaction(objSession);
		TfiBillrecord objTfiBillrecord = m_objSaveBillRecordTrans.getNewBillrecord();		
		// 新建收款
		m_objSaveCashRecordTrans.transaction(objSession);
		TfiCashrecord objTfiCashrecord = m_objSaveCashRecordTrans.getSavedCashrecord();
		m_lCrid = objTfiCashrecord.getCrId();
		// 新建核销记录
		AddWriteOffTrans objAddWriteOffTrans = new AddWriteOffTrans();
		objAddWriteOffTrans.setParam(objTfiBillrecord,
				objTfiCashrecord,
				m_strOperId);
		objAddWriteOffTrans.transaction(objSession);
	}
}
