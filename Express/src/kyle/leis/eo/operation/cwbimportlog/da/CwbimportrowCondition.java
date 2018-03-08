package kyle.leis.eo.operation.cwbimportlog.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class CwbimportrowCondition extends GeneralCondition {
	public CwbimportrowCondition() {
		m_astrConditions = new String[5];
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

	public void setSuccesssign(String successsign) {
		this.setField(2, successsign);
	}

	public String getSuccesssign() {
		return this.getField(2);
	}

	public void setOperatetype(String operatetype) {
		this.setField(3, operatetype);
	}

	public String getOperatetype() {
		return this.getField(3);
	}

}
