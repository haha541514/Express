package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class CargokindQuery extends HGeneralQuery {
	
	public CargokindQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.CargokindColumns(ck.cgkCode,ck.cgkName,ck.cgkEname,ck.cgkBatterysign,ss.ssCode) FROM TdiCargokind as ck inner join ck.tdiSimplestatus as ss";
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
