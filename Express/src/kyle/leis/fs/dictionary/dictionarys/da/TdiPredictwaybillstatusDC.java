package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiPredictwaybillstatus;

public class TdiPredictwaybillstatusDC extends HSingleQuery {

	public TdiPredictwaybillstatusDC() {
		m_strSelectClause = " from TdiPredictwaybillstatus";
		setUseCachesign(true);
	}
	
	public static TdiPredictwaybillstatus loadByKey(String strKeycode) throws Exception {
		TdiPredictwaybillstatusDC objTdiPredictwaybillstatusDC = new TdiPredictwaybillstatusDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiPredictwaybillstatusDC.getCacheData("TdiPredictwaybillstatus" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiPredictwaybillstatus)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiPredictwaybillstatusDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiPredictwaybillstatus objTdiPredictwaybillstatus = (TdiPredictwaybillstatus)objList.get(i);
			if (objTdiPredictwaybillstatus.getPwbsCode().toString().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiPredictwaybillstatus);
				objQueryCache.refresh("TdiPredictwaybillstatus" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiPredictwaybillstatus;
			}
		}
		return null;
	}
}
