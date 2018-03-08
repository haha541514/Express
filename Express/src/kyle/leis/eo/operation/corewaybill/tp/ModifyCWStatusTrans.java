package kyle.leis.eo.operation.corewaybill.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import net.sf.hibernate.Session;

public class ModifyCWStatusTrans extends AbstractTransaction {
	private String m_strCwcode;
	private String m_strCwscode;
	private String m_strOperId;
	
	public void setParam(String strCwcode, 
			String strCwscode,
			String strOperId) {
		m_strCwcode = strCwcode;
		m_strCwscode = strCwscode;
		m_strOperId = strOperId;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (StringUtility.isNull(m_strCwcode)) 
			return;
			
		String strUpdateSql = "update T_OP_COREWAYBILL cw SET cw.cws_code = '" + m_strCwscode + 
		"', cw.CW_OP_ID_MODIFIER = " + m_strOperId + 
		", cw.CW_MODIFYDATE = SYSDATE WHERE cw.cw_code = " + m_strCwcode;	
		execute(objSession, strUpdateSql);
	}

}
