package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class CountryCondition extends GeneralCondition {
	public CountryCondition() {
		m_astrConditions = new String[3];
	}	
	
	public void setDkcode(String dkcode) {
		this.setField(0, dkcode);
	}

	public String getDkcode() {
		return this.getField(0);
	}

	public void setDthubcode(String dthubcode) {
		this.setField(1, dthubcode);
	}

	public String getDthubcode() {
		return this.getField(1);
	}

}
