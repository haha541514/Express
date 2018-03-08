package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class LatestcreatedateCondition extends GeneralCondition {
	public LatestcreatedateCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setCo_code_customer(String co_code_customer) {
		this.setField(0, co_code_customer);
	}

	public String getCo_code_customer() {
		return this.getField(0);
	}

}
