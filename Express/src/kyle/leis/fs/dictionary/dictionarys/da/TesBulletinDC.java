package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TesBulletin;

public class TesBulletinDC extends HSingleQuery {

	public TesBulletinDC() {
		m_strSelectClause = " from TesBulletin";
		setUseCachesign(true);
	}
	
	public static TesBulletin loadByKey(String strKeycode) throws Exception {
		TesBulletinDC objTesBulletinDC = new TesBulletinDC();
		// 从缓冲中获取数据
		List listResults = objTesBulletinDC.getCacheData("TesBulletin" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TesBulletin)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTesBulletinDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TesBulletin objTesBulletin = (TesBulletin)objList.get(i);
			if (objTesBulletin.getBlId().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTesBulletin);
				objQueryCache.refresh("TesBulletin" + strKeycode, objCacheList);				
				// 返回值				
				return objTesBulletin;
			}
		}
		return null;
	}
}
