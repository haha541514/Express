package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiDistributioncenter;

public class TdiDistributioncenterDC extends HSingleQuery {

	public TdiDistributioncenterDC() {
		m_strSelectClause = " from TdiDistributioncenter";
		setUseCachesign(true);
	}
	
	public static TdiDistributioncenter loadByKey(String strKeycode) throws Exception {
		TdiDistributioncenterDC objTdiDistributioncenterDC = new TdiDistributioncenterDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiDistributioncenterDC.getCacheData("TdiDistributioncenter" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiDistributioncenter)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiDistributioncenterDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiDistributioncenter objTdiDistributioncenter = (TdiDistributioncenter)objList.get(i);
			if (objTdiDistributioncenter.getEeCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiDistributioncenter);
				objQueryCache.refresh("TdiDistributioncenter" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiDistributioncenter;
			}
		}
		return null;
	}
}
