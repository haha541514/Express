package kyle.leis.es.systemproperty.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class PreictmappingCondition extends GeneralCondition {
	public PreictmappingCondition() {
		m_astrConditions = new String[4];
	}	
	
	public void setDmkcode(String dmkcode) {
		this.setField(0, dmkcode);
	}

	public String getDmkcode() {
		return this.getField(0);
	}

	public void setPtccode(String ptccode) {
		this.setField(1, ptccode);
	}

	public String getPtccode() {
		return this.getField(1);
	}

	public void setOriginvalue(String originvalue) {
		this.setField(2, originvalue);
	}

	public String getOriginvalue() {
		return this.getField(2);
	}

}
