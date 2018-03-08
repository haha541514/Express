package kyle.leis.es.company.predicttemplate.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class PredicttemplatevalueCondition extends GeneralCondition {
	public PredicttemplatevalueCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setPotid(String potId) {
		this.setField(0, potId);
	}

	public String getPotid() {
		return this.getField(0);
	}

}
