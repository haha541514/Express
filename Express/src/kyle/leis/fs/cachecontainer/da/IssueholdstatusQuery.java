package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class IssueholdstatusQuery extends HGeneralQuery {
	
	public IssueholdstatusQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.IssueholdstatusColumns(ihs.ihsCode,ihs.ihsName) FROM TdiIssueholdstatus as ihs";
	    m_strWhereClause = "ss.ssCode = 'ON'";
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
