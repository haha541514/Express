package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiPackagetype;

public class TdiPackagetypeDC extends HSingleQuery {

	public TdiPackagetypeDC() {
		m_strSelectClause = " from TdiPackagetype";
		setUseCachesign(true);
	}
	
	public static TdiPackagetype loadByKey(String strKeycode) throws Exception {
		TdiPackagetypeDC objTdiPackagetypeDC = new TdiPackagetypeDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiPackagetypeDC.getCacheData("TdiPackagetype" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiPackagetype)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiPackagetypeDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiPackagetype objTdiPackagetype = (TdiPackagetype)objList.get(i);
			if (objTdiPackagetype.getPatCode().toString().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiPackagetype);
				objQueryCache.refresh("TdiPackagetype" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiPackagetype;
			}
		}
		return null;
	}
}
