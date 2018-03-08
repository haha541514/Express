package kyle.leis.eo.customerservice.track.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class SimpletrackQuery extends JGeneralQuery {
	
	public SimpletrackQuery(){
	    m_strSelectClause = "SELECT swbt.swbt_id,swb.swb_customerewbcode,swbt.swbt_description,swbt.swbt_location,swbt.swbt_occurdate,swbt.swbt_creator,swbt.swbt_modifier,swbt.swbt_createdate,swbt.swbt_modifydate FROM T_CS_SimpleWayBill swb,T_CS_SimpleWayBillTrack swbt";
	    m_strWhereClause = "swb.swb_code=swbt.swb_code";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "swbt.swb_code='~~'", "swb.swb_customerewbcode= '~~'", "swbt.swbt_id = '~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new SimpletrackColumns();
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

	public void setSwbtid(String swbtid) {
		this.setField(2, swbtid);
	}

	public String getSwbtid() {
		return this.getField(2);
	}

}
