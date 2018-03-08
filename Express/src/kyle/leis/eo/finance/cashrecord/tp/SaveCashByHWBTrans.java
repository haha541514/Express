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
		// �տ�
		m_objSaveCashRecordTrans = new SaveCashRecordTrans();
		m_objSaveCashRecordTrans.setParam(objCashrecordColumns, strOperId);
		// �˵�
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
		// ��Ӧ�ķ��������˵�������
		if (m_astrCwCode == null || m_astrCwCode.length < 1) return;
		if (m_objSaveCashRecordTrans == null) return;
		// �����˵�
		m_objSaveBillRecordTrans.transaction(objSession);
		TfiBillrecord objTfiBillrecord = m_objSaveBillRecordTrans.getNewBillrecord();		
		// �½��տ�
		m_objSaveCashRecordTrans.transaction(objSession);
		TfiCashrecord objTfiCashrecord = m_objSaveCashRecordTrans.getSavedCashrecord();
		m_lCrid = objTfiCashrecord.getCrId();
		// �½�������¼
		AddWriteOffTrans objAddWriteOffTrans = new AddWriteOffTrans();
		objAddWriteOffTrans.setParam(objTfiBillrecord,
				objTfiCashrecord,
				m_strOperId);
		objAddWriteOffTrans.transaction(objSession);
	}
}
