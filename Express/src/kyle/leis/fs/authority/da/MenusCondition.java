package kyle.leis.fs.authority.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class MenusCondition extends GeneralCondition {
	public MenusCondition() {
		m_astrConditions = new String[3];
	}	
	
	public void setGmname(String strGmname) {
		this.setField(0, strGmname);
	}

	public String getGmname() {
		return this.getField(0);
	}
	
	public void setIskcode(String strIskcode) {
		this.setField(1, strIskcode);
	}

	public String getIskcode() {
		return this.getField(1);
	}	
}
