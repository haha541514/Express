package kyle.leis.es.company.customer.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class CawtseqQuery extends JGeneralQuery {
	
	public CawtseqQuery(){
	    m_strSelectClause = "SELECT S_CAWT_ID.nextval as Cawtseq FROM dual";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] {  };
	    m_aiConditionVariableCount = new int[] {  };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new CawtseqColumns();
	}
	

}
