package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class ServerstructuregroupQuery extends HGeneralQuery {
	
	public ServerstructuregroupQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.ServerstructuregroupColumns(ssg.ssgCode, ssg.ssgName, ssg.ssgEname) FROM TdiServerstructuregroup as ssg";
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
