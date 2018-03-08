package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiBatterykind;

public class TdiBatterykindDC extends HSingleQuery {

	public TdiBatterykindDC() {
		m_strSelectClause = " from TdiBatterykind";
		setUseCachesign(true);
	}
	
	public static TdiBatterykind loadByKey(String strKeycode) throws Exception {
		TdiBatterykindDC objTdiBatterykindDC = new TdiBatterykindDC();
		// 从缓冲中获取数据
		List listResults = objTdiBatterykindDC.getCacheData("TdiBatterykind" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiBatterykind)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiBatterykindDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiBatterykind objTdiBatterykind = (TdiBatterykind)objList.get(i);
			if (objTdiBatterykind.getBkCode().toString().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiBatterykind);
				objQueryCache.refresh("TdiBatterykind" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiBatterykind;
			}
		}
		return null;
	}
}
