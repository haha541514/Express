package kyle.leis.fs.dictionary.dictionarys.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class CustomscargoCondition extends GeneralCondition {
	public CustomscargoCondition() {
		m_astrConditions = new String[4];
	}	
	
	public void setCcename(String ccEname) {
		this.setField(0, ccEname);
	}

	public String getCcename() {
		return this.getField(0);
	}

	public void setCcname(String ccName) {
		this.setField(1, ccName);
	}

	public String getCcname() {
		return this.getField(1);
	}

	public void setCchscode(String ccHscode) {
		this.setField(2, ccHscode);
	}

	public String getCchscode() {
		return this.getField(2);
	}

}
