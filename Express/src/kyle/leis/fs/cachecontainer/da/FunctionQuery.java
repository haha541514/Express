package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class FunctionQuery extends HGeneralQuery {
	
	public FunctionQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.FunctionColumns(fc.fcCode,fc.fcName,fc.fcEname) FROM TdiFunction as fc";
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
