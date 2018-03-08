package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiGuiopenstyle;

public class TdiGuiopenstyleDC extends HSingleQuery {

	public TdiGuiopenstyleDC() {
		m_strSelectClause = " from TdiGuiopenstyle";
		setUseCachesign(true);
	}
	
	public static TdiGuiopenstyle loadByKey(String strKeycode) throws Exception {
		TdiGuiopenstyleDC objTdiGuiopenstyleDC = new TdiGuiopenstyleDC();
		// 从缓冲中获取数据
		List listResults = objTdiGuiopenstyleDC.getCacheData("TdiGuiopenstyle" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiGuiopenstyle)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiGuiopenstyleDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiGuiopenstyle objTdiGuiopenstyle = (TdiGuiopenstyle)objList.get(i);
			if (objTdiGuiopenstyle.getGosCode().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiGuiopenstyle);
				objQueryCache.refresh("TdiGuiopenstyle" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiGuiopenstyle;
			}
		}
		return null;
	}
}
