package kyle.leis.fs.authoritys.user.dax;

import kyle.leis.fs.authoritys.user.da.UserQuery;

public class UserQueryEX extends UserQuery {
	public UserQueryEX(String openID){
		m_strWhereClause = "op.opMsnname = '" + openID + "'";
	}
	
	public UserQueryEX(String[] fcCodes){
		m_strWhereClause = "fc.fcCode in (";
		for (int i = 0; i < fcCodes.length; i++) {
			String fcCode = fcCodes[i];
			if (i != 0) {
				m_strWhereClause += ",";
			}
			m_strWhereClause += "'" + fcCode + "'";
		}
		m_strWhereClause += ")";
	}
}
