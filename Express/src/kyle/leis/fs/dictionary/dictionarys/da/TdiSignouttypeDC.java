package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiSignouttype;

public class TdiSignouttypeDC extends HSingleQuery {

	public TdiSignouttypeDC() {
		m_strSelectClause = " from TdiSignouttype";
		setUseCachesign(true);
	}
	
	public static TdiSignouttype loadByKey(String strKeycode) throws Exception {
		TdiSignouttypeDC objTdiSignouttypeDC = new TdiSignouttypeDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiSignouttypeDC.getCacheData("TdiSignouttype" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiSignouttype)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiSignouttypeDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiSignouttype objTdiSignouttype = (TdiSignouttype)objList.get(i);
			if (objTdiSignouttype.getSotCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiSignouttype);
				objQueryCache.refresh("TdiSignouttype" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiSignouttype;
			}
		}
		return null;
	}
}
