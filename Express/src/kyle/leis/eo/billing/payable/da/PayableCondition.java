package kyle.leis.eo.billing.payable.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class PayableCondition extends GeneralCondition {
	public PayableCondition() {
		m_astrConditions = new String[12];
	}	
	
	public void setCwcode(String cwCode) {
		this.setField(0, cwCode);
	}

	public String getCwcode() {
		return this.getField(0);
	}

	public void setPyid(String pyId) {
		this.setField(1, pyId);
	}

	public String getPyid() {
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

	public void setCocode(String coCode) {
		this.setField(5, coCode);
	}

	public String getCocode() {
		return this.getField(5);
	}

	public void setStartpyoccurdate(String StartPyOccurdate) {
		this.setField(6, StartPyOccurdate);
	}

	public String getStartpyoccurdate() {
		return this.getField(6);
	}

	public void setEndpyoccurdate(String EndPyOccurdate) {
		this.setField(7, EndPyOccurdate);
	}

	public String getEndpyoccurdate() {
		return this.getField(7);
	}

	public void setFscode(String fsCode) {
		this.setField(8, fsCode);
	}

	public String getFscode() {
		return this.getField(8);
	}

	public void setFkcode(String fkCode) {
		this.setField(9, fkCode);
	}

	public String getFkcode() {
		return this.getField(9);
	}

	public void setBkcode(String bkCode) {
		this.setField(10, bkCode);
	}

	public String getBkcode() {
		return this.getField(10);
	}	
	
}
