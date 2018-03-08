package kyle.leis.eo.finance.billrecord.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.finance.billrecord.da.BillrecordColumns;
import kyle.leis.eo.finance.billrecord.dax.BillRecordDemand;
import kyle.leis.hi.TdiBillrecordstatus;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TfiBillrecord;

public class ModifyBillRecordTrans extends AbstractTransaction {
    private String m_strOperId;
    private String m_strBrid;
    private String m_strBrscode;
    private BillrecordColumns m_objBillrecordColumns;
    
    public void setParam(BillrecordColumns objBillrecordColumns, 
    		String strOperId) {
    	m_strOperId = strOperId;
    	m_objBillrecordColumns = objBillrecordColumns;
    }
    
    
	public void setModifyStatusParam(String strBrid, 
			String strOperId, 
			String strBrscode) throws Exception {
		m_strOperId = strOperId;
		m_strBrid = strBrid;
		m_strBrscode = strBrscode;
	}
	
	public void transaction(Session objSession) throws Exception {
		if ((m_objBillrecordColumns == null || StringUtility.isNull(m_objBillrecordColumns.getBrbrid())) &&
				StringUtility.isNull(m_strBrid)) 
			return;
		
		if (m_objBillrecordColumns != null) {
			m_strBrid = m_objBillrecordColumns.getBrbrid();
		}
		if (StringUtility.isNull(m_strBrid)) return;
		
		TfiBillrecord objTfiBillrecord = (TfiBillrecord)objSession.load(TfiBillrecord.class, 
				Long.parseLong(m_strBrid));
		// ÐÞ¸Ä×´Ì¬
		if (!StringUtility.isNull(m_strBrscode)) {
			TdiBillrecordstatus objTdiBillrecordstatus = (TdiBillrecordstatus)objSession.load(TdiBillrecordstatus.class, 
					m_strBrscode);
			objTfiBillrecord.setTdiBillrecordstatus(objTdiBillrecordstatus);
			TdiOperator objMOP = (TdiOperator)objSession.load(TdiOperator.class, Long.parseLong(m_strOperId));
			objTfiBillrecord.setTdiOperatorByBrOpIdModifier(objMOP);
			objTfiBillrecord.setBrModifydate(DateFormatUtility.getSysdate());
			// ÉóºË
			if (m_strBrscode.equals("A")) {
				objTfiBillrecord.setTdiOperatorByBrOpIdAuditor(objMOP);
				objTfiBillrecord.setBrAuditdate(DateFormatUtility.getSysdate());
			}
			
		} else if (m_objBillrecordColumns != null) {
			BillRecordDemand.setBillRecordByColumns(objTfiBillrecord, m_objBillrecordColumns, m_strOperId, objSession);
		}
		objSession.save(objTfiBillrecord);
	}
}
