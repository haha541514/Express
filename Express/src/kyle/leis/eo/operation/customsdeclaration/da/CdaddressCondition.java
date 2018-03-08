package kyle.leis.eo.operation.customsdeclaration.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class CdaddressCondition extends GeneralCondition {
	public CdaddressCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setCdscacode(String cdscacode) {
		this.setField(0, cdscacode);
	}

	public String getCdscacode() {
		return this.getField(0);
	}

}
