package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiExpresspricekind;

public class TdiExpresspricekindDC extends HSingleQuery {

	public TdiExpresspricekindDC() {
		m_strSelectClause = " from TdiExpresspricekind";
		setUseCachesign(true);
	}
	
	public static TdiExpresspricekind loadByKey(String strKeycode) throws Exception {
		TdiExpresspricekindDC objTdiExpresspricekindDC = new TdiExpresspricekindDC();
		// 从缓冲中获取数据
		List listResults = objTdiExpresspricekindDC.getCacheData("TdiExpresspricekind" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiExpresspricekind)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiExpresspricekindDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiExpresspricekind objTdiExpresspricekind = (TdiExpresspricekind)objList.get(i);
			if (objTdiExpresspricekind.getEpkCode().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiExpresspricekind);
				objQueryCache.refresh("TdiExpresspricekind" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiExpresspricekind;
			}
		}
		return null;
	}
}
