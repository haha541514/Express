package kyle.leis.es.businessrule.productrule.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ProductcswbCondition extends GeneralCondition {
	public ProductcswbCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setBrid(String brId) {
		this.setField(0, brId);
	}

	public String getBrid() {
		return this.getField(0);
	}

	public void setCocode(String coCode) {
		this.setField(1, coCode);
	}

	public String getCocode() {
		return this.getField(1);
	}

}
