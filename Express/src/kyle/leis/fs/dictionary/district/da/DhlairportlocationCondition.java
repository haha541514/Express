package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class DhlairportlocationCondition extends GeneralCondition {
	public DhlairportlocationCondition() {
		m_astrConditions = new String[4];
	}	
	
	public void setAphubcode(String aphubcode) {
		this.setField(0, aphubcode);
	}

	public String getAphubcode() {
		return this.getField(0);
	}

	public void setApename(String apename) {
		this.setField(1, apename);
	}

	public String getApename() {
		return this.getField(1);
	}

	public void setApcname(String apcname) {
		this.setField(2, apcname);
	}

	public String getApcname() {
		return this.getField(2);
	}

}
