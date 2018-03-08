package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiFinanceauditstatus;

public class TdiFinanceauditstatusDC extends HSingleQuery {

	public TdiFinanceauditstatusDC() {
		m_strSelectClause = " from TdiFinanceauditstatus";
		setUseCachesign(true);
	}
	
	public static TdiFinanceauditstatus loadByKey(String strKeycode) throws Exception {
		TdiFinanceauditstatusDC objTdiFinanceauditstatusDC = new TdiFinanceauditstatusDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiFinanceauditstatusDC.getCacheData("TdiFinanceauditstatus" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiFinanceauditstatus)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiFinanceauditstatusDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiFinanceauditstatus objTdiFinanceauditstatus = (TdiFinanceauditstatus)objList.get(i);
			if (objTdiFinanceauditstatus.getFasCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiFinanceauditstatus);
				objQueryCache.refresh("TdiFinanceauditstatus" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiFinanceauditstatus;
			}
		}
		return null;
	}
}
