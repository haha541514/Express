package kyle.leis.eo.billing.receivable.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ReceivableforsmsCondition extends GeneralCondition {
	public ReceivableforsmsCondition() {
		m_astrConditions = new String[1];
	}	
	
	public void setBw_code_arrival(String bw_code_arrival) {
		this.setField(0, bw_code_arrival);
	}

	public String getBw_code_arrival() {
		return this.getField(0);
	}

}
