package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class CustomerTypeQuery extends JGeneralQuery {
	
	public CustomerTypeQuery(){
	    m_strSelectClause = "SELECT dt.ct_code,dt.ct_name,dt.ct_ename FROM t_di_customertype dt";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] {  };
	    m_aiConditionVariableCount = new int[] {  };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new CustomerTypeColumns();
	}
	

}
