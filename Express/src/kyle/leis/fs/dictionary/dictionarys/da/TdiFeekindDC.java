package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiFeekind;

public class TdiFeekindDC extends HSingleQuery {

	public TdiFeekindDC() {
		m_strSelectClause = " from TdiFeekind";
		setUseCachesign(true);
	}
	
	public static TdiFeekind loadByKey(String strKeycode) throws Exception {
		TdiFeekindDC objTdiFeekindDC = new TdiFeekindDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiFeekindDC.getCacheData("TdiFeekind" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiFeekind)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiFeekindDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiFeekind objTdiFeekind = (TdiFeekind)objList.get(i);
			if (objTdiFeekind.getFkCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiFeekind);
				objQueryCache.refresh("TdiFeekind" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiFeekind;
			}
		}
		return null;
	}
}
