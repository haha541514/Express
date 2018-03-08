package kyle.leis.es.company.customer.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class MaxsamelevelcustomerCondition extends GeneralCondition {
	public MaxsamelevelcustomerCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setCocodeparent(String cocodeparent) {
		this.setField(0, cocodeparent);
	}

	public String getCocodeparent() {
		return this.getField(0);
	}

}
