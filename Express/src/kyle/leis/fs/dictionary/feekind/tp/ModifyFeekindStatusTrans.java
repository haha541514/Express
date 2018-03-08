package kyle.leis.fs.dictionary.feekind.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.dictionarys.da.TdiFeekindDC;
import kyle.leis.hi.TdiFeekind;
import kyle.leis.hi.TdiSimplestatus;
import net.sf.hibernate.Session;

public class ModifyFeekindStatusTrans extends AbstractTransaction {

	private String m_strFkcode;
	private String m_strSscode;
	
	public void setParam(String strFkcode,String strSscode)
	{
		this.m_strFkcode = strFkcode;
		this.m_strSscode = strSscode;
	}
	public void transaction(Session objSession) throws Exception {
		if(StringUtility.isNull(m_strFkcode) || StringUtility.isNull(m_strSscode)) return;
		
		TdiFeekind objTdiFeekind = (TdiFeekind)TdiFeekindDC.load(TdiFeekind.class,m_strFkcode);
		objTdiFeekind.setTdiSimplestatus((TdiSimplestatus)objSession.load(TdiSimplestatus.class, m_strSscode));
		objSession.update(objTdiFeekind);
	}

}
