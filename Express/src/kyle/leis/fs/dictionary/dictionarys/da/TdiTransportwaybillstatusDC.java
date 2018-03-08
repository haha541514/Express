package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiTransportwaybillstatus;

public class TdiTransportwaybillstatusDC extends HSingleQuery {

	public TdiTransportwaybillstatusDC() {
		m_strSelectClause = " from TdiTransportwaybillstatus";
		setUseCachesign(true);
	}
	
	public static TdiTransportwaybillstatus loadByKey(String strKeycode) throws Exception {
		TdiTransportwaybillstatusDC objTdiTransportwaybillstatusDC = new TdiTransportwaybillstatusDC();
		// 从缓冲中获取数据
		List listResults = objTdiTransportwaybillstatusDC.getCacheData("TdiTransportwaybillstatus" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiTransportwaybillstatus)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiTransportwaybillstatusDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiTransportwaybillstatus objTdiTransportwaybillstatus = (TdiTransportwaybillstatus)objList.get(i);
			if (objTdiTransportwaybillstatus.getTwbsCode().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiTransportwaybillstatus);
				objQueryCache.refresh("TdiTransportwaybillstatus" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiTransportwaybillstatus;
			}
		}
		return null;
	}
}
