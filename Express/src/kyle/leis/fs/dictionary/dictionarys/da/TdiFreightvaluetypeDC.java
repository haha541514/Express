package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiFreightvaluetype;

public class TdiFreightvaluetypeDC extends HSingleQuery {

	public TdiFreightvaluetypeDC() {
		m_strSelectClause = " from TdiFreightvaluetype";
		setUseCachesign(true);
	}
	
	public static TdiFreightvaluetype loadByKey(String strKeycode) throws Exception {
		TdiFreightvaluetypeDC objTdiFreightvaluetypeDC = new TdiFreightvaluetypeDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiFreightvaluetypeDC.getCacheData("TdiFreightvaluetype" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiFreightvaluetype)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiFreightvaluetypeDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiFreightvaluetype objTdiFreightvaluetype = (TdiFreightvaluetype)objList.get(i);
			if (objTdiFreightvaluetype.getFvtCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiFreightvaluetype);
				objQueryCache.refresh("TdiFreightvaluetype" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiFreightvaluetype;
			}
		}
		return null;
	}
}
