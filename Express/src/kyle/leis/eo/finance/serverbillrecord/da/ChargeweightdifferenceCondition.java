package kyle.leis.eo.finance.serverbillrecord.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ChargeweightdifferenceCondition extends GeneralCondition {
	public ChargeweightdifferenceCondition() {
		m_astrConditions = new String[10];
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

	public void setStartadddate(String StartAddDate) {
		this.setField(2, StartAddDate);
	}

	public String getStartadddate() {
		return this.getField(2);
	}

	public void setEndadddate(String EndAddDate) {
		this.setField(3, EndAddDate);
	}

	public String getEndadddate() {
		return this.getField(3);
	}

	public void setCocode(String coCode) {
		this.setField(4, coCode);
	}

	public String getCocode() {
		return this.getField(4);
	}

	public void setPkcode(String pkCode) {
		this.setField(5, pkCode);
	}

	public String getPkcode() {
		return this.getField(5);
	}

	public void setCtcode(String ctCode) {
		this.setField(6, ctCode);
	}

	public String getCtcode() {
		return this.getField(6);
	}

	public void setHwowninputcwauditsign(String hwOwnInputcwauditSign) {
		this.setField(7, hwOwnInputcwauditSign);
	}

	public String getHwowninputcwauditsign() {
		return this.getField(7);
	}

	public void setCustomerewbcode(String customerewbcode) {
		this.setField(8, customerewbcode);
	}

	public String getCustomerewbcode() {
		return this.getField(8);
	}

}
