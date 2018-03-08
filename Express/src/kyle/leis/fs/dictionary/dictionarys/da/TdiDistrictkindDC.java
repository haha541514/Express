package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiDistrictkind;

public class TdiDistrictkindDC extends HSingleQuery {

	public TdiDistrictkindDC() {
		m_strSelectClause = " from TdiDistrictkind";
		setUseCachesign(true);
	}
	
	public static TdiDistrictkind loadByKey(String strKeycode) throws Exception {
		TdiDistrictkindDC objTdiDistrictkindDC = new TdiDistrictkindDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiDistrictkindDC.getCacheData("TdiDistrictkind" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiDistrictkind)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiDistrictkindDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiDistrictkind objTdiDistrictkind = (TdiDistrictkind)objList.get(i);
			if (objTdiDistrictkind.getDkCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiDistrictkind);
				objQueryCache.refresh("TdiDistrictkind" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiDistrictkind;
			}
		}
		return null;
	}
}
