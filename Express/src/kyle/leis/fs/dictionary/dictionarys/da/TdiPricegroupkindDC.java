package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiPricegroupkind;

public class TdiPricegroupkindDC extends HSingleQuery {

	public TdiPricegroupkindDC() {
		m_strSelectClause = " from TdiPricegroupkind";
		setUseCachesign(true);
	}
	
	public static TdiPricegroupkind loadByKey(String strKeycode) throws Exception {
		TdiPricegroupkindDC objTdiPricegroupkindDC = new TdiPricegroupkindDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiPricegroupkindDC.getCacheData("TdiPricegroupkind" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiPricegroupkind)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiPricegroupkindDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiPricegroupkind objTdiPricegroupkind = (TdiPricegroupkind)objList.get(i);
			if (objTdiPricegroupkind.getPgkCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiPricegroupkind);
				objQueryCache.refresh("TdiPricegroupkind" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiPricegroupkind;
			}
		}
		return null;
	}
}
