package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiFinanceauditstatus;

public class TdiFinanceauditstatusDC extends HSingleQuery {

	public TdiFinanceauditstatusDC() {
		m_strSelectClause = " from TdiFinanceauditstatus";
		setUseCachesign(true);
	}
	
	public static TdiFinanceauditstatus loadByKey(String strKeycode) throws Exception {
		TdiFinanceauditstatusDC objTdiFinanceauditstatusDC = new TdiFinanceauditstatusDC();
		// 从缓冲中获取数据
		List listResults = objTdiFinanceauditstatusDC.getCacheData("TdiFinanceauditstatus" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiFinanceauditstatus)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiFinanceauditstatusDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiFinanceauditstatus objTdiFinanceauditstatus = (TdiFinanceauditstatus)objList.get(i);
			if (objTdiFinanceauditstatus.getFasCode().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiFinanceauditstatus);
				objQueryCache.refresh("TdiFinanceauditstatus" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiFinanceauditstatus;
			}
		}
		return null;
	}
}
