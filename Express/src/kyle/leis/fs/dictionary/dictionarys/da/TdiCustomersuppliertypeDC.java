package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiCustomersuppliertype;

public class TdiCustomersuppliertypeDC extends HSingleQuery {

	public TdiCustomersuppliertypeDC() {
		m_strSelectClause = " from TdiCustomersuppliertype";
		setUseCachesign(true);
	}
	
	public static TdiCustomersuppliertype loadByKey(String strKeycode) throws Exception {
		TdiCustomersuppliertypeDC objTdiCustomersuppliertypeDC = new TdiCustomersuppliertypeDC();
		// 从缓冲中获取数据
		List listResults = objTdiCustomersuppliertypeDC.getCacheData("TdiCustomersuppliertype" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiCustomersuppliertype)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiCustomersuppliertypeDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiCustomersuppliertype objTdiCustomersuppliertype = (TdiCustomersuppliertype)objList.get(i);
			if (objTdiCustomersuppliertype.getCstCode().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiCustomersuppliertype);
				objQueryCache.refresh("TdiCustomersuppliertype" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiCustomersuppliertype;
			}
		}
		return null;
	}
}
