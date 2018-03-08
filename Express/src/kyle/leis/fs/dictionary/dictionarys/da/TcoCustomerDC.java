package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TcoCustomer;

public class TcoCustomerDC extends HSingleQuery {

	public TcoCustomerDC(String strKey) {
		m_strSelectClause = " from TcoCustomer cm where cm.coCode = " + strKey;
		setUseCachesign(true);
	}
	
	public static TcoCustomer loadByKey(String strKeycode) throws Exception {
		TcoCustomerDC objTcoCustomerDC = new TcoCustomerDC(strKeycode);
		// 从缓冲中获取数据
		List listResults = objTcoCustomerDC.getCacheData("TcoCustomer" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TcoCustomer)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTcoCustomerDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TcoCustomer objTcoCustomer = (TcoCustomer)objList.get(i);
			if (objTcoCustomer.getCoCode().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTcoCustomer);
				objQueryCache.refresh("TcoCustomer" + strKeycode, objCacheList);				
				// 返回值				
				return objTcoCustomer;
			}
		}
		return null;
	}
}
