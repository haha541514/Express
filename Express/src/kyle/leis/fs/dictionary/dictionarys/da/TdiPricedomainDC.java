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
		// 从缓冲中获取数据
		List listResults = objTdiPricedomainDC.getCacheData("TdiPricedomain" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiPricedomain)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiPricedomainDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiPricedomain objTdiPricedomain = (TdiPricedomain)objList.get(i);
			if (objTdiPricedomain.getPdCode().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiPricedomain);
				objQueryCache.refresh("TdiPricedomain" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiPricedomain;
			}
		}
		return null;
	}
}
