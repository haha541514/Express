package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiCustomertype;

public class TdiCustomertypeDC extends HSingleQuery {

	public TdiCustomertypeDC() {
		m_strSelectClause = " from TdiCustomertype";
		setUseCachesign(true);
	}
	
	public static TdiCustomertype loadByKey(String strKeycode) throws Exception {
		TdiCustomertypeDC objTdiCustomertypeDC = new TdiCustomertypeDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiCustomertypeDC.getCacheData("TdiCustomertype" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiCustomertype)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiCustomertypeDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiCustomertype objTdiCustomertype = (TdiCustomertype)objList.get(i);
			if (objTdiCustomertype.getCtCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiCustomertype);
				objQueryCache.refresh("TdiCustomertype" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiCustomertype;
			}
		}
		return null;
	}
}
