package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiPaymenttype;

public class TdiPaymenttypeDC extends HSingleQuery {

	public TdiPaymenttypeDC() {
		m_strSelectClause = " from TdiPaymenttype";
		setUseCachesign(true);
	}
	
	public static TdiPaymenttype loadByKey(String strKeycode) throws Exception {
		TdiPaymenttypeDC objTdiPaymenttypeDC = new TdiPaymenttypeDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiPaymenttypeDC.getCacheData("TdiPaymenttype" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiPaymenttype)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiPaymenttypeDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiPaymenttype objTdiPaymenttype = (TdiPaymenttype)objList.get(i);
			if (objTdiPaymenttype.getPtCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiPaymenttype);
				objQueryCache.refresh("TdiPaymenttype" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiPaymenttype;
			}
		}
		return null;
	}
}
