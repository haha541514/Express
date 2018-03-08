package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class PredictwaybillCondition extends GeneralCondition {
	public PredictwaybillCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setCwcustomerewbcode(String cwCustomerewbcode) {
		this.setField(0, cwCustomerewbcode);
	}

	public String getCwcustomerewbcode() {
		return this.getField(0);
	}

	public void setPkcode(String pkCode) {
		this.setField(1, pkCode);
	}

	public String getPkcode() {
		return this.getField(1);
	}

}
