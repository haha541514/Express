package kyle.leis.eo.operation.predictwaybill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class PredictcargoinfoCondition extends GeneralCondition {
	public PredictcargoinfoCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setPwbcode(String pwbcode) {
		this.setField(0, pwbcode);
	}

	public String getPwbcode() {
		return this.getField(0);
	}

}
