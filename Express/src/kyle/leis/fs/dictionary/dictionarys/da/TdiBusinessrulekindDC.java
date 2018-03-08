package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiBusinessrulekind;

public class TdiBusinessrulekindDC extends HSingleQuery {

	public TdiBusinessrulekindDC() {
		m_strSelectClause = " from TdiBusinessrulekind";
		setUseCachesign(true);
	}
	
	public static TdiBusinessrulekind loadByKey(String strKeycode) throws Exception {
		TdiBusinessrulekindDC objTdiBusinessrulekindDC = new TdiBusinessrulekindDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiBusinessrulekindDC.getCacheData("TdiBusinessrulekind" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiBusinessrulekind)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiBusinessrulekindDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiBusinessrulekind objTdiBusinessrulekind = (TdiBusinessrulekind)objList.get(i);
			if (objTdiBusinessrulekind.getBrkCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiBusinessrulekind);
				objQueryCache.refresh("TdiBusinessrulekind" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiBusinessrulekind;
			}
		}
		return null;
	}
}
