package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiChannelstatus;

public class TdiChannelstatusDC extends HSingleQuery {

	public TdiChannelstatusDC() {
		m_strSelectClause = " from TdiChannelstatus";
		setUseCachesign(true);
	}
	
	public static TdiChannelstatus loadByKey(String strKeycode) throws Exception {
		TdiChannelstatusDC objTdiChannelstatusDC = new TdiChannelstatusDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiChannelstatusDC.getCacheData("TdiChannelstatus" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiChannelstatus)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiChannelstatusDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiChannelstatus objTdiChannelstatus = (TdiChannelstatus)objList.get(i);
			if (objTdiChannelstatus.getCsCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiChannelstatus);
				objQueryCache.refresh("TdiChannelstatus" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiChannelstatus;
			}
		}
		return null;
	}
}
