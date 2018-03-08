package kyle.leis.eo.operation.corewaybillcode.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class CorewaybillcodeCondition extends GeneralCondition {
	public CorewaybillcodeCondition() {
		m_astrConditions = new String[3];
	}	
	
	public void setCwcode(String cwCode) {
		this.setField(0, cwCode);
	}

	public String getCwcode() {
		return this.getField(0);
	}

	public void setCwbcid(String cwbcId) {
		this.setField(1, cwbcId);
	}

	public String getCwbcid() {
		return this.getField(1);
	}

}
