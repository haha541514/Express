package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiFeestatus;

public class TdiFeestatusDC extends HSingleQuery {

	public TdiFeestatusDC() {
		m_strSelectClause = " from TdiFeestatus";
		setUseCachesign(true);
	}
	
	public static TdiFeestatus loadByKey(String strKeycode) throws Exception {
		TdiFeestatusDC objTdiFeestatusDC = new TdiFeestatusDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiFeestatusDC.getCacheData("TdiFeestatus" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiFeestatus)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiFeestatusDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiFeestatus objTdiFeestatus = (TdiFeestatus)objList.get(i);
			if (objTdiFeestatus.getFsCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiFeestatus);
				objQueryCache.refresh("TdiFeestatus" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiFeestatus;
			}
		}
		return null;
	}
}
