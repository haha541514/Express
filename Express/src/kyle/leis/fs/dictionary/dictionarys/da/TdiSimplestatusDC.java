package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiSimplestatus;

public class TdiSimplestatusDC extends HSingleQuery {

	public TdiSimplestatusDC() {
		m_strSelectClause = " from TdiSimplestatus";
		setUseCachesign(true);
	}
	
	public static TdiSimplestatus loadByKey(String strKeycode) throws Exception {
		TdiSimplestatusDC objTdiSimplestatusDC = new TdiSimplestatusDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiSimplestatusDC.getCacheData("TdiSimplestatus" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiSimplestatus)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiSimplestatusDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiSimplestatus objTdiSimplestatus = (TdiSimplestatus)objList.get(i);
			if (objTdiSimplestatus.getSsCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiSimplestatus);
				objQueryCache.refresh("TdiSimplestatus" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiSimplestatus;
			}
		}
		return null;
	}
}
