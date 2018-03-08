package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiState;

public class TdiStateDC extends HSingleQuery {

	public TdiStateDC() {
		m_strSelectClause = " from TdiState";
		setUseCachesign(true);
	}
	
	public static TdiState loadByKey(String strKeycode) throws Exception {
		TdiStateDC objTdiStateDC = new TdiStateDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiStateDC.getCacheData("TdiState" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiState)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiStateDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiState objTdiState = (TdiState)objList.get(i);
			if (objTdiState.getStCode().toString().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiState);
				objQueryCache.refresh("TdiState" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiState;
			}
		}
		return null;
	}
}
