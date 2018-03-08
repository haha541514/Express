package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class CorewaybillforchangeewbCondition extends GeneralCondition {
	public CorewaybillforchangeewbCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setCw_code(String cw_code) {
		this.setField(0, cw_code);
	}

	public String getCw_code() {
		return this.getField(0);
	}

}
