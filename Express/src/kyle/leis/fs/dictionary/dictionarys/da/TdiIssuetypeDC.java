package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiIssuetype;

public class TdiIssuetypeDC extends HSingleQuery {

	public TdiIssuetypeDC() {
		m_strSelectClause = " from TdiIssuetype";
		setUseCachesign(true);
	}
	
	public static TdiIssuetype loadByKey(String strKeycode) throws Exception {
		TdiIssuetypeDC objTdiIssuetypeDC = new TdiIssuetypeDC();
		// 从缓冲中获取数据
		List listResults = objTdiIssuetypeDC.getCacheData("TdiIssuetype" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiIssuetype)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiIssuetypeDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiIssuetype objTdiIssuetype = (TdiIssuetype)objList.get(i);
			if (objTdiIssuetype.getIsutCode().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiIssuetype);
				objQueryCache.refresh("TdiIssuetype" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiIssuetype;
			}
		}
		return null;
	}
}
