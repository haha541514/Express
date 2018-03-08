package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiFunction;

public class TdiFunctionDC extends HSingleQuery {

	public TdiFunctionDC() {
		m_strSelectClause = " from TdiFunction";
		setUseCachesign(true);
	}
	
	public static TdiFunction loadByKey(String strKeycode) throws Exception {
		TdiFunctionDC objTdiFunctionDC = new TdiFunctionDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiFunctionDC.getCacheData("TdiFunction" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiFunction)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiFunctionDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiFunction objTdiFunction = (TdiFunction)objList.get(i);
			if (objTdiFunction.getFcCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiFunction);
				objQueryCache.refresh("TdiFunction" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiFunction;
			}
		}
		return null;
	}
}
