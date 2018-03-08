package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiFeekind;

public class TdiFeekindDC extends HSingleQuery {

	public TdiFeekindDC() {
		m_strSelectClause = " from TdiFeekind";
		setUseCachesign(true);
	}
	
	public static TdiFeekind loadByKey(String strKeycode) throws Exception {
		TdiFeekindDC objTdiFeekindDC = new TdiFeekindDC();
		// 从缓冲中获取数据
		List listResults = objTdiFeekindDC.getCacheData("TdiFeekind" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiFeekind)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiFeekindDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiFeekind objTdiFeekind = (TdiFeekind)objList.get(i);
			if (objTdiFeekind.getFkCode().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiFeekind);
				objQueryCache.refresh("TdiFeekind" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiFeekind;
			}
		}
		return null;
	}
}
