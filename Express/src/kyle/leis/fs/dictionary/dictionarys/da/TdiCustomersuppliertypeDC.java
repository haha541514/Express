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
		// �ӻ����л�ȡ����
		List listResults = objTdiCustomersuppliertypeDC.getCacheData("TdiCustomersuppliertype" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiCustomersuppliertype)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiCustomersuppliertypeDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiCustomersuppliertype objTdiCustomersuppliertype = (TdiCustomersuppliertype)objList.get(i);
			if (objTdiCustomersuppliertype.getCstCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiCustomersuppliertype);
				objQueryCache.refresh("TdiCustomersuppliertype" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiCustomersuppliertype;
			}
		}
		return null;
	}
}
