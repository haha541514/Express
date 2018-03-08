package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class UspsdistrictCondition extends GeneralCondition {
	public UspsdistrictCondition() {
		m_astrConditions = new String[3];
	}	
	
	public void setHubcode(String hubCode) {
		this.setField(0, hubCode);
	}

	public String getHubcode() {
		return this.getField(0);
	}

	public void setCityname(String cityName) {
		this.setField(1, cityName);
	}

	public String getCityname() {
		return this.getField(1);
	}

}
