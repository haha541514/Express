package kyle.leis.es.businessrule.productrule.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class CorporationproductruleCondition extends GeneralCondition {
	public CorporationproductruleCondition() {
		m_astrConditions = new String[1];
	}	
	
	public void setBrid(String brId) {
		this.setField(0, brId);
	}

	public String getBrid() {
		return this.getField(0);
	}

}
