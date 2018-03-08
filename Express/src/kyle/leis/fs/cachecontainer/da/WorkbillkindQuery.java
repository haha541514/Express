package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class WorkbillkindQuery extends HGeneralQuery {
	
	public WorkbillkindQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.WorkbillkindColumns(wbk.wbkCode,wbk.wbkName,wbk.wbkEname) FROM TdiWorkbillkind as wbk";
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
