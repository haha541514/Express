package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiWaybillcodekind;

public class TdiWaybillcodekindDC extends HSingleQuery {

	public TdiWaybillcodekindDC() {
		m_strSelectClause = " from TdiWaybillcodekind";
		setUseCachesign(true);
	}
	
	public static TdiWaybillcodekind loadByKey(String strKeycode) throws Exception {
		TdiWaybillcodekindDC objTdiWaybillcodekindDC = new TdiWaybillcodekindDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiWaybillcodekindDC.getCacheData("TdiWaybillcodekind" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiWaybillcodekind)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiWaybillcodekindDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiWaybillcodekind objTdiWaybillcodekind = (TdiWaybillcodekind)objList.get(i);
			if (objTdiWaybillcodekind.getBckCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiWaybillcodekind);
				objQueryCache.refresh("TdiWaybillcodekind" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiWaybillcodekind;
			}
		}
		return null;
	}
}
