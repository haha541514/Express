package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiIssuegrade;

public class TdiIssuegradeDC extends HSingleQuery {

	public TdiIssuegradeDC() {
		m_strSelectClause = " from TdiIssuegrade";
		setUseCachesign(true);
	}
	
	public static TdiIssuegrade loadByKey(String strKeycode) throws Exception {
		TdiIssuegradeDC objTdiIssuegradeDC = new TdiIssuegradeDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiIssuegradeDC.getCacheData("TdiIssuegrade" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiIssuegrade)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiIssuegradeDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiIssuegrade objTdiIssuegrade = (TdiIssuegrade)objList.get(i);
			if (objTdiIssuegrade.getIsugCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiIssuegrade);
				objQueryCache.refresh("TdiIssuegrade" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiIssuegrade;
			}
		}
		return null;
	}
}
