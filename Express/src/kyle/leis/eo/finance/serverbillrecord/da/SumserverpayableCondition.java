package kyle.leis.eo.finance.serverbillrecord.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class SumserverpayableCondition extends GeneralCondition {
	public SumserverpayableCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setSprid(String sprId) {
		this.setField(0, sprId);
	}

	public String getSprid() {
		return this.getField(0);
	}

}
