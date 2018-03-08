package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;

import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiInvoiceprinttype;

public class TdiInvoiceprinttypeDC extends HSingleQuery {

	public TdiInvoiceprinttypeDC() {
		m_strSelectClause = " from TdiInvoiceprinttype";
		setUseCachesign(true);
	}
	
	public static TdiInvoiceprinttype loadByKey(String strKeycode) throws Exception {
		TdiInvoiceprinttypeDC objTdiInvoiceprinttypeDC = new TdiInvoiceprinttypeDC();
		// 从缓冲中获取数据
		List listResults = objTdiInvoiceprinttypeDC.getCacheData("TdiInvoiceprinttype" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiInvoiceprinttype)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiInvoiceprinttypeDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiInvoiceprinttype objTdiInvoiceprinttype = (TdiInvoiceprinttype)objList.get(i);
			if (objTdiInvoiceprinttype.getIptCode().toString().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiInvoiceprinttype);
				objQueryCache.refresh("TdiInvoiceprinttype" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiInvoiceprinttype;
			}
		}
		return null;
	}
}
