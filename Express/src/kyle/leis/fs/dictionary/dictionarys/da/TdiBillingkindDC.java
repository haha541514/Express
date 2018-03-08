package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiBillingkind;

public class TdiBillingkindDC extends HSingleQuery {

	public TdiBillingkindDC() {
		m_strSelectClause = " from TdiBillingkind";
		setUseCachesign(true);
	}
	
	public static TdiBillingkind loadByKey(String strKeycode) throws Exception {
		TdiBillingkindDC objTdiBillingkindDC = new TdiBillingkindDC();
		// 从缓冲中获取数据
		List listResults = objTdiBillingkindDC.getCacheData("TdiBillingkind" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiBillingkind)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiBillingkindDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiBillingkind objTdiBillingkind = (TdiBillingkind)objList.get(i);
			if (objTdiBillingkind.getBkCode().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiBillingkind);
				objQueryCache.refresh("TdiBillingkind" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiBillingkind;
			}
		}
		return null;
	}
}
