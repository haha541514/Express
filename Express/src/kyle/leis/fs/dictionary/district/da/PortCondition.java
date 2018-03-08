package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class PortCondition extends GeneralCondition {
	public PortCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setPtename(String ptename) {
		this.setField(0, ptename);
	}

	public String getPtename() {
		return this.getField(0);
	}

	public void setPtcname(String ptcname) {
		this.setField(1, ptcname);
	}

	public String getPtcname() {
		return this.getField(1);
	}

}
