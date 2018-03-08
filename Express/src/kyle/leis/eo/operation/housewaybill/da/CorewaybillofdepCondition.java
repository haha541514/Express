package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class CorewaybillofdepCondition extends GeneralCondition {
	public CorewaybillofdepCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setBwcodedeparture(String bwcodedeparture) {
		this.setField(0, bwcodedeparture);
	}

	public String getBwcodedeparture() {
		return this.getField(0);
	}

}
