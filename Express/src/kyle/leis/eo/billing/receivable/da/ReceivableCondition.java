package kyle.leis.eo.billing.receivable.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ReceivableCondition extends GeneralCondition {
	public ReceivableCondition() {
		m_astrConditions = new String[9];
	}	
	
	public void setCwcode(String cwCode) {
		this.setField(0, cwCode);
	}

	public String getCwcode() {
		return this.getField(0);
	}

	public void setRvid(String rvId) {
		this.setField(1, rvId);
	}

	public String getRvid() {
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

	public void setStartoccurdate(String StartOccurdate) {
		this.setField(5, StartOccurdate);
	}

	public String getStartoccurdate() {
		return this.getField(5);
	}

	public void setEndoccurdate(String EndOccurdate) {
		this.setField(6, EndOccurdate);
	}

	public String getEndoccurdate() {
		return this.getField(6);
	}

	public void setFscode(String fsCode) {
		this.setField(7, fsCode);
	}

	public String getFscode() {
		return this.getField(7);
	}

	public void setFkcode(String fkCode) {
		this.setField(8, fkCode);
	}

	public String getFkcode() {
		return this.getField(8);
	}

}
