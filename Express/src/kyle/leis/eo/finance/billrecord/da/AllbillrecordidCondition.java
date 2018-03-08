package kyle.leis.eo.finance.billrecord.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class AllbillrecordidCondition extends GeneralCondition {
	public AllbillrecordidCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setCwcwcode(String cwcwcode) {
		this.setField(0, cwcwcode);
	}

	public String getCwcwcode() {
		return this.getField(0);
	}

}
