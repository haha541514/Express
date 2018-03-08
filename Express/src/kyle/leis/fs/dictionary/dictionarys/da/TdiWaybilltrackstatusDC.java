package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiWaybilltrackstatus;

public class TdiWaybilltrackstatusDC extends HSingleQuery {

	public TdiWaybilltrackstatusDC() {
		m_strSelectClause = " from TdiWaybilltrackstatus";
		setUseCachesign(true);
	}
	
	public static TdiWaybilltrackstatus loadByKey(String strKeycode) throws Exception {
		TdiWaybilltrackstatusDC objTdiWaybilltrackstatusDC = new TdiWaybilltrackstatusDC();
		// 从缓冲中获取数据
		List listResults = objTdiWaybilltrackstatusDC.getCacheData("TdiWaybilltrackstatus" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiWaybilltrackstatus)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiWaybilltrackstatusDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiWaybilltrackstatus objTdiWaybilltrackstatus = (TdiWaybilltrackstatus)objList.get(i);
			if (objTdiWaybilltrackstatus.getWbtsCode().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiWaybilltrackstatus);
				objQueryCache.refresh("TdiWaybilltrackstatus" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiWaybilltrackstatus;
			}
		}
		return null;
	}
}
