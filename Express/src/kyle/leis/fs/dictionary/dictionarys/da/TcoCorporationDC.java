package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TcoCorporation;

public class TcoCorporationDC extends HSingleQuery {

	public TcoCorporationDC(String strKey) {
		m_strSelectClause = " from TcoCorporation co where co.coCode = " + strKey;
		setUseCachesign(true);
	}
	
	public static TcoCorporation loadByKey(String strKeycode) throws Exception {
		TcoCorporationDC objTcoCorporationDC = new TcoCorporationDC(strKeycode);
		// 从缓冲中获取数据
		List listResults = objTcoCorporationDC.getCacheData("TcoCorporation" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TcoCorporation)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTcoCorporationDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TcoCorporation objTcoCorporation = (TcoCorporation)objList.get(i);
			if (objTcoCorporation.getCoCode().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTcoCorporation);
				objQueryCache.refresh("TcoCorporation" + strKeycode, objCacheList);				
				// 返回值				
				return objTcoCorporation;
			}
		}
		return null;
	}
}
