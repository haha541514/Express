package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiPosition;

public class TdiPositionDC extends HSingleQuery {

	public TdiPositionDC() {
		m_strSelectClause = " from TdiPosition";
		setUseCachesign(true);
	}
	
	public static TdiPosition loadByKey(String strKeycode) throws Exception {
		TdiPositionDC objTdiPositionDC = new TdiPositionDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiPositionDC.getCacheData("TdiPosition" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiPosition)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiPositionDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiPosition objTdiPosition = (TdiPosition)objList.get(i);
			if (objTdiPosition.getPsCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiPosition);
				objQueryCache.refresh("TdiPosition" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiPosition;
			}
		}
		return null;
	}
}
