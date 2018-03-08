package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiActionkind;

public class TdiActionkindDC extends HSingleQuery {

	public TdiActionkindDC() {
		m_strSelectClause = " from TdiActionkind";
		setUseCachesign(true);
	}
	
	public static TdiActionkind loadByKey(String strKeycode) throws Exception {
		TdiActionkindDC objTdiActionkindDC = new TdiActionkindDC();
		// 从缓冲中获取数据
		List listResults = objTdiActionkindDC.getCacheData("TdiActionkind" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiActionkind)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiActionkindDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiActionkind objTdiActionkind = (TdiActionkind)objList.get(i);
			if (objTdiActionkind.getAkCode().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiActionkind);
				objQueryCache.refresh("TdiActionkind" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiActionkind;
			}
		}
		return null;
	}
}
