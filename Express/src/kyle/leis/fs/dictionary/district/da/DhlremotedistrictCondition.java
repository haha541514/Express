package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class DhlremotedistrictCondition extends GeneralCondition {
	public DhlremotedistrictCondition() {
		m_astrConditions = new String[4];
	}	
	
	public void setDrdnationcode(String DrdNationcode) {
		this.setField(0, DrdNationcode);
	}

	public String getDrdnationcode() {
		return this.getField(0);
	}

	public void setDrdcityname(String DrdCityname) {
		this.setField(1, DrdCityname);
	}

	public String getDrdcityname() {
		return this.getField(1);
	}

	public void setDrdpostcode(String DrdPostcode) {
		this.setField(2, DrdPostcode);
	}

	public String getDrdpostcode() {
		return this.getField(2);
	}

	public void setDrdnationname(String DrdNationname) {
		this.setField(3, DrdNationname);
	}

	public String getDrdnationname() {
		return this.getField(3);
	}

}
