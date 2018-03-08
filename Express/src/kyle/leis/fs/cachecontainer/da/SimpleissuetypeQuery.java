package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class SimpleissuetypeQuery extends HGeneralQuery {
	
	public SimpleissuetypeQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.SimpleissuetypeColumns(isut.isutCode,isut.isutName,isut.isutEname,isut.isutGroup) FROM TdiIssuetype as isut inner join isut.tdiSimplestatus as ss";
	    m_strWhereClause = "ss.ssCode = 'ON'";
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
