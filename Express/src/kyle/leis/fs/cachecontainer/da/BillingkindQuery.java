package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class BillingkindQuery extends HGeneralQuery {
	
	public BillingkindQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.BillingkindColumns(bk.bkCode,bk.bkName,bk.bkEname) FROM TdiBillingkind as bk";
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
