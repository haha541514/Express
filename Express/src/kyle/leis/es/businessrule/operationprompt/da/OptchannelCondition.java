package kyle.leis.es.businessrule.operationprompt.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class OptchannelCondition extends GeneralCondition {
	public OptchannelCondition() {
		m_astrConditions = new String[3];
	}	
	
	public void setBrid(String brId) {
		this.setField(0, brId);
	}

	public String getBrid() {
		return this.getField(0);
	}

	public void setChncode(String chnCode) {
		this.setField(1, chnCode);
	}

	public String getChncode() {
		return this.getField(1);
	}

	public void setOptncssign(String optnCssign) {
		this.setField(2, optnCssign);
	}

	public String getOptncssign() {
		return this.getField(2);
	}

}
