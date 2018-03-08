package kyle.leis.fs.dictionary.productkind.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.dictionarys.da.TdiSimplestatusDC;
import kyle.leis.hi.TdiProductkind;
import kyle.leis.hi.TdiSimplestatus;
import net.sf.hibernate.Session;

public class ModifyStatusTransaction extends AbstractTransaction {

	private String m_strpkCode;
	private String m_strssCode;
	
	public void setParame(String strpkCode,String strssCode)
	{
		m_strpkCode = strpkCode;
		m_strssCode = strssCode;
	}
	public void transaction(Session objSession) throws Exception {
		if(StringUtility.isNull(m_strpkCode) || StringUtility.isNull(m_strssCode)) return;
		TdiProductkind objTdiProductkind = (TdiProductkind)objSession.load(TdiProductkind.class, m_strpkCode);
		TdiSimplestatus objTdiSimplestatus = TdiSimplestatusDC.loadByKey(m_strssCode);
		objTdiProductkind.setTdiSimplestatus(objTdiSimplestatus);
		objSession.update(objTdiProductkind);
	}

}
