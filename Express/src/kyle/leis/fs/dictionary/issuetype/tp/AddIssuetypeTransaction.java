package kyle.leis.fs.dictionary.issuetype.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.fs.dictionary.issuetype.da.TdiissuetypeColumns;
import kyle.leis.fs.dictionary.issuetype.dax.TdiissuetypeDemand;
import kyle.leis.hi.TdiIssuetype;

public class AddIssuetypeTransaction extends AbstractTransaction {
	private TdiissuetypeColumns m_objTdiissuetypeColumns;

	public void setParam(TdiissuetypeColumns objTdiissuetypeColumns)
			throws Exception {
		m_objTdiissuetypeColumns = objTdiissuetypeColumns;
	}

	public void transaction(Session objSession) throws Exception {
		TdiIssuetype objTdiissuetype = new TdiIssuetype();
		TdiissuetypeDemand.setTdiissuetypeByColumns(objTdiissuetype,m_objTdiissuetypeColumns, objSession);
		objSession.save(objTdiissuetype);

	}

}
