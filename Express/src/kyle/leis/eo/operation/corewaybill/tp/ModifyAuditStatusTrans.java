package kyle.leis.eo.operation.corewaybill.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import net.sf.hibernate.Session;

public class ModifyAuditStatusTrans extends AbstractTransaction {
	private String m_strCwcode;
	private String m_strFascode;
	private String m_strOperId;
	
	public void setParam(String strCwcode, 
			String strCwscode,
			String strOperId) {
		m_strCwcode = strCwcode;
		m_strFascode = strCwscode;
		m_strOperId = strOperId;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (StringUtility.isNull(m_strCwcode)) 
			return;
			
		String strUpdateSql = "update T_OP_COREWAYBILL cw SET cw.fas_code = '" + m_strFascode + 
		"', cw.CW_OP_ID_MODIFIER = " + m_strOperId + 
		", cw.CW_MODIFYDATE = SYSDATE WHERE cw.cw_code = " + m_strCwcode;	
		execute(objSession, strUpdateSql);
	}

}
