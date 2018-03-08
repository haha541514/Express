package kyle.leis.es.company.customer.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class CusoperationCondition extends GeneralCondition {
	public CusoperationCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setCmcocode(String cmcocode) {
		this.setField(0, cmcocode);
	}

	public String getCmcocode() {
		return this.getField(0);
	}

}
