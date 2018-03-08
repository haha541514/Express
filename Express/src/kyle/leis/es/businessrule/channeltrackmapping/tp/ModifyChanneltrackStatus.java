package kyle.leis.es.businessrule.channeltrackmapping.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiSimplestatusDC;
import kyle.leis.hi.TbrChanneltrackmapping;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TdiSimplestatus;

public class ModifyChanneltrackStatus extends AbstractTransaction {

	private String m_strCtmid;
	private String m_strNewsscode;
	private String m_strOperId;
	
	public void setParam(String strCtmid,
			String strNewSscode,
			String strOperId) {
		m_strCtmid = strCtmid;
		m_strNewsscode = strNewSscode;
		m_strOperId = strOperId;
	}
	
	public void transaction(Session objSession) throws Exception {
		TbrChanneltrackmapping objTCTM = (TbrChanneltrackmapping)objSession.load(TbrChanneltrackmapping.class, 
				Long.parseLong(m_strCtmid));
		objTCTM.setCtmModifydate(DateFormatUtility.getSysdate());
		
		TdiOperator objTOP = TdiOperatorDC.loadByKey(m_strOperId);
		objTCTM.setTdiOperatorByOpIdModifier(objTOP);
		
		TdiSimplestatus objTSS = TdiSimplestatusDC.loadByKey(m_strNewsscode);
		objTCTM.setTdiSimplestatus(objTSS);
	}

}
