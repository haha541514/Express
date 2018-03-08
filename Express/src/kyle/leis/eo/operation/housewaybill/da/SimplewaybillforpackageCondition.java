package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class SimplewaybillforpackageCondition extends GeneralCondition {
	public SimplewaybillforpackageCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setCwcode(String cwCode) {
		this.setField(0, cwCode);
	}

	public String getCwcode() {
		return this.getField(0);
	}

}
