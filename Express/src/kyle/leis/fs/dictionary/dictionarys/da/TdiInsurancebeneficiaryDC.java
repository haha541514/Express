package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;

import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiInsurancebeneficiary;

public class TdiInsurancebeneficiaryDC extends HSingleQuery {

	public TdiInsurancebeneficiaryDC() {
		m_strSelectClause = " from TdiInsurancebeneficiary";
		setUseCachesign(true);
	}
	
	public static TdiInsurancebeneficiary loadByKey(String strKeycode) throws Exception {
		TdiInsurancebeneficiaryDC objTdiInsurancebeneficiaryDC = new TdiInsurancebeneficiaryDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiInsurancebeneficiaryDC.getCacheData("TdiInsurancebeneficiary" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiInsurancebeneficiary)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiInsurancebeneficiaryDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiInsurancebeneficiary objTdiInsurancebeneficiary = (TdiInsurancebeneficiary)objList.get(i);
			if (objTdiInsurancebeneficiary.getIbCode().toString().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiInsurancebeneficiary);
				objQueryCache.refresh("TdiInsurancebeneficiary" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiInsurancebeneficiary;
			}
		}
		return null;
	}
}
