package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiCorewaybillstatus;

public class TdiCorewaybillstatusDC extends HSingleQuery {

	public TdiCorewaybillstatusDC() {
		m_strSelectClause = " from TdiCorewaybillstatus";
		setUseCachesign(true);
	}
	
	public static TdiCorewaybillstatus loadByKey(String strKeycode) throws Exception {
		TdiCorewaybillstatusDC objTdiCorewaybillstatusDC = new TdiCorewaybillstatusDC();
		// 从缓冲中获取数据
		List listResults = objTdiCorewaybillstatusDC.getCacheData("TdiCorewaybillstatus" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiCorewaybillstatus)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiCorewaybillstatusDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiCorewaybillstatus objTdiCorewaybillstatus = (TdiCorewaybillstatus)objList.get(i);
			if (objTdiCorewaybillstatus.getCwsCode().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiCorewaybillstatus);
				objQueryCache.refresh("TdiCorewaybillstatus" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiCorewaybillstatus;
			}
		}
		return null;
	}
}
