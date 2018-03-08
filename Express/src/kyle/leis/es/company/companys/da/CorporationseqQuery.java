package kyle.leis.es.company.companys.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class CorporationseqQuery extends JGeneralQuery {
	
	public CorporationseqQuery(){
	    m_strSelectClause = "SELECT S_CORPORTION_CODE.nextval as CorporationSeq FROM dual";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] {  };
	    m_aiConditionVariableCount = new int[] {  };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new CorporationseqColumns();
	}
	

}
