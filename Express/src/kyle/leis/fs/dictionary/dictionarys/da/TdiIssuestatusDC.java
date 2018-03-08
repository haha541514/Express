package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiIssuestatus;

public class TdiIssuestatusDC extends HSingleQuery {

	public TdiIssuestatusDC() {
		m_strSelectClause = " from TdiIssuestatus";
		setUseCachesign(true);
	}
	
	public static TdiIssuestatus loadByKey(String strKeycode) throws Exception {
		TdiIssuestatusDC objTdiIssuestatusDC = new TdiIssuestatusDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiIssuestatusDC.getCacheData("TdiIssuestatus" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiIssuestatus)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiIssuestatusDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiIssuestatus objTdiIssuestatus = (TdiIssuestatus)objList.get(i);
			if (objTdiIssuestatus.getIsusCode().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiIssuestatus);
				objQueryCache.refresh("TdiIssuestatus" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiIssuestatus;
			}
		}
		return null;
	}
}
