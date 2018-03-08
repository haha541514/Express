package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiDeliverytype;

public class TdiDeliverytypeDC extends HSingleQuery {

	public TdiDeliverytypeDC() {
		m_strSelectClause = " from TdiDeliverytype";
		setUseCachesign(true);
	}
	
	public static TdiDeliverytype loadByKey(String strKeycode) throws Exception {
		TdiDeliverytypeDC objTdiDeliverytypeDC = new TdiDeliverytypeDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiDeliverytypeDC.getCacheData("TdiDeliverytype" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiDeliverytype)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiDeliverytypeDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiDeliverytype objTdiDeliverytype = (TdiDeliverytype)objList.get(i);
			if (objTdiDeliverytype.getDtCode().toString().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiDeliverytype);
				objQueryCache.refresh("TdiDeliverytype" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiDeliverytype;
			}
		}
		return null;
	}
}
