package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiPricegroup;

public class TdiPricegroupDC extends HSingleQuery {

	public TdiPricegroupDC() {
		m_strSelectClause = " from TdiPricegroup";
		setUseCachesign(true);
	}
	
	public static TdiPricegroup loadByKey(String strKeycode) throws Exception {
		TdiPricegroupDC objTdiPricegroupDC = new TdiPricegroupDC();
		// 从缓冲中获取数据
		List listResults = objTdiPricegroupDC.getCacheData("TdiPricegroup" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiPricegroup)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiPricegroupDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiPricegroup objTdiPricegroup = (TdiPricegroup)objList.get(i);
			if (objTdiPricegroup.getPgCode().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiPricegroup);
				objQueryCache.refresh("TdiPricegroup" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiPricegroup;
			}
		}
		return null;
	}
}
