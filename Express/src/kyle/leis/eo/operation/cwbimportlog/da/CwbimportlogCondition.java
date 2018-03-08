package kyle.leis.eo.operation.cwbimportlog.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class CwbimportlogCondition extends GeneralCondition {
	public CwbimportlogCondition() {
		m_astrConditions = new String[6];
	}	
	
	public void setCwlid(String cwlId) {
		this.setField(0, cwlId);
	}

	public String getCwlid() {
		return this.getField(0);
	}

	public void setOpid(String opId) {
		this.setField(1, opId);
	}

	public String getOpid() {
		return this.getField(1);
	}

	public void setBegindate(String begindate) {
		this.setField(2, begindate);
	}

	public String getBegindate() {
		return this.getField(2);
	}

	public void setEnddate(String enddate) {
		this.setField(3, enddate);
	}

	public String getEnddate() {
		return this.getField(3);
	}

	public void setTotalrecords(String totalrecords) {
		this.setField(4, totalrecords);
	}

	public String getTotalrecords() {
		return this.getField(4);
	}

}
