package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiPricestatus;

public class TdiPricestatusDC extends HSingleQuery {

	public TdiPricestatusDC() {
		m_strSelectClause = " from TdiPricestatus";
		setUseCachesign(true);
	}
	
	public static TdiPricestatus loadByKey(String strKeycode) throws Exception {
		TdiPricestatusDC objTdiPricestatusDC = new TdiPricestatusDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiPricestatusDC.getCacheData("TdiPricestatus" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiPricestatus)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiPricestatusDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiPricestatus objTdiPricestatus = (TdiPricestatus)objList.get(i);
			if (objTdiPricestatus.getPsCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiPricestatus);
				objQueryCache.refresh("TdiPricestatus" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiPricestatus;
			}
		}
		return null;
	}
}
