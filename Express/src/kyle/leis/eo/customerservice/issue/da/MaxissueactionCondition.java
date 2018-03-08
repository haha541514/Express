package kyle.leis.eo.customerservice.issue.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class MaxissueactionCondition extends GeneralCondition {
	public MaxissueactionCondition() {
		m_astrConditions = new String[1];
	}	
	
	public void setIsuid(String isuId) {
		this.setField(0, isuId);
	}

	public String getIsuid() {
		return this.getField(0);
	}

}
