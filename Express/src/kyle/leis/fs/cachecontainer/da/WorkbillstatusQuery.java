package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class WorkbillstatusQuery extends HGeneralQuery {
	
	public WorkbillstatusQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.WorkbillstatusColumns(wbs.wbsCode,wbs.wbsName,wbs.wbsEname) FROM TdiWorkbillstatus as wbs";
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
