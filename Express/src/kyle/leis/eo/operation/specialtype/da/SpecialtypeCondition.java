package kyle.leis.eo.operation.specialtype.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class SpecialtypeCondition extends GeneralCondition {
	public SpecialtypeCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setCwcode(String cwCode) {
		this.setField(0, cwCode);
	}

	public String getCwcode() {
		return this.getField(0);
	}

	public void setEstcode(String estCode) {
		this.setField(1, estCode);
	}

	public String getEstcode() {
		return this.getField(1);
	}

}
