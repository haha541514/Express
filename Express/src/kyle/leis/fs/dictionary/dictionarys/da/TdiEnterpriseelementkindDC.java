package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiEnterpriseelementkind;

public class TdiEnterpriseelementkindDC extends HSingleQuery {

	public TdiEnterpriseelementkindDC() {
		m_strSelectClause = " from TdiEnterpriseelementkind";
		setUseCachesign(true);
	}
	
	public static TdiEnterpriseelementkind loadByKey(String strKeycode) throws Exception {
		TdiEnterpriseelementkindDC objTdiEnterpriseelementkindDC = new TdiEnterpriseelementkindDC();
		// 从缓冲中获取数据
		List listResults = objTdiEnterpriseelementkindDC.getCacheData("TdiEnterpriseelementkind" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiEnterpriseelementkind)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiEnterpriseelementkindDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiEnterpriseelementkind objTdiEnterpriseelementkind = (TdiEnterpriseelementkind)objList.get(i);
			if (objTdiEnterpriseelementkind.getEekCode().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiEnterpriseelementkind);
				objQueryCache.refresh("TdiEnterpriseelementkind" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiEnterpriseelementkind;
			}
		}
		return null;
	}
}
