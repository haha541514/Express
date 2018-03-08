package kyle.leis.es.price.expressprice.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class PriceenterpriseCondition extends GeneralCondition {
	public PriceenterpriseCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setEpcode(String epCode) {
		this.setField(0, epCode);
	}

	public String getEpcode() {
		return this.getField(0);
	}

}
