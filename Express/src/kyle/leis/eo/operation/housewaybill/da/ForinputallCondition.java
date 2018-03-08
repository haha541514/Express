package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ForinputallCondition extends GeneralCondition {
	public ForinputallCondition() {
		m_astrConditions = new String[6];
	}	
	
	public void setCustomerewbcode(String strCustomerewbcode) {
		this.setField(0, strCustomerewbcode);
	}

	public String getCustomerewbcode() {
		return this.getField(0);
	}


	public void setServerewbcode(String strServerewbcode) {
		this.setField(1, strServerewbcode);
	}

	public String getServerewbcode() {
		return this.getField(1);
	}


	public void setEwbcode(String strEwbcode) {
		this.setField(2, strEwbcode);
	}

	public String getEwbcode() {
		return this.getField(2);
	}

	public void setCwcode(String strCwcode) {
		this.setField(3, strCwcode);
	}

	public String getCwcode() {
		return this.getField(3);
	}
	
	public void setCwcuscocode(String strCwcuscocode) {
		this.setField(4, strCwcuscocode);
	}

	public String getCwcuscocode() {
		return this.getField(4);
	}
	
	public void setNotincwscode(String strNotincwscode) {
		this.setField(5, strNotincwscode);
	}

	public String getNotincwscode() {
		return this.getField(5);
	}		
}
