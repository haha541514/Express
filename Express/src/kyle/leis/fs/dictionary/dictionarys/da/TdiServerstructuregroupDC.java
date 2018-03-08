package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiServerstructuregroup;

public class TdiServerstructuregroupDC extends HSingleQuery {

	public TdiServerstructuregroupDC() {
		m_strSelectClause = " from TdiServerstructuregroup";
		setUseCachesign(true);
	}
	
	public static TdiServerstructuregroup loadByKey(String strKeycode) throws Exception {
		TdiServerstructuregroupDC objTdiServerstructuregroupDC = new TdiServerstructuregroupDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiServerstructuregroupDC.getCacheData("TdiServerstructuregroup" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiServerstructuregroup)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiServerstructuregroupDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiServerstructuregroup objTdiServerstructuregroup = (TdiServerstructuregroup)objList.get(i);
			if (objTdiServerstructuregroup.getSsgCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiServerstructuregroup);
				objQueryCache.refresh("TdiServerstructuregroup" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiServerstructuregroup;
			}
		}
		return null;
	}
}
