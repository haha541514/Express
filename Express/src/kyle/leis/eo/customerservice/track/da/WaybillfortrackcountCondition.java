package kyle.leis.eo.customerservice.track.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class WaybillfortrackcountCondition extends GeneralCondition {
	public WaybillfortrackcountCondition() {
		m_astrConditions = new String[6];
	}	
	
	public void setCwcocode(String cwCoCode) {
		this.setField(0, cwCoCode);
	}

	public String getCwcocode() {
		return this.getField(0);
	}

	public void setStartcwcreatedate(String startCwcreatedate) {
		this.setField(1, startCwcreatedate);
	}

	public String getStartcwcreatedate() {
		return this.getField(1);
	}

	public void setEndcwcreatedate(String endCwcreatedate) {
		this.setField(2, endCwcreatedate);
	}

	public String getEndcwcreatedate() {
		return this.getField(2);
	}

	public void setWbtscode(String wbtsCode) {
		this.setField(3, wbtsCode);
	}

	public String getWbtscode() {
		return this.getField(3);
	}

	public void setNotwbtscode(String notWbtsCode) {
		this.setField(4, notWbtsCode);
	}

	public String getNotwbtscode() {
		return this.getField(4);
	}

}
