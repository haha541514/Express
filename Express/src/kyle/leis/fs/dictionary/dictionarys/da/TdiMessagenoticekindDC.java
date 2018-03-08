package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiMessagenoticekind;

public class TdiMessagenoticekindDC extends HSingleQuery {

	public TdiMessagenoticekindDC() {
		m_strSelectClause = " from TdiMessagenoticekind";
		setUseCachesign(true);
	}
	
	public static TdiMessagenoticekind loadByKey(String strKeycode) throws Exception {
		TdiMessagenoticekindDC objTdiMessagenoticekindDC = new TdiMessagenoticekindDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiMessagenoticekindDC.getCacheData("TdiMessagenoticekind" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiMessagenoticekind)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiMessagenoticekindDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiMessagenoticekind objTdiMessagenoticekind = (TdiMessagenoticekind)objList.get(i);
			if (objTdiMessagenoticekind.getMnkCode().toString().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiMessagenoticekind);
				objQueryCache.refresh("TdiMessagenoticekind" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiMessagenoticekind;
			}
		}
		return null;
	}
}
