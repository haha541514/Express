package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiCorporationstatus;

public class TdiCorporationstatusDC extends HSingleQuery {

	public TdiCorporationstatusDC() {
		m_strSelectClause = " from TdiCorporationstatus";
		setUseCachesign(true);
	}
	
	public static TdiCorporationstatus loadByKey(String strKeycode) throws Exception {
		TdiCorporationstatusDC objTdiCorporationstatusDC = new TdiCorporationstatusDC();
		// 从缓冲中获取数据
		List listResults = objTdiCorporationstatusDC.getCacheData("TdiCorporationstatus" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiCorporationstatus)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiCorporationstatusDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiCorporationstatus objTdiCorporationstatus = (TdiCorporationstatus)objList.get(i);
			if (objTdiCorporationstatus.getCsCode().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiCorporationstatus);
				objQueryCache.refresh("TdiCorporationstatus" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiCorporationstatus;
			}
		}
		return null;
	}
}
