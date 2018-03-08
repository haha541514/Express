package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class OperationtacheQuery extends HGeneralQuery {
	
	public OperationtacheQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.OperationtacheColumns(ot.otCode, ot.otName,ot.otEname) FROM TdiOperationtache as ot";
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
