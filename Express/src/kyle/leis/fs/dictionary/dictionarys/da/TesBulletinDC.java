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
		// �ӻ����л�ȡ����
		List listResults = objTesBulletinDC.getCacheData("TesBulletin" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TesBulletin)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTesBulletinDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TesBulletin objTesBulletin = (TesBulletin)objList.get(i);
			if (objTesBulletin.getBlId().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTesBulletin);
				objQueryCache.refresh("TesBulletin" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTesBulletin;
			}
		}
		return null;
	}
}
