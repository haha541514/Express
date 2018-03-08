package kyle.leis.eo.operation.customsdeclaration.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class CustomsdeclarationCondition extends GeneralCondition {
	public CustomsdeclarationCondition() {
		m_astrConditions = new String[3];
	}	
	
	public void setCwcode(String cwCode) {
		this.setField(0, cwCode);
	}

	public String getCwcode() {
		return this.getField(0);
	}

	public void setCdlabelcode(String cdLabelcode) {
		this.setField(1, cdLabelcode);
	}

	public String getCdlabelcode() {
		return this.getField(1);
	}

}
