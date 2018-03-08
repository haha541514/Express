package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class CustomertypeQuery extends HGeneralQuery {
	
	public CustomertypeQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.CustomertypeColumns(ct.ctCode,ct.ctName,ct.ctEname) FROM TdiCustomertype as ct";
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
