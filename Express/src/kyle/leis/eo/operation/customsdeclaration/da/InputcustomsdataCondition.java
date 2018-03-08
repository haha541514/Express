package kyle.leis.eo.operation.customsdeclaration.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class InputcustomsdataCondition extends GeneralCondition {
	public InputcustomsdataCondition() {
		m_astrConditions = new String[14];
	}	
	
	public void setStartdate(String StartDate) {
		this.setField(0, StartDate);
	}

	public String getStartdate() {
		return this.getField(0);
	}

	public void setEnddate(String EndDate) {
		this.setField(1, EndDate);
	}

	public String getEnddate() {
		return this.getField(1);
	}

	public void setCwcode(String cwcode) {
		this.setField(2, cwcode);
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

	public void setCwserverewbcode(String cwServerewbcode) {
		this.setField(4, cwServerewbcode);
	}

	public String getCwserverewbcode() {
		return this.getField(4);
	}

	public void setChncodesupplier(String chncodeSupplier) {
		this.setField(5, chncodeSupplier);
	}

	public String getChncodesupplier() {
		return this.getField(5);
	}

	public void setBwcodedeparture(String bwcodeDeparture) {
		this.setField(6, bwcodeDeparture);
	}

	public String getBwcodedeparture() {
		return this.getField(6);
	}

	public void setCtcode(String ctCode) {
		this.setField(7, ctCode);
	}

	public String getCtcode() {
		return this.getField(7);
	}

	public void setCdlabelcode(String cdLabelcode) {
		this.setField(8, cdLabelcode);
	}

	public String getCdlabelcode() {
		return this.getField(8);
	}

	public void setCpbaglabelcode(String cpBaglabelcode) {
		this.setField(9, cpBaglabelcode);
	}

	public String getCpbaglabelcode() {
		return this.getField(9);
	}

	public void setTwblabelcode(String twbLabelcode) {
		this.setField(10, twbLabelcode);
	}

	public String getTwblabelcode() {
		return this.getField(10);
	}

	public void setCocodecustomer(String coCodecustomer) {
		this.setField(11, coCodecustomer);
	}

	public String getCocodecustomer() {
		return this.getField(11);
	}

	public void setEestructurecode(String eestructurecode) {
		this.setField(12, eestructurecode);
	}

	public String getEestructurecode() {
		return this.getField(12);
	}

}
