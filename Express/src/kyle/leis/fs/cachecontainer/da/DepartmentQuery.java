package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class DepartmentQuery extends HGeneralQuery {
	
	public DepartmentQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.DepartmentColumns(dp.dpCode,dp.dpName,dp.dpEname) FROM TdiDepartment as dp";
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
