package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiBulletinkind;

public class TdiBulletinkindDC extends HSingleQuery {

	public TdiBulletinkindDC() {
		m_strSelectClause = " from TdiBulletinkind";
		setUseCachesign(true);
	}
	
	public static TdiBulletinkind loadByKey(String strKeycode) throws Exception {
		TdiBulletinkindDC objTdiBulletinkindDC = new TdiBulletinkindDC();
		// 从缓冲中获取数据
		List listResults = objTdiBulletinkindDC.getCacheData("TdiBulletinkind" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiBulletinkind)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiBulletinkindDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiBulletinkind objTdiBulletinkind = (TdiBulletinkind)objList.get(i);
			if (objTdiBulletinkind.getBkCode().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiBulletinkind);
				objQueryCache.refresh("TdiBulletinkind" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiBulletinkind;
			}
		}
		return null;
	}
}
