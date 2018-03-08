package kyle.leis.eo.customerservice.issue.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class IssuecountCondition extends GeneralCondition {
	public IssuecountCondition() {
		m_astrConditions = new String[6];
	}	
	
	public void setCwcocode(String cwCoCode) {
		this.setField(0, cwCoCode);
	}

	public String getCwcocode() {
		return this.getField(0);
	}

	public void setStarthwsignindate(String startHwSignindate) {
		this.setField(1, startHwSignindate);
	}

	public String getStarthwsignindate() {
		return this.getField(1);
	}

	public void setEndhwsignindate(String endHwSignindate) {
		this.setField(2, endHwSignindate);
	}

	public String getEndhwsignindate() {
		return this.getField(2);
	}

	public void setIsuscode(String isusCode) {
		this.setField(3, isusCode);
	}

	public String getIsuscode() {
		return this.getField(3);
	}

	public void setIsutcode(String isutCode) {
		this.setField(4, isutCode);
	}

	public String getIsutcode() {
		return this.getField(4);
	}

}
