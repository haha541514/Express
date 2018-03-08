package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiFinanceauditlogtype;

public class TdiFinanceauditlogtypeDC extends HSingleQuery {

	public TdiFinanceauditlogtypeDC() {
		m_strSelectClause = " from TdiFinanceauditlogtype";
		setUseCachesign(true);
	}
	
	public static TdiFinanceauditlogtype loadByKey(String strKeycode) throws Exception {
		TdiFinanceauditlogtypeDC objTdiFinanceauditlogtypeDC = new TdiFinanceauditlogtypeDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiFinanceauditlogtypeDC.getCacheData("TdiFinanceauditlogtype" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiFinanceauditlogtype)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiFinanceauditlogtypeDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiFinanceauditlogtype objTdiFinanceauditlogtype = (TdiFinanceauditlogtype)objList.get(i);
			if (objTdiFinanceauditlogtype.getFaltCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiFinanceauditlogtype);
				objQueryCache.refresh("TdiFinanceauditlogtype" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiFinanceauditlogtype;
			}
		}
		return null;
	}
}
