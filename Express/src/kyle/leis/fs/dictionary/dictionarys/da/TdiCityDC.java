package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiCity;

public class TdiCityDC extends HSingleQuery {

	public TdiCityDC() {
		m_strSelectClause = " from TdiCity";
		setUseCachesign(true);
	}
	
	public static TdiCity loadByKey(String strKeycode) throws Exception {
		TdiCityDC objTdiCityDC = new TdiCityDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiCityDC.getCacheData("TdiCity" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiCity)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiCityDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiCity objTdiCity = (TdiCity)objList.get(i);
			if (objTdiCity.getCtCode().toString().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiCity);
				objQueryCache.refresh("TdiCity" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiCity;
			}
		}
		return null;
	}
}
