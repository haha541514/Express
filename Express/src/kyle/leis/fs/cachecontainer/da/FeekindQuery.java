package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class FeekindQuery extends HGeneralQuery {
	
	public FeekindQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.FeekindColumns(fk.fkCode, fk.fkName, fk.fkEname, fk.fkAccountingonlysign) FROM TdiFeekind as fk";
	    m_strWhereClause = "fk.tdiSimplestatus.ssCode = 'ON'";
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
