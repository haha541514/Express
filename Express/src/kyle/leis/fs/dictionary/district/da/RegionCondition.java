package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class RegionCondition extends GeneralCondition {
	public RegionCondition() {
		m_astrConditions = new String[5];
	}	
	
	public void setRgcode(String rgCode) {
		this.setField(0, rgCode);
	}

	public String getRgcode() {
		return this.getField(0);
	}

	public void setRgname(String rgName) {
		this.setField(1, rgName);
	}

	public String getRgname() {
		return this.getField(1);
	}

	public void setRgename(String rgEname) {
		this.setField(2, rgEname);
	}

	public String getRgename() {
		return this.getField(2);
	}

	public void setCtcode(String ctCode) {
		this.setField(3, ctCode);
	}

	public String getCtcode() {
		return this.getField(3);
	}

}
