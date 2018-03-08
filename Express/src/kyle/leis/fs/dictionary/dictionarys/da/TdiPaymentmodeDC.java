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
		// �ӻ����л�ȡ����
		List listResults = objTdiPaymentmodeDC.getCacheData("TdiPaymentmode" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiPaymentmode)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiPaymentmodeDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiPaymentmode objTdiPaymentmode = (TdiPaymentmode)objList.get(i);
			if (objTdiPaymentmode.getPmCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiPaymentmode);
				objQueryCache.refresh("TdiPaymentmode" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiPaymentmode;
			}
		}
		return null;
	}
}
