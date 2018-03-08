package kyle.leis.es.businessrule.productrule.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class DistrictproductruleCondition extends GeneralCondition {
	public DistrictproductruleCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setBrid(String brId) {
		this.setField(0, brId);
	}

	public String getBrid() {
		return this.getField(0);
	}

	public void setDtcode(String dtCode) {
		this.setField(1, dtCode);
	}

	public String getDtcode() {
		return this.getField(1);
	}

}
