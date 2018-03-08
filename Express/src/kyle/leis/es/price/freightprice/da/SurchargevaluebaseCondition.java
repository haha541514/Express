package kyle.leis.es.price.freightprice.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class SurchargevaluebaseCondition extends GeneralCondition {
	public SurchargevaluebaseCondition() {
		m_astrConditions = new String[3];
	}	
	
	public void setEpcode(String epCode) {
		this.setField(0, epCode);
	}

	public String getEpcode() {
		return this.getField(0);
	}

	public void setSvid(String svId) {
		this.setField(1, svId);
	}

	public String getSvid() {
		return this.getField(1);
	}

	public void setFkcode(String fkCode) {
		this.setField(2, fkCode);
	}

	public String getFkcode() {
		return this.getField(2);
	}

}
