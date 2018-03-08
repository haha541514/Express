package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiOperatorstatus;

public class TdiOperatorstatusDC extends HSingleQuery {

	public TdiOperatorstatusDC() {
		m_strSelectClause = " from TdiOperatorstatus";
		setUseCachesign(true);
	}
	
	public static TdiOperatorstatus loadByKey(String strKeycode) throws Exception {
		TdiOperatorstatusDC objTdiOperatorstatusDC = new TdiOperatorstatusDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiOperatorstatusDC.getCacheData("TdiOperatorstatus" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiOperatorstatus)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiOperatorstatusDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiOperatorstatus objTdiOperatorstatus = (TdiOperatorstatus)objList.get(i);
			if (objTdiOperatorstatus.getOsCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiOperatorstatus);
				objQueryCache.refresh("TdiOperatorstatus" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiOperatorstatus;
			}
		}
		return null;
	}
}
