package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiBillrecordstatus;

public class TdiBillrecordstatusDC extends HSingleQuery {

	public TdiBillrecordstatusDC() {
		m_strSelectClause = " from TdiBillrecordstatus";
		setUseCachesign(true);
	}
	
	public static TdiBillrecordstatus loadByKey(String strKeycode) throws Exception {
		TdiBillrecordstatusDC objTdiBillrecordstatusDC = new TdiBillrecordstatusDC();
		// 从缓冲中获取数据
		List listResults = objTdiBillrecordstatusDC.getCacheData("TdiBillrecordstatus" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiBillrecordstatus)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiBillrecordstatusDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiBillrecordstatus objTdiBillrecordstatus = (TdiBillrecordstatus)objList.get(i);
			if (objTdiBillrecordstatus.getBrsCode().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiBillrecordstatus);
				objQueryCache.refresh("TdiBillrecordstatus" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiBillrecordstatus;
			}
		}
		return null;
	}
}
