package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class PositionQuery extends HGeneralQuery {
	
	public PositionQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.PositionColumns(ps.psCode,ps.psName,ps.psEname) FROM TdiPosition as ps";
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
