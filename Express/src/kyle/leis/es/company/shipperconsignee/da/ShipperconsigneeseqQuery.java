package kyle.leis.es.company.shipperconsignee.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class ShipperconsigneeseqQuery extends JGeneralQuery {
	
	public ShipperconsigneeseqQuery(){
	    m_strSelectClause = "SELECT S_SC_CODE.nextval as ShipperconsigneeSeq FROM dual";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] {  };
	    m_aiConditionVariableCount = new int[] {  };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new ShipperconsigneeseqColumns();
	}
	

}
