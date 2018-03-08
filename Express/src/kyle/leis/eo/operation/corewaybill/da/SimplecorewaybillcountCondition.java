package kyle.leis.eo.operation.corewaybill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class SimplecorewaybillcountCondition extends GeneralCondition {
	public SimplecorewaybillcountCondition() {
		m_astrConditions = new String[4];
	}	
	
	public void setCocodecustomer(String cocodecustomer) {
		this.setField(0, cocodecustomer);
	}

	public String getCocodecustomer() {
		return this.getField(0);
	}

	public void setStartime(String startime) {
		this.setField(1, startime);
	}

	public String getStartime() {
		return this.getField(1);
	}
	public void setEndtime(String endtime) {
		this.setField(2, endtime);
	}

	public String getEndtime() {
		return this.getField(2);
	}

}
