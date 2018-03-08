package kyle.leis.fs.dictionary.cargokind.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class CargokindCondition extends GeneralCondition {
	public CargokindCondition() {
		m_astrConditions = new String[7];
	}	
	
	public void setCgkcode(String cgkCode) {
		this.setField(0, cgkCode);
	}

	public String getCgkcode() {
		return this.getField(0);
	}

	public void setCgkname(String cgkName) {
		this.setField(1, cgkName);
	}

	public String getCgkname() {
		return this.getField(1);
	}

	public void setCgkename(String cgkEname) {
		this.setField(2, cgkEname);
	}

	public String getCgkename() {
		return this.getField(2);
	}

	public void setCgkbatterysign(String cgkBatterysign) {
		this.setField(3, cgkBatterysign);
	}

	public String getCgkbatterysign() {
		return this.getField(3);
	}

	public void setSscode(String ssCode) {
		this.setField(4, ssCode);
	}

	public String getSscode() {
		return this.getField(4);
	}

	public void setSsname(String ssName) {
		this.setField(5, ssName);
	}

	public String getSsname() {
		return this.getField(5);
	}

}
