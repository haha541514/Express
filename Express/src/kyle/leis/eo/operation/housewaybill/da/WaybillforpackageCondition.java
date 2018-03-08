package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class WaybillforpackageCondition extends GeneralCondition {
	public WaybillforpackageCondition() {
		m_astrConditions = new String[14];
	}	
	
	public void setBwcode(String bwCode) {
		this.setField(0, bwCode);
	}

	public String getBwcode() {
		return this.getField(0);
	}

	public void setBwlabelcode(String bwLabelcode) {
		this.setField(1, bwLabelcode);
	}

	public String getBwlabelcode() {
		return this.getField(1);
	}

	public void setCwcustomerewbcode(String cwCustomerewbcode) {
		this.setField(2, cwCustomerewbcode);
	}

	public String getCwcustomerewbcode() {
		return this.getField(2);
	}

	public void setCwserverewbcode(String cwServerewbcode) {
		this.setField(3, cwServerewbcode);
	}

	public String getCwserverewbcode() {
		return this.getField(3);
	}

	public void setCwewbcode(String cwEwbcode) {
		this.setField(4, cwEwbcode);
	}

	public String getCwewbcode() {
		return this.getField(4);
	}

	public void setPkcode(String pkCode) {
		this.setField(5, pkCode);
	}

	public String getPkcode() {
		return this.getField(5);
	}

	public void setZnvname(String znvName) {
		this.setField(6, znvName);
	}

	public String getZnvname() {
		return this.getField(6);
	}

	public void setSchncode(String schnCode) {
		this.setField(7, schnCode);
	}

	public String getSchncode() {
		return this.getField(7);
	}

	public void setIncwscode(String Incwscode) {
		this.setField(8, Incwscode);
	}

	public String getIncwscode() {
		return this.getField(8);
	}

	public void setDtcode(String dtCode) {
		this.setField(9, dtCode);
	}

	public String getDtcode() {
		return this.getField(9);
	}

	public void setCcocode(String ccoCode) {
		this.setField(10, ccoCode);
	}

	public String getCcocode() {
		return this.getField(10);
	}

	public void setScocode(String scoCode) {
		this.setField(11, scoCode);
	}

	public String getScocode() {
		return this.getField(11);
	}

	public void setCwcode(String cwCode) {
		this.setField(12, cwCode);
	}

	public String getCwcode() {
		return this.getField(12);
	}

}
