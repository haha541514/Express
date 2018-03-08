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
		// �ӻ����л�ȡ����
		List listResults = objTdiBulletinkindDC.getCacheData("TdiBulletinkind" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiBulletinkind)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiBulletinkindDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiBulletinkind objTdiBulletinkind = (TdiBulletinkind)objList.get(i);
			if (objTdiBulletinkind.getBkCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiBulletinkind);
				objQueryCache.refresh("TdiBulletinkind" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiBulletinkind;
			}
		}
		return null;
	}
}
