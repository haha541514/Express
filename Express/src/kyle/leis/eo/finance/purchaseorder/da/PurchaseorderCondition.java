package kyle.leis.eo.finance.purchaseorder.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class PurchaseorderCondition extends GeneralCondition {
	public PurchaseorderCondition() {
		m_astrConditions = new String[4];
	}	
	
	public void setPoid(String poId) {
		this.setField(0, poId);
	}

	public String getPoid() {
		return this.getField(0);
	}

	public void setSscode(String ssCode) {
		this.setField(1, ssCode);
	}

	public String getSscode() {
		return this.getField(1);
	}

	public void setStartdate(String StartDate) {
		this.setField(2, StartDate);
	}

	public String getStartdate() {
		return this.getField(2);
	}

	public void setEnddate(String EndDate) {
		this.setField(3, EndDate);
	}

	public String getEnddate() {
		return this.getField(3);
	}

}
