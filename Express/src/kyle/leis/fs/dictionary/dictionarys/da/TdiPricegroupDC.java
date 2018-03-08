package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiPricegroup;

public class TdiPricegroupDC extends HSingleQuery {

	public TdiPricegroupDC() {
		m_strSelectClause = " from TdiPricegroup";
		setUseCachesign(true);
	}
	
	public static TdiPricegroup loadByKey(String strKeycode) throws Exception {
		TdiPricegroupDC objTdiPricegroupDC = new TdiPricegroupDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiPricegroupDC.getCacheData("TdiPricegroup" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiPricegroup)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiPricegroupDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiPricegroup objTdiPricegroup = (TdiPricegroup)objList.get(i);
			if (objTdiPricegroup.getPgCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiPricegroup);
				objQueryCache.refresh("TdiPricegroup" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiPricegroup;
			}
		}
		return null;
	}
}
