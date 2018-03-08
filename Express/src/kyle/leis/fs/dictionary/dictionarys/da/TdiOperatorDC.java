package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiOperator;

public class TdiOperatorDC extends HSingleQuery {

	public TdiOperatorDC(String strKey) {
		m_strSelectClause = " from TdiOperator op where op.opId = " + strKey;
		setUseCachesign(true);
	}
	
	public static TdiOperator loadByKey(String strKeycode) throws Exception {
		TdiOperatorDC objTdiOperatorDC = new TdiOperatorDC(strKeycode);
		// 从缓冲中获取数据
		List listResults = objTdiOperatorDC.getCacheData("TdiOperator" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiOperator)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiOperatorDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiOperator objTdiOperator = (TdiOperator)objList.get(i);
			if (objTdiOperator.getOpId().toString().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiOperator);
				objQueryCache.refresh("TdiOperator" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiOperator;
			}
		}
		return null;
	}
}
