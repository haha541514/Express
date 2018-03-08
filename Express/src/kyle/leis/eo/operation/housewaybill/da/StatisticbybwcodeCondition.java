package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class StatisticbybwcodeCondition extends GeneralCondition {
	public StatisticbybwcodeCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setBw_code_arrival(String BW_CODE_ARRIVAL) {
		this.setField(0, BW_CODE_ARRIVAL);
	}

	public String getBw_code_arrival() {
		return this.getField(0);
	}

	public void setCo_code_customer(String CO_CODE_CUSTOMER) {
		this.setField(1, CO_CODE_CUSTOMER);
	}

	public String getCo_code_customer() {
		return this.getField(1);
	}

}
