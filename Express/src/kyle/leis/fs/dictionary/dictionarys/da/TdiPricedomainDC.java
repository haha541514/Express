package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiPricedomain;

public class TdiPricedomainDC extends HSingleQuery {

	public TdiPricedomainDC() {
		m_strSelectClause = " from TdiPricedomain";
		setUseCachesign(true);
	}
	
	public static TdiPricedomain loadByKey(String strKeycode) throws Exception {
		TdiPricedomainDC objTdiPricedomainDC = new TdiPricedomainDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiPricedomainDC.getCacheData("TdiPricedomain" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiPricedomain)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiPricedomainDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiPricedomain objTdiPricedomain = (TdiPricedomain)objList.get(i);
			if (objTdiPricedomain.getPdCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiPricedomain);
				objQueryCache.refresh("TdiPricedomain" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiPricedomain;
			}
		}
		return null;
	}
}
