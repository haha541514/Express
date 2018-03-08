package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class HousewaybillremarkCondition extends GeneralCondition {
	public HousewaybillremarkCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setIncwcode(String Incwcode) {
		this.setField(0, Incwcode);
	}

	public String getIncwcode() {
		return this.getField(0);
	}

}
