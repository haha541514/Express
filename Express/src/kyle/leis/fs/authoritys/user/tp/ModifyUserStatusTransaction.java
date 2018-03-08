package kyle.leis.fs.authoritys.user.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TdiOperatorstatus;
import net.sf.hibernate.Session;

public class ModifyUserStatusTransaction extends AbstractTransaction {

	private String m_strOperId;
	private String m_strOsCode;

	public void setParam(String strOperId, String strOsCode) {
		this.m_strOperId = strOperId;
		this.m_strOsCode = strOsCode;
	}

	public void transaction(Session objSession) throws Exception {
		if (StringUtility.isNull(m_strOperId)
				|| StringUtility.isNull(m_strOsCode))
			return;

		TdiOperator objTdiOperator = (TdiOperator) objSession.load(
				TdiOperator.class, Long.parseLong(m_strOperId));
		TdiOperatorstatus objTdiOperatorstatus = (TdiOperatorstatus) objSession
				.load(TdiOperatorstatus.class, m_strOsCode);
		objTdiOperator.setTdiOperatorstatus(objTdiOperatorstatus);
		objSession.update(objTdiOperator);
	}

}
