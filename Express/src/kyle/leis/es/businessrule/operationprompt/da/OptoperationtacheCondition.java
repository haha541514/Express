package kyle.leis.es.businessrule.operationprompt.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class OptoperationtacheCondition extends GeneralCondition {
	public OptoperationtacheCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setBrid(String brId) {
		this.setField(0, brId);
	}

	public String getBrid() {
		return this.getField(0);
	}

	public void setOtcode(String otCode) {
		this.setField(1, otCode);
	}

	public String getOtcode() {
		return this.getField(1);
	}

}
