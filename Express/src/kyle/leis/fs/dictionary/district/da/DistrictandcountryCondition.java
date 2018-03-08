package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class DistrictandcountryCondition extends GeneralCondition {
	public DistrictandcountryCondition() {
		m_astrConditions = new String[3];
	}	
	
	public void setCountryhubcode(String countryhubCode) {
		this.setField(0, countryhubCode);
	}

	public String getCountryhubcode() {
		return this.getField(0);
	}

	public void setCityname(String cityName) {
		this.setField(1, cityName);
	}

	public String getCityname() {
		return this.getField(1);
	}

}
