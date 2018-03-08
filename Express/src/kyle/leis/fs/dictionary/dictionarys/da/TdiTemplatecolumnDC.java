package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiTemplatecolumn;

public class TdiTemplatecolumnDC extends HSingleQuery {

	public TdiTemplatecolumnDC() {
		m_strSelectClause = " from TdiTemplatecolumn";
		setUseCachesign(true);
	}
	
	public static TdiTemplatecolumn loadByKey(String strKeycode) throws Exception {
		TdiTemplatecolumnDC objTdiTemplatecolumnDC = new TdiTemplatecolumnDC();
		// �ӻ����л�ȡ����
		List listResults = objTdiTemplatecolumnDC.getCacheData("TdiTemplatecolumn" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiTemplatecolumn)listResults.get(0);
		// δȡ���������ѯ���м�¼
		List objList = objTdiTemplatecolumnDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiTemplatecolumn objTdiTemplatecolumn = (TdiTemplatecolumn)objList.get(i);
			if (objTdiTemplatecolumn.getTcId().toString().equals(strKeycode)) {
				// ˢ�»���
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiTemplatecolumn);
				objQueryCache.refresh("TdiTemplatecolumn" + strKeycode, objCacheList);				
				// ����ֵ				
				return objTdiTemplatecolumn;
			}
		}
		return null;
	}
}
