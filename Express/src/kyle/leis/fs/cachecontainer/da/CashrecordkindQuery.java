package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class CashrecordkindQuery extends HGeneralQuery {
	
	public CashrecordkindQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.CashrecordkindColumns(crk.crkCode,crk.crkName,crk.crkEname) FROM TdiCashrecordkind as crk";
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
