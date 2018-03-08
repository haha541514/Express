package kyle.leis.es.businessrule.channeltrackmapping.bl;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.leis.es.businessrule.channeltrackmapping.da.ChanneltrackmappingColumns;
import kyle.leis.es.businessrule.channeltrackmapping.dax.ChanneltrackmappingDemand;
import kyle.leis.es.businessrule.channeltrackmapping.tp.ModifyChanneltrackStatus;
import kyle.leis.es.businessrule.channeltrackmapping.tp.SaveChanneltrackmapping;

public class Channeltrackmapping {
	
	public ChanneltrackmappingColumns save(ChanneltrackmappingColumns objCTMC,
			String strOperID) throws Exception {
		SaveChanneltrackmapping objSCTM = new SaveChanneltrackmapping();
		objSCTM.setParam(objCTMC, strOperID);
		objSCTM.execute();
		
		// Ë¢ÐÂ»º³å
		QueryCache objQueryCache = new QueryCache();
		objQueryCache.reset();			
		
		String strCtmid = objSCTM.getSavedID();
		return ChanneltrackmappingDemand.load(strCtmid);
	}
	
	public void delete(String strCtmid,
			String strOperID) throws Exception {
		ModifyChanneltrackStatus objMCTS = new ModifyChanneltrackStatus();
		objMCTS.setParam(strCtmid, "OFF", strOperID);
		objMCTS.execute();
		
		// Ë¢ÐÂ»º³å
		QueryCache objQueryCache = new QueryCache();
		objQueryCache.reset();			
	}	
	
}
