package kyle.leis.es.smsservice.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class SrrseqQuery extends JGeneralQuery {
	
	public SrrseqQuery(){
	    m_strSelectClause = "SELECT S_SRR_ID.nextval as Srrseq FROM dual";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] {  };
	    m_aiConditionVariableCount = new int[] {  };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new SrrseqColumns();
	}
	

}
