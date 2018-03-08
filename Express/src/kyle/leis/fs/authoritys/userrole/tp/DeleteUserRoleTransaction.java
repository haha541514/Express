package kyle.leis.fs.authoritys.userrole.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.hi.TfsUserrole;
import net.sf.hibernate.Session;

public class DeleteUserRoleTransaction extends AbstractTransaction {

	private String m_strUser_code;
	private String[] m_astrRole_code;
	private String m_strIsk_code;

	public void setParam(String strUser_code, String[] astrRole_code,
			String strIsk_code) {
		m_strUser_code = strUser_code;
		m_astrRole_code = astrRole_code;
		m_strIsk_code = strIsk_code;
	}

	public void transaction(Session objSession) throws Exception {
		TfsUserrole objTfsUserrole = null;
		if (m_astrRole_code != null && m_astrRole_code.length > 0) {
			for (int i = 0; i < m_astrRole_code.length; i++) {
				objSession
						.delete("from TfsUserrole ul where ul.comp_id.rlCode ='"
								+ m_astrRole_code[i]
								+ "' and ul.comp_id.urUsercode ='"
								+ m_strUser_code
								+ "' and ul.comp_id.iskCode ='"
								+ m_strIsk_code
								+ "'");
			}
		} /*else {//只有用户和平台编号，没角色编号被认为删除指定用户平台下的所有角色
			objSession
					.delete("from TfsUserrole ul where ul.com_id.urUsercode ='"
							+ m_strUser_code + "' and ul.com_id.iskCode ='"
							+ m_strIsk_code + "'");
		}*/
	}

}
