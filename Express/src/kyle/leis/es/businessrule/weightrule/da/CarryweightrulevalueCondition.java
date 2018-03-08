package kyle.leis.es.businessrule.weightrule.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class CarryweightrulevalueCondition extends GeneralCondition {
	public CarryweightrulevalueCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setCwrvid(String cwrvId) {
		this.setField(0, cwrvId);
	}

	public String getCwrvid() {
		return this.getField(0);
	}

	public void setBrid(String brId) {
		this.setField(1, brId);
	}

	public String getBrid() {
		return this.getField(1);
	}

}
