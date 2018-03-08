package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiDepartment;

public class TdiDepartmentDC extends HSingleQuery {

	public TdiDepartmentDC() {
		m_strSelectClause = " from TdiDepartment";
		setUseCachesign(true);
	}
	
	public static TdiDepartment loadByKey(String strKeycode) throws Exception {
		TdiDepartmentDC objTdiDepartmentDC = new TdiDepartmentDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiDepartmentDC.getCacheData("TdiDepartment" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiDepartment)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiDepartmentDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiDepartment objTdiDepartment = (TdiDepartment)objList.get(i);
			if (objTdiDepartment.getDpCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiDepartment);
				objQueryCache.refresh("TdiDepartment" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiDepartment;
			}
		}
		return null;
	}
}
