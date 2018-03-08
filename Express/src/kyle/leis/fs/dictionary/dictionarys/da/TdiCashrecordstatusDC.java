package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiCashrecordstatus;

public class TdiCashrecordstatusDC extends HSingleQuery {

	public TdiCashrecordstatusDC() {
		m_strSelectClause = " from TdiCashrecordstatus";
		setUseCachesign(true);
	}
	
	public static TdiCashrecordstatus loadByKey(String strKeycode) throws Exception {
		TdiCashrecordstatusDC objTdiCashrecordstatusDC = new TdiCashrecordstatusDC();
		// 从缓冲中获取数据
		List listResults = objTdiCashrecordstatusDC.getCacheData("TdiCashrecordstatus" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiCashrecordstatus)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiCashrecordstatusDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiCashrecordstatus objTdiCashrecordstatus = (TdiCashrecordstatus)objList.get(i);
			if (objTdiCashrecordstatus.getCrsCode().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiCashrecordstatus);
				objQueryCache.refresh("TdiCashrecordstatus" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiCashrecordstatus;
			}
		}
		return null;
	}
}
