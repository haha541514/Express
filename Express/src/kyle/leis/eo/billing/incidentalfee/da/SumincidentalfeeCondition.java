package kyle.leis.eo.billing.incidentalfee.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class SumincidentalfeeCondition extends GeneralCondition {
	public SumincidentalfeeCondition() {
		m_astrConditions = new String[3];
	}	
	
	public void setCocode(String coCode) {
		this.setField(0, coCode);
	}

	public String getCocode() {
		return this.getField(0);
	}

	public void setBrid(String brId) {
		this.setField(1, brId);
	}

	public String getBrid() {
		return this.getField(1);
	}

}
