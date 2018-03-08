package kyle.leis.fs.dictionary.customscargo.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class CustomscargoCondition extends GeneralCondition {
	public CustomscargoCondition() {
		m_astrConditions = new String[6];
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

	public void setCccodes(String ccCodes) {
		this.setField(3, ccCodes);
	}

	public String getCccodes() {
		return this.getField(3);
	}

	public void setCheckename(String checkEname) {
		this.setField(4, checkEname);
	}

	public String getCheckename() {
		return this.getField(4);
	}

}
