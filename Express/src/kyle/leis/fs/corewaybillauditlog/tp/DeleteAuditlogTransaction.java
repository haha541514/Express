package kyle.leis.fs.corewaybillauditlog.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import net.sf.hibernate.Session;

public class DeleteAuditlogTransaction extends AbstractTransaction {
	private String m_strCwcode;
	private String m_strFaltcode;
	
	public void setParam(String strCwcode, 
			String strFaltcode) {
		m_strCwcode = strCwcode;
		m_strFaltcode = strFaltcode;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (StringUtility.isNull(m_strCwcode)) return;
		
		objSession.delete(" from TfsCorewaybillauditlog as cal where cal.comp_id.cwCode = " + m_strCwcode +
				" and cal.comp_id.faltCode = '" + m_strFaltcode + "'");
	}
}
