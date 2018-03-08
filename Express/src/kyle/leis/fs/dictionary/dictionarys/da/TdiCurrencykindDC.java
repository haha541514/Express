package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiCurrencykind;

public class TdiCurrencykindDC extends HSingleQuery {

	public TdiCurrencykindDC() {
		m_strSelectClause = " from TdiCurrencykind";
		setUseCachesign(true);
	}
	
	public static TdiCurrencykind loadByKey(String strKeycode) throws Exception {
		TdiCurrencykindDC objTdiCurrencykindDC = new TdiCurrencykindDC();
		// 从缓冲中获取数据
		List listResults = objTdiCurrencykindDC.getCacheData("TdiCurrencykind" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiCurrencykind)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiCurrencykindDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiCurrencykind objTdiCurrencykind = (TdiCurrencykind)objList.get(i);
			if (objTdiCurrencykind.getCkCode().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiCurrencykind);
				objQueryCache.refresh("TdiCurrencykind" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiCurrencykind;
			}
		}
		return null;
	}
}
