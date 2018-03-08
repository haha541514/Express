package kyle.leis.es.bulletin.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;

public class DeleteBulletinTrans extends AbstractTransaction {
	private String m_strBlid;
	public void setParam(String strBlid) {
		m_strBlid = strBlid;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (StringUtility.isNull(m_strBlid)) return;
		String strDeleteSql="delete from T_ES_BULLETIN bl where bl.BL_ID='"+m_strBlid+"'";
		execute(objSession,strDeleteSql);
	}
}
