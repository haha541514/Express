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
		// 从缓冲中获取数据
		List listResults = objTchnChannelDC.getCacheData("TchnChannel" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TchnChannel)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTchnChannelDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TchnChannel objTchnChannel = (TchnChannel)objList.get(i);
			if (objTchnChannel.getChnCode().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTchnChannel);
				objQueryCache.refresh("TchnChannel" + strKeycode, objCacheList);				
				// 返回值				
				return objTchnChannel;
			}
		}
		return null;
	}
}
