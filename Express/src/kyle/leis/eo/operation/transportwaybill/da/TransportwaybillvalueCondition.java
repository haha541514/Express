package kyle.leis.eo.operation.transportwaybill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class TransportwaybillvalueCondition extends GeneralCondition {
	public TransportwaybillvalueCondition() {
		m_astrConditions = new String[3];
	}	
	
	public void setTwbid(String twbId) {
		this.setField(0, twbId);
	}

	public String getTwbid() {
		return this.getField(0);
	}

	public void setBwcode(String bwCode) {
		this.setField(1, bwCode);
	}

	public String getBwcode() {
		return this.getField(1);
	}

}
