package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TfsWebpageaccess;

public class TfsWebpageaccessDC extends HSingleQuery {

	public TfsWebpageaccessDC() {
		m_strSelectClause = " from TfsWebpageaccess";
		setUseCachesign(true);
	}
	
	public static TfsWebpageaccess loadByKey(String strKeycode) throws Exception {
		TfsWebpageaccessDC objTfsWebpageaccessDC = new TfsWebpageaccessDC();
		// �ӻ����л�ȡ����
		List listResults = objTfsWebpageaccessDC.getCacheData("TfsWebpageaccess" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TfsWebpageaccess)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTfsWebpageaccessDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TfsWebpageaccess objTfsWebpageaccess = (TfsWebpageaccess)objList.get(i);
			if (objTfsWebpageaccess.getWpaCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTfsWebpageaccess);
				objQueryCache.refresh("TfsWebpageaccess" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTfsWebpageaccess;
			}
		}
		return null;
	}
}
