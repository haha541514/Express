package kyle.leis.es.systemproperty.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class SystempropertyCondition extends GeneralCondition {
	public SystempropertyCondition() {
		m_astrConditions = new String[3];
	}	
	
	public void setSpcode(String spCode) {
		this.setField(0, spCode);
	}

	public String getSpcode() {
		return this.getField(0);
	}

	public void setSpname(String spName) {
		this.setField(1, spName);
	}

	public String getSpname() {
		return this.getField(1);
	}

	public void setEecode(String eeCode) {
		this.setField(2, eeCode);
	}

	public String getEecode() {
		return this.getField(2);
	}

}
