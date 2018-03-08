package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiEnterpriseelement;

public class TdiEnterpriseelementDC extends HSingleQuery {

	public TdiEnterpriseelementDC() {
		m_strSelectClause = " from TdiEnterpriseelement";
		setUseCachesign(true);
	}
	
	public static TdiEnterpriseelement loadByKey(String strKeycode) throws Exception {
		TdiEnterpriseelementDC objTdiEnterpriseelementDC = new TdiEnterpriseelementDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiEnterpriseelementDC.getCacheData("TdiEnterpriseelement" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiEnterpriseelement)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiEnterpriseelementDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiEnterpriseelement objTdiEnterpriseelement = (TdiEnterpriseelement)objList.get(i);
			if (objTdiEnterpriseelement.getEeCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiEnterpriseelement);
				objQueryCache.refresh("TdiEnterpriseelement" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiEnterpriseelement;
			}
		}
		return null;
	}
}
