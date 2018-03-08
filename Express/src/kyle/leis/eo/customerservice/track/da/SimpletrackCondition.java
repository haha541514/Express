package kyle.leis.eo.customerservice.track.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class SimpletrackCondition extends GeneralCondition {
	public SimpletrackCondition() {
		m_astrConditions = new String[4];
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
