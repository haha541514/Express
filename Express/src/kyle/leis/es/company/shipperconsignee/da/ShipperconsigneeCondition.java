package kyle.leis.es.company.shipperconsignee.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ShipperconsigneeCondition extends GeneralCondition {
	public ShipperconsigneeCondition() {
		m_astrConditions = new String[6];
	}	
	
	public void setSclabelcode(String scLabelcode) {
		this.setField(0, scLabelcode);
	}

	public String getSclabelcode() {
		return this.getField(0);
	}

	public void setScshipperconsigneetype(String scShipperconsigneetype) {
		this.setField(1, scShipperconsigneetype);
	}

	public String getScshipperconsigneetype() {
		return this.getField(1);
	}

	public void setChncode(String chnCode) {
		this.setField(2, chnCode);
	}

	public String getChncode() {
		return this.getField(2);
	}

	public void setSccode(String scCode) {
		this.setField(3, scCode);
	}

	public String getSccode() {
		return this.getField(3);
	}

	public void setCmcocode(String cmcocode) {
		this.setField(4, cmcocode);
	}

	public String getCmcocode() {
		return this.getField(4);
	}

}
