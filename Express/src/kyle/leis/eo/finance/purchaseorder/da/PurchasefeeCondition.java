package kyle.leis.eo.finance.purchaseorder.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class PurchasefeeCondition extends GeneralCondition {
	public PurchasefeeCondition() {
		m_astrConditions = new String[1];
	}	
	
	public void setPoid(String poId) {
		this.setField(0, poId);
	}

	public String getPoid() {
		return this.getField(0);
	}

}
