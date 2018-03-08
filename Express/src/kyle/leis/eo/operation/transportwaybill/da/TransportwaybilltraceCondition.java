package kyle.leis.eo.operation.transportwaybill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class TransportwaybilltraceCondition extends GeneralCondition {
	public TransportwaybilltraceCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setTwbid(String twbId) {
		this.setField(0, twbId);
	}

	public String getTwbid() {
		return this.getField(0);
	}

}
