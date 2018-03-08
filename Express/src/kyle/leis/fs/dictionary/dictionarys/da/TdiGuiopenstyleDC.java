package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiGuiopenstyle;

public class TdiGuiopenstyleDC extends HSingleQuery {

	public TdiGuiopenstyleDC() {
		m_strSelectClause = " from TdiGuiopenstyle";
		setUseCachesign(true);
	}
	
	public static TdiGuiopenstyle loadByKey(String strKeycode) throws Exception {
		TdiGuiopenstyleDC objTdiGuiopenstyleDC = new TdiGuiopenstyleDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiGuiopenstyleDC.getCacheData("TdiGuiopenstyle" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiGuiopenstyle)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiGuiopenstyleDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiGuiopenstyle objTdiGuiopenstyle = (TdiGuiopenstyle)objList.get(i);
			if (objTdiGuiopenstyle.getGosCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiGuiopenstyle);
				objQueryCache.refresh("TdiGuiopenstyle" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiGuiopenstyle;
			}
		}
		return null;
	}
}
