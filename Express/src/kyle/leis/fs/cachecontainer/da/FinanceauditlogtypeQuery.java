package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class FinanceauditlogtypeQuery extends HGeneralQuery {
	
	public FinanceauditlogtypeQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.FinanceauditlogtypeColumns(falt.faltCode,falt.faltContent) FROM TdiFinanceauditlogtype as falt";
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
