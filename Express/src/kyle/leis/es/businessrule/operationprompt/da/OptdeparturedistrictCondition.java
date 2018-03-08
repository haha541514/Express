package kyle.leis.es.businessrule.operationprompt.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class OptdeparturedistrictCondition extends GeneralCondition {
	public OptdeparturedistrictCondition() {
		m_astrConditions = new String[3];
	}	
	
	public void setBrid(String brId) {
		this.setField(0, brId);
	}

	public String getBrid() {
		return this.getField(0);
	}

	public void setDtcode(String dtCode) {
		this.setField(1, dtCode);
	}

	public String getDtcode() {
		return this.getField(1);
	}
	
	public void setCountrydtcode(String dtCode) {
		this.setField(2, dtCode);
	}

	public String getCountrydtcode() {
		return this.getField(2);
	}	
}
