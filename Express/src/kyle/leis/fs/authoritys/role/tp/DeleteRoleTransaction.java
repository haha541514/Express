package kyle.leis.fs.authoritys.role.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;

public class DeleteRoleTransaction extends AbstractTransaction {

	private String m_strRole_code;

	// private String m_strIsk_code;

	public void setParam(String strRole_code) {
		m_strRole_code = strRole_code;
	}

	public void transaction(Session objSession) throws Exception {
		if (!StringUtility.isNull(m_strRole_code))
		{
			execute(objSession, "delete from t_fs_role rl " + "where rl.rl_code = '" + m_strRole_code + "'");
		}
			
	}

}
