package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TfsActionstatusmapping;

public class TfsActionstatusmappingDC extends HSingleQuery {

	public TfsActionstatusmappingDC() {
		m_strSelectClause = " from TfsActionstatusmapping";
		setUseCachesign(true);
	}
	
	public static TfsActionstatusmapping loadByKey(String strKeycode) throws Exception {
		TfsActionstatusmappingDC objTfsActionstatusmappingDC = new TfsActionstatusmappingDC();
		// 从缓冲中获取数据
		List listResults = objTfsActionstatusmappingDC.getCacheData("TfsActionstatusmapping" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TfsActionstatusmapping)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTfsActionstatusmappingDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TfsActionstatusmapping objTfsActionstatusmapping = (TfsActionstatusmapping)objList.get(i);
			if (objTfsActionstatusmapping.getAsmCode().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTfsActionstatusmapping);
				objQueryCache.refresh("TfsActionstatusmapping" + strKeycode, objCacheList);				
				// 返回值				
				return objTfsActionstatusmapping;
			}
		}
		return null;
	}
}
