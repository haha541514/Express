package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class MessagenoticekindQuery extends HGeneralQuery {
	
	public MessagenoticekindQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.MessagenoticekindColumns(mnk.mnkCode,mnk.mnkName,mnk.mnkEname) FROM TdiMessagenoticekind as mnk";
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
