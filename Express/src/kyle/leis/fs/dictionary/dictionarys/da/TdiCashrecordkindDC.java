package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiCashrecordkind;

public class TdiCashrecordkindDC extends HSingleQuery {

	public TdiCashrecordkindDC() {
		m_strSelectClause = " from TdiCashrecordkind";
		setUseCachesign(true);
	}
	
	public static TdiCashrecordkind loadByKey(String strKeycode) throws Exception {
		TdiCashrecordkindDC objTdiCashrecordkindDC = new TdiCashrecordkindDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiCashrecordkindDC.getCacheData("TdiCashrecordkind" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiCashrecordkind)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiCashrecordkindDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiCashrecordkind objTdiCashrecordkind = (TdiCashrecordkind)objList.get(i);
			if (objTdiCashrecordkind.getCrkCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiCashrecordkind);
				objQueryCache.refresh("TdiCashrecordkind" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiCashrecordkind;
			}
		}
		return null;
	}
}
