package kyle.leis.es.price.currency.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class CurrencyvalueCondition extends GeneralCondition {
	public CurrencyvalueCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setEpcode(String epCode) {
		this.setField(0, epCode);
	}

	public String getEpcode() {
		return this.getField(0);
	}

	public void setCvid(String cvId) {
		this.setField(1, cvId);
	}

	public String getCvid() {
		return this.getField(1);
	}

}
