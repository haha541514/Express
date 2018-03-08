package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TchnChannel;

public class TchnChannelDC extends HSingleQuery {

	public TchnChannelDC(String strKey) {
		m_strSelectClause = " from TchnChannel chn where chn.chnCode = " + strKey;
		setUseCachesign(true);
	}
	
	public static TchnChannel loadByKey(String strKeycode) throws Exception {
		TchnChannelDC objTchnChannelDC = new TchnChannelDC(strKeycode);
		// �ӻ����л�ȡ����
		List listResults = objTchnChannelDC.getCacheData("TchnChannel" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TchnChannel)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTchnChannelDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TchnChannel objTchnChannel = (TchnChannel)objList.get(i);
			if (objTchnChannel.getChnCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTchnChannel);
				objQueryCache.refresh("TchnChannel" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTchnChannel;
			}
		}
		return null;
	}
}
