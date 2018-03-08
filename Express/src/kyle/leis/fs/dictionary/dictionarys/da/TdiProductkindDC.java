package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiProductkind;

public class TdiProductkindDC extends HSingleQuery {

	public TdiProductkindDC() {
		m_strSelectClause = " from TdiProductkind";
		setUseCachesign(true);
	}
	
	public static TdiProductkind loadByKey(String strKeycode) throws Exception {
		TdiProductkindDC objTdiProductkindDC = new TdiProductkindDC();
		// 从缓冲中获取数据
		List listResults = objTdiProductkindDC.getCacheData("TdiProductkind" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiProductkind)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiProductkindDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiProductkind objTdiProductkind = (TdiProductkind)objList.get(i);
			if (objTdiProductkind.getPkCode().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiProductkind);
				objQueryCache.refresh("TdiProductkind" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiProductkind;
			}
		}
		return null;
	}
}
