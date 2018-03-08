package kyle.leis.eo.finance.billrecord.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;

public class UndoBillRecordTrans extends AbstractTransaction {
	private String m_strBrid;
	private String m_strOperId;
	private ModifyBillRecordTrans m_objMBillRecordTrans;
	
	public void setParam(String strBrid, 
			String strOperId) throws Exception {
		m_strBrid = strBrid;
		m_strOperId = strOperId;
		m_objMBillRecordTrans = new ModifyBillRecordTrans();
		m_objMBillRecordTrans.setModifyStatusParam(m_strBrid, m_strOperId, "E");
	}
	
	public void transaction(Session objSession) throws Exception {
		if (StringUtility.isNull(m_strBrid)) return;
		// 修改账单状态
		m_objMBillRecordTrans.transaction(objSession);
		// 修改应收费用
		execute(objSession, "UPDATE T_BL_RECEIVABLE rv " + 
				" SET FS_CODE = 'C'," +
				" BR_ID = null " + 
				" WHERE rv.BR_ID = " + m_strBrid);
		// 修改应付费用
		execute(objSession, "UPDATE T_BL_PAYABLE py " + 
				" SET FS_CODE = 'C'," +
				" BR_ID = null " + 
				" WHERE py.BR_ID = " + m_strBrid);
		// 修改杂项费用
		execute(objSession, "UPDATE t_bl_incidentalfee if " + 
				" SET FS_CODE = 'C'," +
				" BR_ID = null " + 
				" WHERE if.BR_ID = " + m_strBrid);		
	}
}
