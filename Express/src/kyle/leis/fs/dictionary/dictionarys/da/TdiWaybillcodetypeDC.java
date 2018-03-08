package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiWaybillcodetype;

public class TdiWaybillcodetypeDC extends HSingleQuery {

	public TdiWaybillcodetypeDC() {
		m_strSelectClause = " from TdiWaybillcodetype";
		setUseCachesign(true);
	}
	
	public static TdiWaybillcodetype loadByKey(String strKeycode) throws Exception {
		TdiWaybillcodetypeDC objTdiWaybillcodetypeDC = new TdiWaybillcodetypeDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiWaybillcodetypeDC.getCacheData("TdiWaybillcodetype" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiWaybillcodetype)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiWaybillcodetypeDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiWaybillcodetype objTdiWaybillcodetype = (TdiWaybillcodetype)objList.get(i);
			if (objTdiWaybillcodetype.getCtCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiWaybillcodetype);
				objQueryCache.refresh("TdiWaybillcodetype" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiWaybillcodetype;
			}
		}
		return null;
	}
}
