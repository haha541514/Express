package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiArrivaldeparturetype;

public class TdiArrivaldeparturetypeDC extends HSingleQuery {

	public TdiArrivaldeparturetypeDC() {
		m_strSelectClause = " from TdiArrivaldeparturetype";
		setUseCachesign(true);
	}
	
	public static TdiArrivaldeparturetype loadByKey(String strKeycode) throws Exception {
		TdiArrivaldeparturetypeDC objTdiArrivaldeparturetypeDC = new TdiArrivaldeparturetypeDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiArrivaldeparturetypeDC.getCacheData("TdiArrivaldeparturetype" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiArrivaldeparturetype)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiArrivaldeparturetypeDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiArrivaldeparturetype objTdiArrivaldeparturetype = (TdiArrivaldeparturetype)objList.get(i);
			if (objTdiArrivaldeparturetype.getAdtCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiArrivaldeparturetype);
				objQueryCache.refresh("TdiArrivaldeparturetype" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiArrivaldeparturetype;
			}
		}
		return null;
	}
}
