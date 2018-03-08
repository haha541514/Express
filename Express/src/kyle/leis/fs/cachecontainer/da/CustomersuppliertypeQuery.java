package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class CustomersuppliertypeQuery extends HGeneralQuery {
	
	public CustomersuppliertypeQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.CustomersuppliertypeColumns(cst.cstCode,cst.cstName,cst.cstEname) FROM TdiCustomersuppliertype as cst";
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
