package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiCorporationstatus;

public class TdiCorporationstatusDC extends HSingleQuery {

	public TdiCorporationstatusDC() {
		m_strSelectClause = " from TdiCorporationstatus";
		setUseCachesign(true);
	}
	
	public static TdiCorporationstatus loadByKey(String strKeycode) throws Exception {
		TdiCorporationstatusDC objTdiCorporationstatusDC = new TdiCorporationstatusDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiCorporationstatusDC.getCacheData("TdiCorporationstatus" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiCorporationstatus)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiCorporationstatusDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiCorporationstatus objTdiCorporationstatus = (TdiCorporationstatus)objList.get(i);
			if (objTdiCorporationstatus.getCsCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiCorporationstatus);
				objQueryCache.refresh("TdiCorporationstatus" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiCorporationstatus;
			}
		}
		return null;
	}
}
