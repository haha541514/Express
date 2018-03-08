package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class CountrypostcodecountCondition extends GeneralCondition {
	public CountrypostcodecountCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setDd_nationcode(String dd_nationcode) {
		this.setField(0, dd_nationcode);
	}

	public String getDd_nationcode() {
		return this.getField(0);
	}

}
