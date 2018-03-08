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
		// �ӻ����л�ȡ����
		List listResults = objTdiInvoiceprinttypeDC.getCacheData("TdiInvoiceprinttype" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiInvoiceprinttype)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiInvoiceprinttypeDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiInvoiceprinttype objTdiInvoiceprinttype = (TdiInvoiceprinttype)objList.get(i);
			if (objTdiInvoiceprinttype.getIptCode().toString().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiInvoiceprinttype);
				objQueryCache.refresh("TdiInvoiceprinttype" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiInvoiceprinttype;
			}
		}
		return null;
	}
}
