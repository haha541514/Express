package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class SumchargeweightCondition extends GeneralCondition {
	public SumchargeweightCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setBw_code_arrival(String BW_CODE_ARRIVAL) {
		this.setField(0, BW_CODE_ARRIVAL);
	}

	public String getBw_code_arrival() {
		return this.getField(0);
	}

}
