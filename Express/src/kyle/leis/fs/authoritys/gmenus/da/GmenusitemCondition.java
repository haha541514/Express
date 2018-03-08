package kyle.leis.fs.authoritys.gmenus.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class GmenusitemCondition extends GeneralCondition {
	public GmenusitemCondition() {
		m_astrConditions = new String[7];
	}	
	
	public void setGmname(String gmName) {
		this.setField(0, gmName);
	}

	public String getGmname() {
		return this.getField(0);
	}

	public void setGmcode(String gmCode) {
		this.setField(1, gmCode);
	}

	public String getGmcode() {
		return this.getField(1);
	}

	public void setGmstructurecode(String gmStructurecode) {
		this.setField(2, gmStructurecode);
	}

	public String getGmstructurecode() {
		return this.getField(2);
	}

	public void setGmgroupcode(String gmGroupcode) {
		this.setField(3, gmGroupcode);
	}

	public String getGmgroupcode() {
		return this.getField(3);
	}

	public void setGmlevel(String gmLevel) {
		this.setField(4, gmLevel);
	}

	public String getGmlevel() {
		return this.getField(4);
	}
	public void setIskcode(String iskcode) {
		this.setField(5, iskcode);
	}

	public String getIskcode() {
		return this.getField(5);
	}
}
