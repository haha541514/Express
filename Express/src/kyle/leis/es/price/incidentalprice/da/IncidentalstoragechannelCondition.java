package kyle.leis.es.price.incidentalprice.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class IncidentalstoragechannelCondition extends GeneralCondition {
	public IncidentalstoragechannelCondition() {
		m_astrConditions = new String[4];
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

	public void setChncode(String chnCode) {
		this.setField(2, chnCode);
	}

	public String getChncode() {
		return this.getField(2);
	}

}
