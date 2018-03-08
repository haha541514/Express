package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class IssuegradeQuery extends HGeneralQuery {
	
	public IssuegradeQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.IssuegradeColumns(isug.isugCode,isug.isugName) FROM TdiIssuegrade as isug";
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
