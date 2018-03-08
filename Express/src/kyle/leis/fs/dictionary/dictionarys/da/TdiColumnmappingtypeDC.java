package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.ArrayList;
import java.util.List;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiColumnmappingtype;

public class TdiColumnmappingtypeDC extends HSingleQuery {

	public TdiColumnmappingtypeDC() {
		m_strSelectClause = " from TdiColumnmappingtype";
		setUseCachesign(true);
	}
	
	public static TdiColumnmappingtype loadByKey(String strKeycode) throws Exception {
		TdiColumnmappingtypeDC objTdiColumnmappingtypeDC = new TdiColumnmappingtypeDC();
		// 从缓冲中获取数据
		List listResults = objTdiColumnmappingtypeDC.getCacheData("TdiColumnmappingtype" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiColumnmappingtype)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiColumnmappingtypeDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiColumnmappingtype objTdiColumnmappingtype = (TdiColumnmappingtype)objList.get(i);
			if (objTdiColumnmappingtype.getCmtCode().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiColumnmappingtype);
				objQueryCache.refresh("TdiColumnmappingtype" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiColumnmappingtype;
			}
		}
		return null;
	}
}

