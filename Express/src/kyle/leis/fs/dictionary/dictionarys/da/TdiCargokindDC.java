package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiCargokind;

public class TdiCargokindDC extends HSingleQuery {

	public TdiCargokindDC() {
		m_strSelectClause = " from TdiCargokind";
		setUseCachesign(true);
	}
	
	public static TdiCargokind loadByKey(String strKeycode) throws Exception {
		TdiCargokindDC objTdiCargokindDC = new TdiCargokindDC();
		// 从缓冲中获取数据
		List listResults = objTdiCargokindDC.getCacheData("TdiCargokind" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiCargokind)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiCargokindDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiCargokind objTdiCargokind = (TdiCargokind)objList.get(i);
			if (objTdiCargokind.getCgkCode().toString().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiCargokind);
				objQueryCache.refresh("TdiCargokind" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiCargokind;
			}
		}
		return null;
	}
}
