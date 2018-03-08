package kyle.leis.es.company.predicttemplate.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class PredictdicmappingCondition extends GeneralCondition {
	public PredictdicmappingCondition() {
		m_astrConditions = new String[5];
	}	
	
	public void setPotid(String potId) {
		this.setField(0, potId);
	}

	public String getPotid() {
		return this.getField(0);
	}

	public void setDmkcode(String dmkcode) {
		this.setField(1, dmkcode);
	}

	public String getDmkcode() {
		return this.getField(1);
	}

	public void setIspotid(String IspotId) {
		this.setField(2, IspotId);
	}

	public String getIspotid() {
		return this.getField(2);
	}

	public void setPodmoriginvalue(String podmOriginvalue) {
		this.setField(3, podmOriginvalue);
	}

	public String getPodmoriginvalue() {
		return this.getField(3);
	}

}
