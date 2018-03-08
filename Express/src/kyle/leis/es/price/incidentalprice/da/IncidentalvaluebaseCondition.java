package kyle.leis.es.price.incidentalprice.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class IncidentalvaluebaseCondition extends GeneralCondition {
	public IncidentalvaluebaseCondition() {
		m_astrConditions = new String[3];
	}	
	
	public void setEpcode(String epCode) {
		this.setField(0, epCode);
	}

	public String getEpcode() {
		return this.getField(0);
	}

	public void setIpvid(String ipvId) {
		this.setField(1, ipvId);
	}

	public String getIpvid() {
		return this.getField(1);
	}

	public void setFkcode(String fkCode) {
		this.setField(2, fkCode);
	}

	public String getFkcode() {
		return this.getField(2);
	}

}
