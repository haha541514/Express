package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiExpressspecialtype;

public class TdiExpressspecialtypeDC extends HSingleQuery {

	public TdiExpressspecialtypeDC() {
		m_strSelectClause = " from TdiExpressspecialtype";
		setUseCachesign(true);
	}
	
	public static TdiExpressspecialtype loadByKey(String strKeycode) throws Exception {
		TdiExpressspecialtypeDC objTdiExpressspecialtypeDC = new TdiExpressspecialtypeDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiExpressspecialtypeDC.getCacheData("TdiExpressspecialtype" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiExpressspecialtype)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiExpressspecialtypeDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiExpressspecialtype objTdiExpressspecialtype = (TdiExpressspecialtype)objList.get(i);
			if (objTdiExpressspecialtype.getEstCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiExpressspecialtype);
				objQueryCache.refresh("TdiExpressspecialtype" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiExpressspecialtype;
			}
		}
		return null;
	}
}
