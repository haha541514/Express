package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiBulletinlevel;

public class TdiBulletinlevelDC extends HSingleQuery {

	public TdiBulletinlevelDC() {
		m_strSelectClause = " from TdiBulletinlevel";
		setUseCachesign(true);
	}
	
	public static TdiBulletinlevel loadByKey(String strKeycode) throws Exception {
		TdiBulletinlevelDC objTdiBulletinlevelDC = new TdiBulletinlevelDC();
		// 从缓冲中获取数据
		List listResults = objTdiBulletinlevelDC.getCacheData("TdiBulletinlevel" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiBulletinlevel)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiBulletinlevelDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiBulletinlevel objTdiBulletinlevel = (TdiBulletinlevel)objList.get(i);
			if (objTdiBulletinlevel.getBlCode().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiBulletinlevel);
				objQueryCache.refresh("TdiBulletinlevel" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiBulletinlevel;
			}
		}
		return null;
	}
}
