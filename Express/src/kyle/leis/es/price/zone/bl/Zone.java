package kyle.leis.es.price.zone.bl;

import java.util.List;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.leis.es.price.zone.da.ZoneColumns;
import kyle.leis.es.price.zone.dax.ZoneDemand;
import kyle.leis.es.price.zone.dax.ZoneQueryReturn;
import kyle.leis.es.price.zone.tp.ModifyStatusTransaction;
import kyle.leis.es.price.zone.tp.SaveZoneTransaction;

public class Zone {
	public ZoneQueryReturn save(ZoneColumns objZoneCol, 
			List listZnvalueCol, 
			List listZnvdistrictCol, 
			List listZndpostcodeCol, 
			String strOperId) 
	throws Exception {
		SaveZoneTransaction objModifyZoneT = new SaveZoneTransaction();
		objModifyZoneT.setParam(objZoneCol, 
				listZnvalueCol, 
				listZnvdistrictCol, 
				listZndpostcodeCol, 
				strOperId);
		objModifyZoneT.execute();
		
		// Ë¢ÐÂ»º³å
		QueryCache objQueryCache = new QueryCache();
		objQueryCache.reset();		
		
		String strZncode = objModifyZoneT.getZncode();
		return ZoneDemand.loadByZncode(strZncode);
	}
	
	public void modifyStatus(String strZncode, String strSscode)
	throws Exception {
		ModifyStatusTransaction objModifyStatusT = new ModifyStatusTransaction();
		objModifyStatusT.setParam(strZncode, strSscode);
		objModifyStatusT.execute();
	}
	
}
