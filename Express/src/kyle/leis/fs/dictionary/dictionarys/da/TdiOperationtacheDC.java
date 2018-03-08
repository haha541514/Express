package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiOperationtache;

public class TdiOperationtacheDC extends HSingleQuery {

	public TdiOperationtacheDC() {
		m_strSelectClause = " from TdiOperationtache";
		setUseCachesign(true);
	}
	
	public static TdiOperationtache loadByKey(String strKeycode) throws Exception {
		TdiOperationtacheDC objTdiOperationtacheDC = new TdiOperationtacheDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiOperationtacheDC.getCacheData("TdiOperationtache" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiOperationtache)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiOperationtacheDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiOperationtache objTdiOperationtache = (TdiOperationtache)objList.get(i);
			if (objTdiOperationtache.getOtCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiOperationtache);
				objQueryCache.refresh("TdiOperationtache" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiOperationtache;
			}
		}
		return null;
	}
}
