package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiBatchwaybillstatus;

public class TdiBatchwaybillstatusDC extends HSingleQuery {

	public TdiBatchwaybillstatusDC() {
		m_strSelectClause = " from TdiBatchwaybillstatus";
		setUseCachesign(true);
	}
	
	public static TdiBatchwaybillstatus loadByKey(String strKeycode) throws Exception {
		TdiBatchwaybillstatusDC objTdiBatchwaybillstatusDC = new TdiBatchwaybillstatusDC();
		// 从缓冲中获取数据
		List listResults = objTdiBatchwaybillstatusDC.getCacheData("TdiBatchwaybillstatus" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiBatchwaybillstatus)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiBatchwaybillstatusDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiBatchwaybillstatus objTdiBatchwaybillstatus = (TdiBatchwaybillstatus)objList.get(i);
			if (objTdiBatchwaybillstatus.getBwsCode().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiBatchwaybillstatus);
				objQueryCache.refresh("TdiBatchwaybillstatus" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiBatchwaybillstatus;
			}
		}
		return null;
	}
}
