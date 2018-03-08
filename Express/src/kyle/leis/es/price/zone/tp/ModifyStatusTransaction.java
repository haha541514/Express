package kyle.leis.es.price.zone.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.hi.TdiSimplestatus;
import kyle.leis.hi.TepZone;

public class ModifyStatusTransaction extends AbstractTransaction {
    private String m_strZncode;
    private String m_strSscode;
    
	public void setParam(String strZncode, String strSscode) {
		m_strZncode = strZncode;
		m_strSscode = strSscode;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (StringUtility.isNull(m_strZncode) || StringUtility.isNull(m_strSscode))
			return;
		// ÐÞ¸Ä×´Ì¬
		TepZone objTepZone = (TepZone)objSession.load(TepZone.class, Long.parseLong(m_strZncode));
		TdiSimplestatus objTdiSimplestatus = (TdiSimplestatus)objSession.load(TdiSimplestatus.class,
				m_strSscode);
		objTepZone.setTdiSimplestatus(objTdiSimplestatus);
		objSession.save(objTdiSimplestatus);
	}

}
