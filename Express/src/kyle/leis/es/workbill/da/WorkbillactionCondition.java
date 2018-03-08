package kyle.leis.es.workbill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class WorkbillactionCondition extends GeneralCondition {
	public WorkbillactionCondition() {
		m_astrConditions = new String[3];
	}	
	
	public void setWbaid(String wbaId) {
		this.setField(0, wbaId);
	}

	public String getWbaid() {
		return this.getField(0);
	}

	public void setWbid(String wbId) {
		this.setField(1, wbId);
	}

	public String getWbid() {
		return this.getField(1);
	}

}
