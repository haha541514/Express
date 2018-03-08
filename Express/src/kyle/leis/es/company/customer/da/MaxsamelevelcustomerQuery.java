package kyle.leis.es.company.customer.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class MaxsamelevelcustomerQuery extends JGeneralQuery {
	
	public MaxsamelevelcustomerQuery(){
	    m_strSelectClause = "SELECT max(cm.cm_structruecode) as maxstructruecode FROM t_co_customer cm,t_co_corporation co";
	    m_strWhereClause = "cm.co_code = co.co_code and co.cs_code != 'E'";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cm.co_code_parent = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new MaxsamelevelcustomerColumns();
	}
	
	public void setCocodeparent(String cocodeparent) {
		this.setField(0, cocodeparent);
	}

	public String getCocodeparent() {
		return this.getField(0);
	}

}
