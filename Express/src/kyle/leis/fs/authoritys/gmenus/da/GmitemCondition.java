package kyle.leis.fs.authoritys.gmenus.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class GmitemCondition extends GeneralCondition {
	public GmitemCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setGmcode(String gmCode) {
		this.setField(0, gmCode);
	}

	public String getGmcode() {
		return this.getField(0);
	}

}
