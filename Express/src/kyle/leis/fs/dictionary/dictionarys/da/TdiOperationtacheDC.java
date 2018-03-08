package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiOperationtache;

public class TdiOperationtacheDC extends HSingleQuery {

	public TdiOperationtacheDC() {
		m_strSelectClause = " from TdiOperationtache";
		setUseCachesign(true);
	}
	
	public static TdiOperationtache loadByKey(String strKeycode) throws Exception {
		TdiOperationtacheDC objTdiOperationtacheDC = new TdiOperationtacheDC();
		// 从缓冲中获取数据
		List listResults = objTdiOperationtacheDC.getCacheData("TdiOperationtache" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiOperationtache)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiOperationtacheDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiOperationtache objTdiOperationtache = (TdiOperationtache)objList.get(i);
			if (objTdiOperationtache.getOtCode().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiOperationtache);
				objQueryCache.refresh("TdiOperationtache" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiOperationtache;
			}
		}
		return null;
	}
}
