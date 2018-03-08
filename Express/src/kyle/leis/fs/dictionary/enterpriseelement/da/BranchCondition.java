package kyle.leis.fs.dictionary.enterpriseelement.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class BranchCondition extends GeneralCondition {
	public BranchCondition() {
		m_astrConditions = new String[8];
	}	
	
	public void setBreecode(String Breecode) {
		this.setField(0, Breecode);
	}

	public String getBreecode() {
		return this.getField(0);
	}

	public void setEeeecode(String Eeeecode) {
		this.setField(1, Eeeecode);
	}

	public String getEeeecode() {
		return this.getField(1);
	}

	public void setBropidmanager(String Bropidmanager) {
		this.setField(2, Bropidmanager);
	}

	public String getBropidmanager() {
		return this.getField(2);
	}

	public void setBropidcustomerservice(String Bropidcustomerservice) {
		this.setField(3, Bropidcustomerservice);
	}

	public String getBropidcustomerservice() {
		return this.getField(3);
	}

	public void setBropiddunner(String Bropiddunner) {
		this.setField(4, Bropiddunner);
	}

	public String getBropiddunner() {
		return this.getField(4);
	}

	public void setBropidsaler(String Bropidsaler) {
		this.setField(5, Bropidsaler);
	}

	public String getBropidsaler() {
		return this.getField(5);
	}

	public void setEekcode(String Eekcode) {
		this.setField(6, Eekcode);
	}

	public String getEekcode() {
		return this.getField(6);
	}

}
