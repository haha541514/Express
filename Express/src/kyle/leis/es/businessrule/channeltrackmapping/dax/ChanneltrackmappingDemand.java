package kyle.leis.es.businessrule.channeltrackmapping.dax;

import java.util.List;

import net.sf.hibernate.Session;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.businessrule.channeltrackmapping.da.ChanneltrackmappingColumns;
import kyle.leis.es.businessrule.channeltrackmapping.da.ChanneltrackmappingCondition;
import kyle.leis.es.businessrule.channeltrackmapping.da.ChanneltrackmappingQuery;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiSimplestatusDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiWaybilltrackstatusDC;
import kyle.leis.hi.TbrChanneltrackmapping;
import kyle.leis.hi.TchnChannel;
import kyle.leis.hi.TdiSimplestatus;
import kyle.leis.hi.TdiWaybilltrackstatus;

public class ChanneltrackmappingDemand {
	
	public static ChanneltrackmappingColumns load(String strCtmid) 
	throws Exception {
		ChanneltrackmappingCondition objCTMC = new ChanneltrackmappingCondition();
		objCTMC.setCtmid(strCtmid);
		List listResults = query(objCTMC);
		if (listResults == null || listResults.size() < 1)
			return null;
		return (ChanneltrackmappingColumns)listResults.get(0);
	}
	
	public static List query(ChanneltrackmappingCondition objCTMC) throws Exception {
		ChanneltrackmappingQuery objCTMQ = new ChanneltrackmappingQuery();
		objCTMQ.setCondition(objCTMC);
		return objCTMQ.getResults();
	}
	
	public static ChanneltrackmappingColumns query(String strOriginTrack, boolean isUseCache) throws Exception {
		ChanneltrackmappingQuery objCTMQ = new ChanneltrackmappingQuery();
		objCTMQ.setCtmsourcetrackdesc(strOriginTrack);
		objCTMQ.setUseCachesign(isUseCache);
		List listResults = objCTMQ.getResults();
		if (listResults == null || listResults.size() < 1)
			return null;
		return (ChanneltrackmappingColumns)listResults.get(0);
	}	
	
	
	public static boolean isExistsOrigintrack(String strOriginTrack) throws Exception {
		ChanneltrackmappingQuery objCTMQ = new ChanneltrackmappingQuery();
		objCTMQ.setCtmsourcetrackdesc(strOriginTrack);
		List listResults = objCTMQ.getResults();
		if (listResults == null || listResults.size() < 1)
			return false;
		return true;
	}	
	
	public static void setByColumns(ChanneltrackmappingColumns objCTMC,
			TbrChanneltrackmapping objTCTM,
			Session objSession) throws Exception {
		objTCTM.setCtmSourcetrackdesc(objCTMC.getCtmctmsourcetrackdesc());
		if (!StringUtility.isNull(objCTMC.getChnchncode())) {
			TchnChannel objTchnChannel = TchnChannelDC.loadByKey(objCTMC.getChnchncode());
			objTCTM.setTchnChannel(objTchnChannel);
		}
		String strSscode = objCTMC.getSssscode();
		if (StringUtility.isNull(strSscode)) {
			strSscode = "ON";
		}		
		TdiSimplestatus objTSS = TdiSimplestatusDC.loadByKey(strSscode);
		objTCTM.setTdiSimplestatus(objTSS);
		
		if (!StringUtility.isNull(objCTMC.getWtswbtscode())) {
			TdiWaybilltrackstatus objTWTS = TdiWaybilltrackstatusDC.loadByKey(objCTMC.getWtswbtscode());
			objTCTM.setTdiWaybilltrackstatus(objTWTS);
		}
	}
	
}
