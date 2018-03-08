package kyle.leis.eo.finance.serverbillrecord.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class DifferenceincidentalsCondition extends GeneralCondition {
	public DifferenceincidentalsCondition() {
		m_astrConditions = new String[5];
	}	
	
	public void setSwbserverewbcode(String swbServerewbcode) {
		this.setField(0, swbServerewbcode);
	}

	public String getSwbserverewbcode() {
		return this.getField(0);
	}

	public void setSwbcustomerewbcode(String swbCustomerEwbcode) {
		this.setField(1, swbCustomerEwbcode);
	}

	public String getSwbcustomerewbcode() {
		return this.getField(1);
	}

	public void setFkcode(String fkCode) {
		this.setField(2, fkCode);
	}

	public String getFkcode() {
		return this.getField(2);
	}

	public void setSbrid(String sbrId) {
		this.setField(3, sbrId);
	}

	public String getSbrid() {
		return this.getField(3);
	}

}
