package kyle.leis.es.company.predicttemplate.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class PredictordertemplateCondition extends GeneralCondition {
	public PredictordertemplateCondition() {
		m_astrConditions = new String[4];
	}	
	
	public void setPotid(String potId) {
		this.setField(0, potId);
	}

	public String getPotid() {
		return this.getField(0);
	}

	public void setCocode(String cocode) {
		this.setField(1, cocode);
	}

	public String getCocode() {
		return this.getField(1);
	}

	public void setIscocode(String Iscocode) {
		this.setField(2, Iscocode);
	}

	public String getIscocode() {
		return this.getField(2);
	}

}
