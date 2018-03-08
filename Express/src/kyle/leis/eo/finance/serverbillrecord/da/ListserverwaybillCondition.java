package kyle.leis.eo.finance.serverbillrecord.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ListserverwaybillCondition extends GeneralCondition {
	public ListserverwaybillCondition() {
		m_astrConditions = new String[1];
	}	
	
	public void setSbrid(String sbrId) {
		this.setField(0, sbrId);
	}

	public String getSbrid() {
		return this.getField(0);
	}

}
