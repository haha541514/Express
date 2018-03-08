package kyle.leis.fs.dictionary.productkind.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.dictionarys.da.TdiProductkindDC;
import kyle.leis.hi.TdiProductkind;

public class ModifySignTransaction extends AbstractTransaction {
	private String m_strpkCode;
	private String m_strpkSign;
	
	public void setParame(String strpkCode,String strpkSign)
	{
		m_strpkCode = strpkCode;
		m_strpkSign = strpkSign;
	}
	public void transaction(Session objSession) throws Exception {
		if(StringUtility.isNull(m_strpkCode) || StringUtility.isNull(m_strpkSign)) return ;
		TdiProductkind objTdiProductkind = TdiProductkindDC.loadByKey(m_strpkCode);
		objTdiProductkind.setPkSigninrestrictsign(m_strpkSign);
		objSession.update(objTdiProductkind);
	}

}
