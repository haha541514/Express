package kyle.leis.eo.finance.serverbillrecord.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class DifferencedetailCondition extends GeneralCondition {
	public DifferencedetailCondition() {
		m_astrConditions = new String[4];
	}	
	
	public void setSwbcode(String swbCode) {
		this.setField(0, swbCode);
	}

	public String getSwbcode() {
		return this.getField(0);
	}

	public void setSwbserverewbcode(String swbServerEwbcode) {
		this.setField(1, swbServerEwbcode);
	}

	public String getSwbserverewbcode() {
		return this.getField(1);
	}

	public void setSbrid(String sbrId) {
		this.setField(2, sbrId);
	}

	public String getSbrid() {
		return this.getField(2);
	}

}
