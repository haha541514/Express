package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class TransportwaybillkindQuery extends HGeneralQuery {
	
	public TransportwaybillkindQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.TransportwaybillkindColumns(twbk.twbkCode,twbk.twbkName,twbk.twbkEname) FROM TdiTransportwaybillkind as twbk";
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
