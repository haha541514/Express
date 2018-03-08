package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class FeestatusQuery extends HGeneralQuery {
	
	public FeestatusQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.FeestatusColumns(fs.fsCode, fs.fsName, fs.fsEname) FROM TdiFeestatus as fs";
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
