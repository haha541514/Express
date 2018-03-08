package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiCargotype;

public class TdiCargotypeDC extends HSingleQuery {

	public TdiCargotypeDC() {
		m_strSelectClause = " from TdiCargotype";
		setUseCachesign(true);
	}
	
	public static TdiCargotype loadByKey(String strKeycode) throws Exception {
		TdiCargotypeDC objTdiCargotypeDC = new TdiCargotypeDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiCargotypeDC.getCacheData("TdiCargotype" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiCargotype)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiCargotypeDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiCargotype objTdiCargotype = (TdiCargotype)objList.get(i);
			if (objTdiCargotype.getCtCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiCargotype);
				objQueryCache.refresh("TdiCargotype" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiCargotype;
			}
		}
		return null;
	}
}
