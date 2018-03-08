package kyle.leis.es.price.pricegroup.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class CustomerpricegroupvalueCondition extends GeneralCondition {
	public CustomerpricegroupvalueCondition() {
		m_astrConditions = new String[3];
	}	
	
	public void setEpcode(String epCode) {
		this.setField(0, epCode);
	}

	public String getEpcode() {
		return this.getField(0);
	}

	public void setFkcode(String fkCode) {
		this.setField(1, fkCode);
	}

	public String getFkcode() {
		return this.getField(1);
	}

	public void setPgcode(String pgCode) {
		this.setField(2, pgCode);
	}

	public String getPgcode() {
		return this.getField(2);
	}

}
