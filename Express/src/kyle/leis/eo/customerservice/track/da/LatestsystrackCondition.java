package kyle.leis.eo.customerservice.track.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class LatestsystrackCondition extends GeneralCondition {
	public LatestsystrackCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setCwcode(String cwcode) {
		this.setField(0, cwcode);
	}

	public String getCwcode() {
		return this.getField(0);
	}

}
