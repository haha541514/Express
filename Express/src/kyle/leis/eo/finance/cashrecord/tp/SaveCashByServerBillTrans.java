package kyle.leis.eo.finance.cashrecord.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.finance.cashrecord.da.CashrecordColumns;
import kyle.leis.eo.finance.writeoff.tp.AddSBRWriteOffTrans;
import kyle.leis.eo.finance.writeoff.tp.DeleteSBRWriteOffTrans;
import kyle.leis.hi.TfiCashrecord;
import net.sf.hibernate.Session;

public class SaveCashByServerBillTrans  extends AbstractTransaction {
	private String[] m_astrSWBCode;
	private Long m_lCrid;
	private CashrecordColumns m_objCashrecordColumns;
	private SaveCashRecordTrans m_objSaveCashRecordTrans;;
	private String m_strOperId;
	
	public void setParam(CashrecordColumns objCashrecordColumns, 
			String strOperId, 
			String[] astrSWBCode) throws Exception {
		m_objCashrecordColumns = objCashrecordColumns;
		m_astrSWBCode = astrSWBCode;
		// 付款
		m_objSaveCashRecordTrans = new SaveCashRecordTrans();
		m_objSaveCashRecordTrans.setParam(objCashrecordColumns, strOperId);
		m_strOperId = strOperId;
	}
	
	public Long getNewCrid() {
		return m_lCrid;
	}
	
	public TfiCashrecord getSavedCashrecord() {
		return m_objSaveCashRecordTrans.getSavedCashrecord();
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_objSaveCashRecordTrans == null) return;
		// 作废原核销记录
		if (!StringUtility.isNull(m_objCashrecordColumns.getCrcrid()) &&
				m_objSaveCashRecordTrans.getSavedCashrecord().getTfiWriteoff() != null) {
			DeleteSBRWriteOffTrans objDSBRWOT = new DeleteSBRWriteOffTrans();
			objDSBRWOT.setParam(String.valueOf(m_objSaveCashRecordTrans.getSavedCashrecord().getTfiWriteoff().getWoId()), 
					m_strOperId);
			objDSBRWOT.transaction(objSession);
		}
		// 新建付款
		m_objSaveCashRecordTrans.transaction(objSession);
		TfiCashrecord objTfiCashrecord = m_objSaveCashRecordTrans.getSavedCashrecord();
		m_lCrid = objTfiCashrecord.getCrId();
		// 新建核销记录
		AddSBRWriteOffTrans objAddSBRWriteOffTrans = new AddSBRWriteOffTrans();
		objAddSBRWriteOffTrans.setParam(objTfiCashrecord,
				m_astrSWBCode,
				m_strOperId);
		objAddSBRWriteOffTrans.transaction(objSession);
	}
}
