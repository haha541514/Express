package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiTransportwaybillkind;

public class TdiTransportwaybillkindDC extends HSingleQuery {

	public TdiTransportwaybillkindDC() {
		m_strSelectClause = " from TdiTransportwaybillkind";
		setUseCachesign(true);
	}
	
	public static TdiTransportwaybillkind loadByKey(String strKeycode) throws Exception {
		TdiTransportwaybillkindDC objTdiTransportwaybillkindDC = new TdiTransportwaybillkindDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiTransportwaybillkindDC.getCacheData("TdiTransportwaybillkind" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiTransportwaybillkind)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiTransportwaybillkindDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiTransportwaybillkind objTdiTransportwaybillkind = (TdiTransportwaybillkind)objList.get(i);
			if (objTdiTransportwaybillkind.getTwbkCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiTransportwaybillkind);
				objQueryCache.refresh("TdiTransportwaybillkind" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiTransportwaybillkind;
			}
		}
		return null;
	}
}
