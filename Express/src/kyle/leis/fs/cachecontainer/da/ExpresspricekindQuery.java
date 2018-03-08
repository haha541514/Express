package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class ExpresspricekindQuery extends HGeneralQuery {
	
	public ExpresspricekindQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.ExpresspricekindColumns(epk.epkCode, epk.epkName, epk.epkEname) FROM TdiExpresspricekind as epk";
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
