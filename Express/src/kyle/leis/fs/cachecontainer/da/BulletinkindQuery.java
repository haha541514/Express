package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class BulletinkindQuery extends HGeneralQuery {
	
	public BulletinkindQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.BulletinkindColumns(bk.bkCode,bk.bkName,bk.bkEname) FROM TdiBulletinkind as bk";
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
