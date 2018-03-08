package kyle.leis.fs.corewaybillauditlog.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class CorewaybillauditlogCondition extends GeneralCondition {
	public CorewaybillauditlogCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setCwcode(String cwCode) {
		this.setField(0, cwCode);
	}

	public String getCwcode() {
		return this.getField(0);
	}

}
