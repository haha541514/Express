package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiArrivaldeparturetype;

public class TdiArrivaldeparturetypeDC extends HSingleQuery {

	public TdiArrivaldeparturetypeDC() {
		m_strSelectClause = " from TdiArrivaldeparturetype";
		setUseCachesign(true);
	}
	
	public static TdiArrivaldeparturetype loadByKey(String strKeycode) throws Exception {
		TdiArrivaldeparturetypeDC objTdiArrivaldeparturetypeDC = new TdiArrivaldeparturetypeDC();
		// 从缓冲中获取数据
		List listResults = objTdiArrivaldeparturetypeDC.getCacheData("TdiArrivaldeparturetype" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiArrivaldeparturetype)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiArrivaldeparturetypeDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiArrivaldeparturetype objTdiArrivaldeparturetype = (TdiArrivaldeparturetype)objList.get(i);
			if (objTdiArrivaldeparturetype.getAdtCode().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiArrivaldeparturetype);
				objQueryCache.refresh("TdiArrivaldeparturetype" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiArrivaldeparturetype;
			}
		}
		return null;
	}
}
