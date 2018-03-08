package kyle.leis.eo.customerservice.track.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.hi.TdiOperator;

public class BatchaddSingleTrackTrans extends AbstractTransaction {

	private String[] m_astrCwcode;
	private String m_strOperId;
	private String m_strWbtscode;
	private String m_strDtcode;
	
	public void setParam(String[] astrCwcode,
			String strOperId,
			String strWbtscode,
			String strDtcode) {
		m_astrCwcode = astrCwcode;
		m_strOperId = strOperId;
		m_strWbtscode = strWbtscode;
		m_strDtcode = strDtcode;
	}
	
	
	public void transaction(Session objSession) throws Exception {
		if (m_astrCwcode == null || m_astrCwcode.length < 1)
			return;
		String strDtcode = m_strDtcode;
		if (StringUtility.isNull(strDtcode)) {
			TdiOperator objTdiOperator = TdiOperatorDC.loadByKey(m_strOperId);
			strDtcode = objTdiOperator.getTdiEnterpriseelement().getTdiDistrict().getDtCode();
		}
		for (int i = 0; i < m_astrCwcode.length; i++) {
			AddSingleTrackTrans objASTT = new AddSingleTrackTrans();
			objASTT.setParam(m_astrCwcode[i], 
					strDtcode, 
					m_strWbtscode, 
					m_strOperId, 
					DateFormatUtility.getStandardSysdate());
			objASTT.transaction(objSession);
		}
	}

}
