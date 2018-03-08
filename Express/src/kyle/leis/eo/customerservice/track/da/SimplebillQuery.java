package kyle.leis.eo.customerservice.track.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class SimplebillQuery extends JGeneralQuery {
	
	public SimplebillQuery(){
	    m_strSelectClause = "SELECT swb.swb_code,swb.swb_customerewbcode FROM T_CS_SimpleWayBill swb";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "swb.swb_code='~~'", "swb.swb_customerewbcode in (~~)"};
	    m_aiConditionVariableCount = new int[] { 1,1};		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new SimplebillColumns();
	}
	
	public void setSwbcode(String swbcode) {
		this.setField(0, swbcode);
	}

	public String getSwbcode() {
		return this.getField(0);
	}

	public void setSwbcustomerewbcode(String swbcustomerewbcode) {
		this.setField(1, swbcustomerewbcode);
	}

	public String getSwbcustomerewbcode() {
		return this.getField(1);
	}
}
