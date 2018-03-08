package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiIssueholdstatus;

public class TdiIssueholdstatusDC extends HSingleQuery {

	public TdiIssueholdstatusDC() {
		m_strSelectClause = " from TdiIssueholdstatus";
		setUseCachesign(true);
	}
	
	public static TdiIssueholdstatus loadByKey(String strKeycode) throws Exception {
		TdiIssueholdstatusDC objTdiIssueholdstatusDC = new TdiIssueholdstatusDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiIssueholdstatusDC.getCacheData("TdiIssueholdstatus" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiIssueholdstatus)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiIssueholdstatusDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiIssueholdstatus objTdiIssueholdstatus = (TdiIssueholdstatus)objList.get(i);
			if (objTdiIssueholdstatus.getIhsCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiIssueholdstatus);
				objQueryCache.refresh("TdiIssueholdstatus" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiIssueholdstatus;
			}
		}
		return null;
	}
}
