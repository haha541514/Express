package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.ArrayList;
import java.util.List;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiDictionarymappingkind;

public class TdiDictionarymappingkindDC extends HSingleQuery {

	public TdiDictionarymappingkindDC() {
		m_strSelectClause = " from TdiDictionarymappingkind";
		setUseCachesign(true);
	}
	
	public static TdiDictionarymappingkind loadByKey(String strKeycode) throws Exception {
		TdiDictionarymappingkindDC objTdiDictionarymappingkindDC = new TdiDictionarymappingkindDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiDictionarymappingkindDC.getCacheData("TdiDictionarymappingkind" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiDictionarymappingkind)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiDictionarymappingkindDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiDictionarymappingkind objTdiDictionarymappingkind = (TdiDictionarymappingkind)objList.get(i);
			if (objTdiDictionarymappingkind.getDmkCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiDictionarymappingkind);
				objQueryCache.refresh("TdiDictionarymappingkind" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiDictionarymappingkind;
			}
		}
		return null;
	}
}
