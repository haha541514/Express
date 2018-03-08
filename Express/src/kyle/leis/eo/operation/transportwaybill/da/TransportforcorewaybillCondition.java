package kyle.leis.eo.operation.transportwaybill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class TransportforcorewaybillCondition extends GeneralCondition {
	public TransportforcorewaybillCondition() {
		m_astrConditions = new String[4];
	}	
	
	public void setBwcode(String bwCode) {
		this.setField(0, bwCode);
	}

	public String getBwcode() {
		return this.getField(0);
	}

	public void setTwbvbaglabelcode(String twbvBaglabelcode) {
		this.setField(1, twbvBaglabelcode);
	}

	public String getTwbvbaglabelcode() {
		return this.getField(1);
	}

	public void setNulltwbvbaglabelcode(String nulltwbvBaglabelcode) {
		this.setField(2, nulltwbvBaglabelcode);
	}

	public String getNulltwbvbaglabelcode() {
		return this.getField(2);
	}
	
	public void setCwcode(String cwCode) {
		this.setField(3, cwCode);
	}

	public String getCwcode() {	
		return this.getField(3);
	}	
		
}
