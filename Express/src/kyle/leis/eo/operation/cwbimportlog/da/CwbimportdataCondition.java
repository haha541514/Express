package kyle.leis.eo.operation.cwbimportlog.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class CwbimportdataCondition extends GeneralCondition {
	public CwbimportdataCondition() {
		m_astrConditions = new String[3];
	}	
	
	public void setCwbrid(String cwbrId) {
		this.setField(0, cwbrId);
	}

	public String getCwbrid() {
		return this.getField(0);
	}

	public void setCwlid(String cwlId) {
		this.setField(1, cwlId);
	}

	public String getCwlid() {
		return this.getField(1);
	}

}
