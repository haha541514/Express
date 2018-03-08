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
		// 从缓冲中获取数据
		List listResults = objTdiInsurancebeneficiaryDC.getCacheData("TdiInsurancebeneficiary" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiInsurancebeneficiary)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiInsurancebeneficiaryDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiInsurancebeneficiary objTdiInsurancebeneficiary = (TdiInsurancebeneficiary)objList.get(i);
			if (objTdiInsurancebeneficiary.getIbCode().toString().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiInsurancebeneficiary);
				objQueryCache.refresh("TdiInsurancebeneficiary" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiInsurancebeneficiary;
			}
		}
		return null;
	}
}
