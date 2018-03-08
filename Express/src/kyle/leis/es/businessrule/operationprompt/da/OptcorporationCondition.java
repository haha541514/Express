package kyle.leis.es.businessrule.operationprompt.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class OptcorporationCondition extends GeneralCondition {
	public OptcorporationCondition() {
		m_astrConditions = new String[3];
	}	
	
	public void setBrid(String brId) {
		this.setField(0, brId);
	}

	public String getBrid() {
		return this.getField(0);
	}

	public void setCocode(String coCode) {
		this.setField(1, coCode);
	}

	public String getCocode() {
		return this.getField(1);
	}

	public void setOptccssign(String optcCssign) {
		this.setField(2, optcCssign);
	}

	public String getOptccssign() {
		return this.getField(2);
	}

}
