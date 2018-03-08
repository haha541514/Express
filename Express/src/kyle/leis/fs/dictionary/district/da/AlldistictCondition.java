package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class AlldistictCondition extends GeneralCondition {
	public AlldistictCondition() {
		m_astrConditions = new String[6];
	}	
	
	public void setDkcode(String dkcode) {
		this.setField(0, dkcode);
	}

	public String getDkcode() {
		return this.getField(0);
	}

	public void setStatename(String statename) {
		this.setField(1, statename);
	}

	public String getStatename() {
		return this.getField(1);
	}

	public void setDtname(String dtname) {
		this.setField(2, dtname);
	}

	public String getDtname() {
		return this.getField(2);
	}

	public void setPostcode(String postcode) {
		this.setField(3, postcode);
	}

	public String getPostcode() {
		return this.getField(3);
	}
	
	public void setLikepostcode(String postcode) {
		this.setField(4, postcode);
	}

	public String getLikepostcode() {
		return this.getField(4);
	}	
}
