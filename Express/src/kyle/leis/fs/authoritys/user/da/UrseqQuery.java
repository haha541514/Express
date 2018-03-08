package kyle.leis.fs.authoritys.user.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class UrseqQuery extends JGeneralQuery {
	
	public UrseqQuery(){
	    m_strSelectClause = "SELECT S_OP_ID.nextval as Urseq FROM dual";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] {  };
	    m_aiConditionVariableCount = new int[] {  };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new UrseqColumns();
	}
	

}
