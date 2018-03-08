package kyle.leis.fs.dictionary.issuetype.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.fs.dictionary.dictionarys.da.TdiSimplestatusDC;
import kyle.leis.hi.TdiIssuetype;


public class DeleteIssuetypeTransaction extends AbstractTransaction {
	private String m_strIsutcode;
	private String m_strSscode;

	public void setParam(String strIsutcode, String strSscode) throws Exception {
		this.m_strIsutcode = strIsutcode;
		this.m_strSscode = strSscode;
	}

	public void transaction(Session objSession) throws Exception {

		TdiIssuetype objTdiissuetype = (TdiIssuetype) objSession.load(TdiIssuetype.class, m_strIsutcode);
		objTdiissuetype.setTdiSimplestatus(TdiSimplestatusDC.loadByKey(m_strSscode));
		objSession.update(objTdiissuetype);
		

	}

}
