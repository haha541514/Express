package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class ServerweightkindQuery extends HGeneralQuery {
	
	public ServerweightkindQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.ServerweightkindColumns(swk.swkCode, swk.swkName,swk.swkEname) FROM TdiServerweightkind as swk";
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
