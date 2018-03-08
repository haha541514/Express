package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiDistrict;

public class TdiDistrictDC extends HSingleQuery {

	public TdiDistrictDC(String strDtcode) {
		m_strSelectClause = " from TdiDistrict dt where dt.dtCode = " + strDtcode;
		setUseCachesign(true);
	}
	
	public static TdiDistrict loadByKey(String strKeycode) throws Exception {
		TdiDistrictDC objTdiDistrictDC = new TdiDistrictDC(strKeycode);
		// 从缓冲中获取数据
		List listResults = objTdiDistrictDC.getCacheData("TdiDistrict" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiDistrict)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiDistrictDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiDistrict objTdiDistrict = (TdiDistrict)objList.get(i);
			if (objTdiDistrict.getDtCode().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiDistrict);
				objQueryCache.refresh("TdiDistrict" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiDistrict;
			}
		}
		return null;
	}
}
