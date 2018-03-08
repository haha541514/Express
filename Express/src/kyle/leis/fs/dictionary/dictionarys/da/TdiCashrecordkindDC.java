package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiCashrecordkind;

public class TdiCashrecordkindDC extends HSingleQuery {

	public TdiCashrecordkindDC() {
		m_strSelectClause = " from TdiCashrecordkind";
		setUseCachesign(true);
	}
	
	public static TdiCashrecordkind loadByKey(String strKeycode) throws Exception {
		TdiCashrecordkindDC objTdiCashrecordkindDC = new TdiCashrecordkindDC();
		// 从缓冲中获取数据
		List listResults = objTdiCashrecordkindDC.getCacheData("TdiCashrecordkind" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiCashrecordkind)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiCashrecordkindDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiCashrecordkind objTdiCashrecordkind = (TdiCashrecordkind)objList.get(i);
			if (objTdiCashrecordkind.getCrkCode().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiCashrecordkind);
				objQueryCache.refresh("TdiCashrecordkind" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiCashrecordkind;
			}
		}
		return null;
	}
}
