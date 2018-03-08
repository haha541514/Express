package kyle.leis.es.company.customer.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class CustomerapiwebtokenCondition extends GeneralCondition {
	public CustomerapiwebtokenCondition() {
		m_astrConditions = new String[7];
	}	
	
	public void setCawtcawtid(String cawtCawtId) {
		this.setField(0, cawtCawtId);
	}

	public String getCawtcawtid() {
		return this.getField(0);
	}

	public void setCococode(String coCoCode) {
		this.setField(1, coCoCode);
	}

	public String getCococode() {
		return this.getField(1);
	}

	public void setCapwtcapwtcode(String capwtCapwtCode) {
		this.setField(2, capwtCapwtCode);
	}

	public String getCapwtcapwtcode() {
		return this.getField(2);
	}

	public void setCawtcawtusername(String cawtCawtUsername) {
		this.setField(3, cawtCawtUsername);
	}

	public String getCawtcawtusername() {
		return this.getField(3);
	}

	public void setCawtcawttoken(String cawtCawtToken) {
		this.setField(4, cawtCawtToken);
	}

	public String getCawtcawttoken() {
		return this.getField(4);
	}
	
	public void setCawtcawtpassword(String cawtCawtPassword) {
		this.setField(5, cawtCawtPassword);
	}

	public String getCawtcawtpassword() {
		return this.getField(5);
	}

}
