package kyle.leis.eo.customerservice.issue.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class IssueactionCondition extends GeneralCondition {
	public IssueactionCondition() {
		m_astrConditions = new String[4];
	}	
	
	public void setIsuid(String isuId) {
		this.setField(0, isuId);
	}

	public String getIsuid() {
		return this.getField(0);
	}

	public void setIsaid(String isaId) {
		this.setField(1, isaId);
	}

	public String getIsaid() {
		return this.getField(1);
	}

	public void setAkcode(String akCode) {
		this.setField(2, akCode);
	}

	public String getAkcode() {
		return this.getField(2);
	}

}
