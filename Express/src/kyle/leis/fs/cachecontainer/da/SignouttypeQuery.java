package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class SignouttypeQuery extends HGeneralQuery {
	
	public SignouttypeQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.SignouttypeColumns(sot.sotCode,sot.sotName,sot.sotEname) FROM TdiSignouttype as sot";
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
