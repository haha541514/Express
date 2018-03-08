package kyle.leis.es.businessrule.weightrulekind.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.hi.TbrWeightrulekind;
import kyle.leis.hi.TdiSimplestatus;

public class ModifyWeightRuleKindTrans extends AbstractTransaction {
	private String m_strWrkid; 
	private String m_strSscode;
	
	public void setParam(String strWrkid, 
			String strSscode) {
		m_strWrkid = strWrkid;
		m_strSscode = strSscode;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (StringUtility.isNull(m_strWrkid)) return;
			
		TbrWeightrulekind objTbrWeightrulekind = (TbrWeightrulekind)objSession.load(TbrWeightrulekind.class, 
					Long.parseLong(m_strWrkid));
		TdiSimplestatus objTdiSimplestatus = (TdiSimplestatus)objSession.load(TdiSimplestatus.class, 
				m_strSscode);
		objTbrWeightrulekind.setTdiSimplestatus(objTdiSimplestatus);
		objSession.save(objTbrWeightrulekind);
	}
}
