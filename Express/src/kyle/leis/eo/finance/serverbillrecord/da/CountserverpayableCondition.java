package kyle.leis.eo.finance.serverbillrecord.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class CountserverpayableCondition extends GeneralCondition {
	public CountserverpayableCondition() {
		m_astrConditions = new String[1];
	}	
	
	public void setSwbcode(String swbCode) {
		this.setField(0, swbCode);
	}

	public String getSwbcode() {
		return this.getField(0);
	}

}
