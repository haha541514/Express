package kyle.leis.eo.finance.purchaseorder.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class PurchasepayableCondition extends GeneralCondition {
	public PurchasepayableCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setCwcode(String cwCode) {
		this.setField(0, cwCode);
	}

	public String getCwcode() {
		return this.getField(0);
	}

	public void setFkcode(String fkCode) {
		this.setField(1, fkCode);
	}

	public String getFkcode() {
		return this.getField(1);
	}

}
