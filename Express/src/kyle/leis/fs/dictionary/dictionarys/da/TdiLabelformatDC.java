package kyle.leis.fs.dictionary.dictionarys.da;

import java.util.List;
import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.hi.TdiLabelformat;

public class TdiLabelformatDC extends HSingleQuery {

	public TdiLabelformatDC() {
		m_strSelectClause = " from TdiLabelformat";
		setUseCachesign(true);
	}
	
	public static TdiLabelformat loadByKey(String strKeycode) throws Exception {
		TdiLabelformatDC objTdiLabelformatDC = new TdiLabelformatDC();
		// 从缓冲中获取数据
		List listResults = objTdiLabelformatDC.getCacheData("TdiLabelformat" + strKeycode);
		if (listResults != null && listResults.size() > 0)
			return (TdiLabelformat)listResults.get(0);
		// 未取到数据则查询所有记录
		List objList = objTdiLabelformatDC.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		for (int i = 0; i < objList.size(); i++) {
			TdiLabelformat objTdiLabelformat = (TdiLabelformat)objList.get(i);
			if (objTdiLabelformat.getLfCode().toString().equals(strKeycode)) {
				// 刷新缓冲
				QueryCache objQueryCache = new QueryCache();
				List<Object> objCacheList = new ArrayList<Object>();
				objCacheList.add(objTdiLabelformat);
				objQueryCache.refresh("TdiLabelformat" + strKeycode, objCacheList);				
				// 返回值				
				return objTdiLabelformat;
			}
		}
		return null;
	}
}
