package kyle.leis.es.company.predicttemplate.bl;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.leis.es.company.predicttemplate.da.PredictdicmappingColumns;
import kyle.leis.es.company.predicttemplate.tp.DeleteCountryTransaction;
import kyle.leis.es.company.predicttemplate.tp.SavePredictdicMappingTrans;

/**
 * @author Synchrn
 * @date:2012-5-17
 * @version :1.0
 * 
 */
public class PredictdicMapping {
	/**
	 * 保存映射国家
	 * 
	 * @param objPredictdicmappingColumns
	 * @throws Exception
	 */
	public void save(PredictdicmappingColumns objPredictdicmappingColumns)
			throws Exception {
		SavePredictdicMappingTrans objTrans = new SavePredictdicMappingTrans();
		objTrans.setParam(objPredictdicmappingColumns);
		objTrans.execute();
		QueryCache objQueryCache = new QueryCache();
		objQueryCache.reset();
	}
	
	/*
	 * 删除国家
	 */
	public static boolean deleteCountry(String podmId) {
		try {
			DeleteCountryTransaction objDABTrans = new DeleteCountryTransaction();
			objDABTrans.setParam(podmId);
			objDABTrans.execute();
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
