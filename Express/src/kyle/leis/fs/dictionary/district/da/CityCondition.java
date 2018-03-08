package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class CityCondition extends GeneralCondition {
	public CityCondition() {
		m_astrConditions = new String[11];
	}	
	
	public void setCtcode(String ctCode) {
		this.setField(0, ctCode);
	}

	public String getCtcode() {
		return this.getField(0);
	}

	public void setCtsname(String ctSname) {
		this.setField(1, ctSname);
	}

	public String getCtsname() {
		return this.getField(1);
	}

	public void setCtname(String ctName) {
		this.setField(2, ctName);
	}

	public String getCtname() {
		return this.getField(2);
	}

	public void setCtename(String ctEname) {
		this.setField(3, ctEname);
	}

	public String getCtename() {
		return this.getField(3);
	}

	public void setCtstartpostcode(String ctStartpostcode) {
		this.setField(4, ctStartpostcode);
	}

	public String getCtstartpostcode() {
		return this.getField(4);
	}

	public void setCtendpostcode(String ctEndpostcode) {
		this.setField(5, ctEndpostcode);
	}

	public String getCtendpostcode() {
		return this.getField(5);
	}

	public void setDtcode(String dtCode) {
		this.setField(6, dtCode);
	}

	public String getDtcode() {
		return this.getField(6);
	}

	public void setDtname(String dtName) {
		this.setField(7, dtName);
	}

	public String getDtname() {
		return this.getField(7);
	}

	public void setStcode(String stCode) {
		this.setField(8, stCode);
	}

	public String getStcode() {
		return this.getField(8);
	}

	public void setStsname(String stSname) {
		this.setField(9, stSname);
	}

	public String getStsname() {
		return this.getField(9);
	}

}
