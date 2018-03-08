package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiInfomationsystemkind;

public class TdiInfomationsystemkindDC extends HSingleQuery {

	public TdiInfomationsystemkindDC() {
		m_strSelectClause = " from TdiInfomationsystemkind";
		setUseCachesign(true);
	}
	
	public static TdiInfomationsystemkind loadByKey(String strKeycode) throws Exception {
		TdiInfomationsystemkindDC objTdiInfomationsystemkindDC = new TdiInfomationsystemkindDC();
		// 从缓冲中获取数据
		List listResults = objTdiInfomationsystemkindDC.getCacheData("TdiInfomationsystemkind" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiInfomationsystemkind)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiInfomationsystemkindDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiInfomationsystemkind objTdiInfomationsystemkind = (TdiInfomationsystemkind)objList.get(i);
			if (objTdiInfomationsystemkind.getIskCode().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiInfomationsystemkind);
				objQueryCache.refresh("TdiInfomationsystemkind" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiInfomationsystemkind;
			}
		}
		return null;
	}
}
