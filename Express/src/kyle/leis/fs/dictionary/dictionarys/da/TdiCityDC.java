package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiCity;

public class TdiCityDC extends HSingleQuery {

	public TdiCityDC() {
		m_strSelectClause = " from TdiCity";
		setUseCachesign(true);
	}
	
	public static TdiCity loadByKey(String strKeycode) throws Exception {
		TdiCityDC objTdiCityDC = new TdiCityDC();
		// 从缓冲中获取数据
		List listResults = objTdiCityDC.getCacheData("TdiCity" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiCity)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiCityDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiCity objTdiCity = (TdiCity)objList.get(i);
			if (objTdiCity.getCtCode().toString().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiCity);
				objQueryCache.refresh("TdiCity" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiCity;
			}
		}
		return null;
	}
}
