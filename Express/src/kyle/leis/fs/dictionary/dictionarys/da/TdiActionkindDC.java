package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiActionkind;

public class TdiActionkindDC extends HSingleQuery {

	public TdiActionkindDC() {
		m_strSelectClause = " from TdiActionkind";
		setUseCachesign(true);
	}
	
	public static TdiActionkind loadByKey(String strKeycode) throws Exception {
		TdiActionkindDC objTdiActionkindDC = new TdiActionkindDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiActionkindDC.getCacheData("TdiActionkind" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiActionkind)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiActionkindDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiActionkind objTdiActionkind = (TdiActionkind)objList.get(i);
			if (objTdiActionkind.getAkCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiActionkind);
				objQueryCache.refresh("TdiActionkind" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiActionkind;
			}
		}
		return null;
	}
}
