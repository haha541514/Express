package kyle.leis.eo.operation.transportwaybill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class StatistictcwCondition extends GeneralCondition {
	public StatistictcwCondition() {
		m_astrConditions = new String[9];
	}	
	
	public void setTwbid(String twbId) {
		this.setField(0, twbId);
	}

	public String getTwbid() {
		return this.getField(0);
	}

	public void setBwcode(String bwCode) {
		this.setField(1, bwCode);
	}

	public String getBwcode() {
		return this.getField(1);
	}

	public void setCwcode(String cwCode) {
		this.setField(2, cwCode);
	}

	public String getCwcode() {
		return this.getField(2);
	}

	public void setCwewbcode(String cwEwbcode) {
		this.setField(3, cwEwbcode);
	}

	public String getCwewbcode() {
		return this.getField(3);
	}

	public void setCwcustomerewbcode(String cwCustomerewbcode) {
		this.setField(4, cwCustomerewbcode);
	}

	public String getCwcustomerewbcode() {
		return this.getField(4);
	}

	public void setInchncodesupplier(String InChncodeSupplier) {
		this.setField(5, InChncodeSupplier);
	}

	public String getInchncodesupplier() {
		return this.getField(5);
	}

	public void setIsnullsign(String Isnullsign) {
		this.setField(6, Isnullsign);
	}

	public String getIsnullsign() {
		return this.getField(6);
	}

	public void setWbtscode(String wbtscode) {
		this.setField(7, wbtscode);
	}

	public String getWbtscode() {
		return this.getField(7);
	}

}
