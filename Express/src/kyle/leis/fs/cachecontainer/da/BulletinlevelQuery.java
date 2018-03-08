package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.HGeneralQuery;
import kyle.common.dbaccess.query.IColumns;

public class BulletinlevelQuery extends HGeneralQuery {
	
	public BulletinlevelQuery(){
	    m_strSelectClause = "SELECT new kyle.leis.fs.cachecontainer.da.BulletinlevelColumns(bl.blCode,bl.blName,bl.blEname) FROM TdiBulletinlevel as bl";
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
