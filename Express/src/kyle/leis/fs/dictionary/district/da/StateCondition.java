package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class StateCondition extends GeneralCondition {
	public StateCondition() {
		m_astrConditions = new String[7];
	}	
	
	public void setStcode(String stCode) {
		this.setField(0, stCode);
	}

	public String getStcode() {
		return this.getField(0);
	}

	public void setStsname(String stSname) {
		this.setField(1, stSname);
	}

	public String getStsname() {
		return this.getField(1);
	}

	public void setStname(String stName) {
		this.setField(2, stName);
	}

	public String getStname() {
		return this.getField(2);
	}

	public void setStename(String stEname) {
		this.setField(3, stEname);
	}

	public String getStename() {
		return this.getField(3);
	}

	public void setDtcode(String dtCode) {
		this.setField(4, dtCode);
	}

	public String getDtcode() {
		return this.getField(4);
	}

	public void setDtname(String dtName) {
		this.setField(5, dtName);
	}

	public String getDtname() {
		return this.getField(5);
	}

}
