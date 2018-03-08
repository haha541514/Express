package kyle.leis.es.bulletin.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class BulletinseqQuery extends JGeneralQuery {
	
	public BulletinseqQuery(){
	    m_strSelectClause = "SELECT S_BULLETIN_ID.nextval as Buildewbcode FROM dual";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] {  };
	    m_aiConditionVariableCount = new int[] {  };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new BulletinseqColumns();
	}
	

}
