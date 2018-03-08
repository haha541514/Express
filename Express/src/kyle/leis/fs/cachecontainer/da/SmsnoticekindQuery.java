package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class SmsnoticekindQuery extends HGeneralQuery {
	
	public SmsnoticekindQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.SmsnoticekindColumns(snk.snkCode, snk.snkName, snk.snkEname) FROM TdiSmsnoticekind as snk";
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
