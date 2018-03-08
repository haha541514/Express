package kyle.leis.fs.dictionary.enterpriseelement.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class EeseqQuery extends JGeneralQuery {
	
	public EeseqQuery(){
	    m_strSelectClause = "SELECT S_EE_CODE.nextval as Eeseq FROM dual";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] {  };
	    m_aiConditionVariableCount = new int[] {  };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new EeseqColumns();
	}
	

}
