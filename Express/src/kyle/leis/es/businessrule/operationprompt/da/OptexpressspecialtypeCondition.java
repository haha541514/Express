package kyle.leis.es.businessrule.operationprompt.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class OptexpressspecialtypeCondition extends GeneralCondition {
	public OptexpressspecialtypeCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setBrid(String brId) {
		this.setField(0, brId);
	}

	public String getBrid() {
		return this.getField(0);
	}

	public void setEstcode(String estCode) {
		this.setField(1, estCode);
	}

	public String getEstcode() {
		return this.getField(1);
	}

}
