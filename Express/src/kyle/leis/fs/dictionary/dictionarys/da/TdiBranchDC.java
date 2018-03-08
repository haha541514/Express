package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiBranch;

public class TdiBranchDC extends HSingleQuery {

	public TdiBranchDC() {
		m_strSelectClause = " from TdiBranch";
		setUseCachesign(true);
	}
	
	public static TdiBranch loadByKey(String strKeycode) throws Exception {
		TdiBranchDC objTdiBranchDC = new TdiBranchDC();
		// 从缓冲中获取数据
		List listResults = objTdiBranchDC.getCacheData("TdiBranch" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiBranch)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiBranchDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiBranch objTdiBranch = (TdiBranch)objList.get(i);
			if (objTdiBranch.getEeCode().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiBranch);
				objQueryCache.refresh("TdiBranch" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiBranch;
			}
		}
		return null;
	}
}
