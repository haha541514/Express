package kyle.leis.fs.authoritys.role.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class RlseqQuery extends JGeneralQuery {
	
	public RlseqQuery(){
	    m_strSelectClause = "SELECT S_RL_Code.nextval as Rlseq FROM dual";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] {  };
	    m_aiConditionVariableCount = new int[] {  };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new RlseqColumns();
	}
	

}
