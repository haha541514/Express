package kyle.leis.fs.authoritys.userrole.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class UserroleCondition extends GeneralCondition {
	public UserroleCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setIsk_code(String isk_code) {
		this.setField(0, isk_code);
	}

	public String getIsk_code() {
		return this.getField(0);
	}

	public void setUrusercode(String urUsercode) {
		this.setField(1, urUsercode);
	}

	public String getUrusercode() {
		return this.getField(1);
	}

}
