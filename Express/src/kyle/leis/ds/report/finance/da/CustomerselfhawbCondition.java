package kyle.leis.ds.report.finance.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class CustomerselfhawbCondition extends GeneralCondition {
	public CustomerselfhawbCondition() {
		m_astrConditions = new String[13];
	}	
	
	public void setCwcustomerewbcode(String cwcustomerewbcode) {
		this.setField(0, cwcustomerewbcode);
	}

	public String getCwcustomerewbcode() {
		return this.getField(0);
	}

	public void setCwserverewbcode(String cwserverewbcode) {
		this.setField(1, cwserverewbcode);
	}

	public String getCwserverewbcode() {
		return this.getField(1);
	}

	public void setStartsignindate(String startsignindate) {
		this.setField(2, startsignindate);
	}

	public String getStartsignindate() {
		return this.getField(2);
	}

	public void setEndsignindate(String Endsignindate) {
		this.setField(3, Endsignindate);
	}

	public String getEndsignindate() {
		return this.getField(3);
	}

	public void setDtcode(String dtcode) {
		this.setField(4, dtcode);
	}

	public String getDtcode() {
		return this.getField(4);
	}

	public void setCtcode(String ctcode) {
		this.setField(5, ctcode);
	}

	public String getCtcode() {
		return this.getField(5);
	}

	public void setIncustomerewbcode(String InCustomerewbcode) {
		this.setField(6, InCustomerewbcode);
	}

	public String getIncustomerewbcode() {
		return this.getField(6);
	}
	public void setInserverewbcode(String InServerewbcode) {
		this.setField(7, InServerewbcode);
	}

	public String getInserverewbcode() {
		return this.getField(7);
	}

	public void setCocodecustomer(String cocodecustomer) {
		this.setField(8, cocodecustomer);
	}

	public String getCocodecustomer() {
		return this.getField(8);
	}

	public void setStartsignoutdate(String startsignoutdate) {
		this.setField(9, startsignoutdate);
	}

	public String getStartsignoutdate() {
		return this.getField(9);
	}

	public void setEndsignoutdate(String Endsignoutdate) {
		this.setField(10, Endsignoutdate);
	}

	public String getEndsignoutdate() {
		return this.getField(10);
	}

	public void setEestructurecode(String eeStructurecode) {
		this.setField(11, eeStructurecode);
	}

	public String getEestructurecode() {
		return this.getField(11);
	}

}
