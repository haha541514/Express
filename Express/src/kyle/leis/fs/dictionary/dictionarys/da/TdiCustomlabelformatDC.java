package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiCustomlabelformat;

public class TdiCustomlabelformatDC extends HSingleQuery {

	public TdiCustomlabelformatDC() {
		m_strSelectClause = " from TdiCustomlabelformat";
		setUseCachesign(true);
	}
	
	public static TdiCustomlabelformat loadByKey(String strKeycode) throws Exception {
		TdiCustomlabelformatDC objTdiCustomlabelformatDC = new TdiCustomlabelformatDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiCustomlabelformatDC.getCacheData("TdiCustomlabelformat" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiCustomlabelformat)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiCustomlabelformatDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiCustomlabelformat objTdiCustomlabelformat = (TdiCustomlabelformat)objList.get(i);
			if (objTdiCustomlabelformat.getClfCode().toString().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiCustomlabelformat);
				objQueryCache.refresh("TdiCustomlabelformat" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiCustomlabelformat;
			}
		}
		return null;
	}
}
