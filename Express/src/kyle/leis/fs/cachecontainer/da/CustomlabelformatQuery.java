package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class CustomlabelformatQuery extends HGeneralQuery {
	
	public CustomlabelformatQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.CustomlabelformatColumns(clf.clfCode,clf.clfName,clf.clfEname) FROM TdiCustomlabelformat as clf";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] {  };
	    m_aiConditionVariableCount = new int[] {  };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
