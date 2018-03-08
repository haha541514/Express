package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class UpsremotedistrictCondition extends GeneralCondition{
	public UpsremotedistrictCondition() {
		m_astrConditions = new String[5];
	}	
	
	public void setNationcode(String Nationcode) {
		this.setField(0, Nationcode);
	}

	public String getNationcode() {
		return this.getField(0);
	}

	public void setUrdcityname(String urdCityname) {
		this.setField(1, urdCityname);
	}

	public String getUrdcityname() {
		return this.getField(1);
	}

	public void setPostcode(String Postcode) {
		this.setField(2, Postcode);
	}

	public String getPostcode() {
		return this.getField(2);
	}

	public void setPostcode2(String Postcode2) {
		this.setField(3, Postcode2);
	}

	public String getPostcode2() {
		return this.getField(3);
	}

}
