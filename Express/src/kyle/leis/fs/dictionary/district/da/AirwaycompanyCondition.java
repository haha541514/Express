package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class AirwaycompanyCondition extends GeneralCondition {
	public AirwaycompanyCondition() {
		m_astrConditions = new String[5];
	}	
	
	public void setAchubcode(String achubcode) {
		this.setField(0, achubcode);
	}

	public String getAchubcode() {
		return this.getField(0);
	}

	public void setActhreehubcode(String acthreehubcode) {
		this.setField(1, acthreehubcode);
	}

	public String getActhreehubcode() {
		return this.getField(1);
	}

	public void setAcename(String acename) {
		this.setField(2, acename);
	}

	public String getAcename() {
		return this.getField(2);
	}

	public void setAccname(String accname) {
		this.setField(3, accname);
	}

	public String getAccname() {
		return this.getField(3);
	}

}
