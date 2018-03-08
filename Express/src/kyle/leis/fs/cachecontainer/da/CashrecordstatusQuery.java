package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class CashrecordstatusQuery extends HGeneralQuery {
	
	public CashrecordstatusQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.CashrecordstatusColumns(crs.crsCode,crs.crsName,crs.crsEname) FROM TdiCashrecordstatus as crs";
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
