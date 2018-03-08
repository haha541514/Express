package kyle.fetcher.track.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiSimplestatusDC;
import kyle.leis.hi.TbrChanneltrackmapping;
import kyle.leis.hi.TchnChannel;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TdiSimplestatus;

public class SaveChannelTrackMapping extends AbstractTransaction {
	private String m_strChncode;
	private String m_strOperId;
	private TbrChanneltrackmapping m_objTCTMapping;
	
	public void setParam(String strChncode,
			String strOriginTrackDesc) {
		m_objTCTMapping = new TbrChanneltrackmapping();
		m_objTCTMapping.setCtmCreatedate(DateFormatUtility.getSysdate());
		m_objTCTMapping.setCtmModifydate(DateFormatUtility.getSysdate());
		m_objTCTMapping.setCtmSourcetrackdesc(strOriginTrackDesc);
		m_strOperId = "0";
		m_strChncode = strChncode;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_objTCTMapping == null) return;
		
		if (!StringUtility.isNull(m_strChncode)) {
			TchnChannel objTchnChannel = TchnChannelDC.loadByKey(m_strChncode);
			m_objTCTMapping.setTchnChannel(objTchnChannel);
		}
		if (!StringUtility.isNull(m_strOperId)) {
			TdiOperator objTdiOperator = TdiOperatorDC.loadByKey(m_strOperId); 
			m_objTCTMapping.setTdiOperatorByOpIdModifier(objTdiOperator);
			if (m_objTCTMapping.getCtmId() == null)
				m_objTCTMapping.setTdiOperatorByOpIdCreator(objTdiOperator);
		}
		TdiSimplestatus objTSS = TdiSimplestatusDC.loadByKey("ON");
		m_objTCTMapping.setTdiSimplestatus(objTSS);
		objSession.save(m_objTCTMapping);
	}

}
