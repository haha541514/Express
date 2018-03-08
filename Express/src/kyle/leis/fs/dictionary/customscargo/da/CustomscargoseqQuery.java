package kyle.leis.fs.dictionary.customscargo.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class CustomscargoseqQuery extends JGeneralQuery {
	
	public CustomscargoseqQuery(){
	    m_strSelectClause = "SELECT S_CUSTOMSCARGO_CODE.nextval as CustomscargoSeq FROM dual";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] {  };
	    m_aiConditionVariableCount = new int[] {  };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new CustomscargoseqColumns();
	}
	

}
