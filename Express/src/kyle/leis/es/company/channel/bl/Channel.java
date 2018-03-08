package kyle.leis.es.company.channel.bl;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.leis.es.company.channel.da.ChannelColumns;
import kyle.leis.es.company.channel.dax.ChannelDemand;
import kyle.leis.es.company.channel.tp.ModifyStatusTransaction;
import kyle.leis.es.company.channel.tp.SaveChannelTransaction;

public class Channel {
	public ChannelColumns save(ChannelColumns objChannelColumns, 
			String strOperId) throws Exception {
		SaveChannelTransaction objSCTrans = new SaveChannelTransaction();
		objSCTrans.setParam(objChannelColumns, strOperId);
		objSCTrans.execute();
		
		// Ë¢ÐÂ»º³å
		QueryCache objQueryCache = new QueryCache();
		objQueryCache.reset();		
		
		String strChncode = objSCTrans.getSavedChncode();
		return ChannelDemand.load(strChncode, false);
		
	}
	
	public ChannelColumns modifyStatus(String strChncode, 
			String strCscode, 
			String strOperId) throws Exception {
		ModifyStatusTransaction objMSTrans = new ModifyStatusTransaction();
		objMSTrans.setParam(strChncode, strCscode, strOperId);
		objMSTrans.execute();
		return ChannelDemand.load(strChncode, false);
	}
}
