package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiRegion;

public class TdiRegionDC extends HSingleQuery {

	public TdiRegionDC() {
		m_strSelectClause = " from TdiRegion";
		setUseCachesign(true);
	}
	
	public static TdiRegion loadByKey(String strKeycode) throws Exception {
		TdiRegionDC objTdiRegionDC = new TdiRegionDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiRegionDC.getCacheData("TdiRegion" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiRegion)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiRegionDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiRegion objTdiRegion = (TdiRegion)objList.get(i);
			if (objTdiRegion.getRgCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiRegion);
				objQueryCache.refresh("TdiRegion" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiRegion;
			}
		}
		return null;
	}
}
