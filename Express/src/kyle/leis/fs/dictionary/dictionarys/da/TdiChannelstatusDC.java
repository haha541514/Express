package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiChannelstatus;

public class TdiChannelstatusDC extends HSingleQuery {

	public TdiChannelstatusDC() {
		m_strSelectClause = " from TdiChannelstatus";
		setUseCachesign(true);
	}
	
	public static TdiChannelstatus loadByKey(String strKeycode) throws Exception {
		TdiChannelstatusDC objTdiChannelstatusDC = new TdiChannelstatusDC();
		// 从缓冲中获取数据
		List listResults = objTdiChannelstatusDC.getCacheData("TdiChannelstatus" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiChannelstatus)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiChannelstatusDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiChannelstatus objTdiChannelstatus = (TdiChannelstatus)objList.get(i);
			if (objTdiChannelstatus.getCsCode().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiChannelstatus);
				objQueryCache.refresh("TdiChannelstatus" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiChannelstatus;
			}
		}
		return null;
	}
}
