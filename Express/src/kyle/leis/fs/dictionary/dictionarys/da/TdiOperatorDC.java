package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiOperator;

public class TdiOperatorDC extends HSingleQuery {

	public TdiOperatorDC(String strKey) {
		m_strSelectClause = " from TdiOperator op where op.opId = " + strKey;
		setUseCachesign(true);
	}
	
	public static TdiOperator loadByKey(String strKeycode) throws Exception {
		TdiOperatorDC objTdiOperatorDC = new TdiOperatorDC(strKeycode);
		// �ӻ����л�ȡ����
		List listResults = objTdiOperatorDC.getCacheData("TdiOperator" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiOperator)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiOperatorDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiOperator objTdiOperator = (TdiOperator)objList.get(i);
			if (objTdiOperator.getOpId().toString().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiOperator);
				objQueryCache.refresh("TdiOperator" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiOperator;
			}
		}
		return null;
	}
}
