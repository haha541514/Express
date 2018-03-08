package kyle.leis.fs.authoritys.gmenus.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import net.sf.hibernate.Session;

public class DeleteGmenusTransaction extends AbstractTransaction {

	private String m_strGmcode;
	public void setParam(String strGmcode)
	{
		m_strGmcode = strGmcode;
	}
	public void transaction(Session objSession) throws Exception {
		if(m_strGmcode == null) return;
		objSession.delete("from TfsGuimenu gm where gm.gmCode = '"
				+ m_strGmcode
				+"'");
	}

}
