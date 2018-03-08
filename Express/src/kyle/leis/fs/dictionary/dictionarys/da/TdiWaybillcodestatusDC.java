package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiWaybillcodestatus;

public class TdiWaybillcodestatusDC extends HSingleQuery {

	public TdiWaybillcodestatusDC() {
		m_strSelectClause = " from TdiWaybillcodestatus";
		setUseCachesign(true);
	}
	
	public static TdiWaybillcodestatus loadByKey(String strKeycode) throws Exception {
		TdiWaybillcodestatusDC objTdiWaybillcodestatusDC = new TdiWaybillcodestatusDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiWaybillcodestatusDC.getCacheData("TdiWaybillcodestatus" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiWaybillcodestatus)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiWaybillcodestatusDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiWaybillcodestatus objTdiWaybillcodestatus = (TdiWaybillcodestatus)objList.get(i);
			if (objTdiWaybillcodestatus.getCsCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiWaybillcodestatus);
				objQueryCache.refresh("TdiWaybillcodestatus" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiWaybillcodestatus;
			}
		}
		return null;
	}
}
