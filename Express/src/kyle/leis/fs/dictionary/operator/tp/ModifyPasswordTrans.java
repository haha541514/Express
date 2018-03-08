package kyle.leis.fs.dictionary.operator.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.hi.TdiOperator;

public class ModifyPasswordTrans extends AbstractTransaction {
	private String m_strOperId;
	private String m_strNewPassword;
	private TdiOperator m_objOriginTdiOperator;
	
	public void setParam(String strOperId, 
			String strNewPassword) throws Exception {
		m_strOperId = strOperId;
		m_strNewPassword = strNewPassword;
		if (StringUtility.isNull(m_strOperId))
			return;
		m_objOriginTdiOperator = (TdiOperator)HSingleQuery.load(TdiOperator.class, 
				Long.parseLong(m_strOperId));
	}
	
	public TdiOperator getOriginOperator() {
		return m_objOriginTdiOperator;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_objOriginTdiOperator == null)
			return;
		m_objOriginTdiOperator.setOpPassword(m_strNewPassword);
		objSession.update(m_objOriginTdiOperator);
	}

}
