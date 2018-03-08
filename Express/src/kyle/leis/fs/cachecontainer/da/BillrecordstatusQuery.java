package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class BillrecordstatusQuery extends HGeneralQuery {
	
	public BillrecordstatusQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.BillrecordstatusColumns(brs.brsCode,brs.brsName,brs.brsEname) FROM TdiBillrecordstatus as brs";
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
