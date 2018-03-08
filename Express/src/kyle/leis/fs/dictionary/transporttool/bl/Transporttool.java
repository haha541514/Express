package kyle.leis.fs.dictionary.transporttool.bl;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.leis.fs.dictionary.transporttool.da.TransporttoolColumns;
import kyle.leis.fs.dictionary.transporttool.dax.TransporttoolDemand;
import kyle.leis.fs.dictionary.transporttool.tp.SaveTransporttoolTransaction;

public class Transporttool {

	public TransporttoolColumns addTransporttool(TransporttoolColumns objTransporttoolCol) throws Exception
	{
		SaveTransporttoolTransaction objSaveTransporttoolTrans = new SaveTransporttoolTransaction();
		objSaveTransporttoolTrans.setParame(objTransporttoolCol);
		objSaveTransporttoolTrans.execute();
		
		// Ë¢ÐÂ»º³å
		QueryCache objQueryCache = new QueryCache();
		objQueryCache.reset();
		
		return TransporttoolDemand.queryByTtcode(objSaveTransporttoolTrans.getNewTtCode());
	}
}
