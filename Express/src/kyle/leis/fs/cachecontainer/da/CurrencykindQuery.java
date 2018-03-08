package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class CurrencykindQuery extends HGeneralQuery {
	
	public CurrencykindQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.CurrencykindColumns(ck.ckCode, ck.ckName, ck.ckEname) FROM TdiCurrencykind as ck";
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
