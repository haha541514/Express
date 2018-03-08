package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class DhldistrictCondition extends GeneralCondition {
	public DhldistrictCondition() {
		m_astrConditions = new String[4];
	}	
	
	public void setDdnationcode(String ddnationcode) {
		this.setField(0, ddnationcode);
	}

	public String getDdnationcode() {
		return this.getField(0);
	}

	public void setStartpostcode(String Startpostcode) {
		this.setField(1, Startpostcode);
	}

	public String getStartpostcode() {
		return this.getField(1);
	}

	public void setEndpostcode(String Endpostcode) {
		this.setField(2, Endpostcode);
	}

	public String getEndpostcode() {
		return this.getField(2);
	}

}
