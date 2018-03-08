package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiUnittype;

public class TdiUnittypeDC extends HSingleQuery {

	public TdiUnittypeDC() {
		m_strSelectClause = " from TdiUnittype";
		setUseCachesign(true);
	}
	
	public static TdiUnittype loadByKey(String strKeycode) throws Exception {
		TdiUnittypeDC objTdiUnittypeDC = new TdiUnittypeDC();
		// 从缓冲中获取数据
		List listResults = objTdiUnittypeDC.getCacheData("TdiUnittype" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiUnittype)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiUnittypeDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiUnittype objTdiUnittype = (TdiUnittype)objList.get(i);
			if (objTdiUnittype.getUtCode().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiUnittype);
				objQueryCache.refresh("TdiUnittype" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiUnittype;
			}
		}
		return null;
	}
}
