package kyle.leis.fs.dictionary.customscargo.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.hi.TdiMemorydeclarename;

public class DeleteMemoryDeclareTrans extends AbstractTransaction {

	private String m_strMdncode;
	public void setParam(String strMdncode) {
		m_strMdncode = strMdncode;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (StringUtility.isNull(m_strMdncode))
			return;
		TdiMemorydeclarename objTMDN = (TdiMemorydeclarename)objSession.load(TdiMemorydeclarename.class, 
				Long.parseLong(m_strMdncode));
		objSession.delete(objTMDN);
	}

}
