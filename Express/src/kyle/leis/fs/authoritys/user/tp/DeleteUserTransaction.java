package kyle.leis.fs.authoritys.user.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.hi.TdiOperator;
import net.sf.hibernate.Session;

public class DeleteUserTransaction extends AbstractTransaction {

	private String m_strOpId;
	public void setParams(String strOpId)
	{
		m_strOpId = strOpId;
	}
	public void transaction(Session objSession) throws Exception 
	{
		if(StringUtility.isNull(m_strOpId)) return;
		TdiOperator objTdiOperator = (TdiOperator)objSession.load(TdiOperator.class, Long.parseLong(m_strOpId));
		objSession.delete(objTdiOperator);
	}

}
