package kyle.leis.es.company.predicttemplate.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class PredicttemplateCondition extends GeneralCondition {
	public PredicttemplateCondition() {
		m_astrConditions = new String[4];
	}	
	
	public void setPotid(String potId) {
		this.setField(0, potId);
	}

	public String getPotid() {
		return this.getField(0);
	}

	public void setPotname(String potName) {
		this.setField(1, potName);
	}

	public String getPotname() {
		return this.getField(1);
	}

	public void setCocode(String coCode) {
		this.setField(2, coCode);
	}

	public String getCocode() {
		return this.getField(2);
	}

}
