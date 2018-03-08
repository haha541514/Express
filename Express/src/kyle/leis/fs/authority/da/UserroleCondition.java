package kyle.leis.fs.authority.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class UserroleCondition extends GeneralCondition {
	public UserroleCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setIsk_code(String strIsk_code) {
		this.setField(0, strIsk_code);
	}

	public String getIsk_code() {
		return this.getField(0);
	}


	public void setUr_usercode(String strUr_usercode) {
		this.setField(1, strUr_usercode);
	}

	public String getUr_usercode() {
		return this.getField(1);
	}


}
