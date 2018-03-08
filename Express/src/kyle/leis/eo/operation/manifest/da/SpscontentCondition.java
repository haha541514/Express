package kyle.leis.eo.operation.manifest.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class SpscontentCondition extends GeneralCondition {
	public SpscontentCondition() {
		m_astrConditions = new String[1];
	}	
	
	public void setMfcode(String strMfcode) {
		this.setField(0, strMfcode);
	}

	public String getMfcode() {
		return this.getField(0);
	}


}
