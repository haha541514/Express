package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiTrasporttool;

public class TdiTrasporttoolDC extends HSingleQuery {

	public TdiTrasporttoolDC() {
		m_strSelectClause = " from TdiTrasporttool";
		setUseCachesign(true);
	}
	
	public static TdiTrasporttool loadByKey(String strKeycode) throws Exception {
		TdiTrasporttoolDC objTdiTrasporttoolDC = new TdiTrasporttoolDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiTrasporttoolDC.getCacheData("TdiTrasporttool" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiTrasporttool)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiTrasporttoolDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiTrasporttool objTdiTrasporttool = (TdiTrasporttool)objList.get(i);
			if (objTdiTrasporttool.getTtCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiTrasporttool);
				objQueryCache.refresh("TdiTrasporttool" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiTrasporttool;
			}
		}
		return null;
	}
}
