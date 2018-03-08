package kyle.leis.es.company.customer.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class CustomerapiwebtypeCondition extends GeneralCondition {
	public CustomerapiwebtypeCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setCapwtcapwtcode(String capwtCapwtCode) {
		this.setField(0, capwtCapwtCode);
	}

	public String getCapwtcapwtcode() {
		return this.getField(0);
	}

}
