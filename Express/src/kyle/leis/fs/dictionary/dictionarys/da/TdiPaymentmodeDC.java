package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiPaymentmode;

public class TdiPaymentmodeDC extends HSingleQuery {

	public TdiPaymentmodeDC() {
		m_strSelectClause = " from TdiPaymentmode";
		setUseCachesign(true);
	}
	
	public static TdiPaymentmode loadByKey(String strKeycode) throws Exception {
		TdiPaymentmodeDC objTdiPaymentmodeDC = new TdiPaymentmodeDC();
		// 从缓冲中获取数据
		List listResults = objTdiPaymentmodeDC.getCacheData("TdiPaymentmode" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiPaymentmode)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiPaymentmodeDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiPaymentmode objTdiPaymentmode = (TdiPaymentmode)objList.get(i);
			if (objTdiPaymentmode.getPmCode().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiPaymentmode);
				objQueryCache.refresh("TdiPaymentmode" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiPaymentmode;
			}
		}
		return null;
	}
}
