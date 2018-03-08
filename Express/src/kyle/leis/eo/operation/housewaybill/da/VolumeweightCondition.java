package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class VolumeweightCondition extends GeneralCondition {
	public VolumeweightCondition() {
		m_astrConditions = new String[1];
	}	
	
	public void setCw_code(String cw_code) {
		this.setField(0, cw_code);
	}

	public String getCw_code() {
		return this.getField(0);
	}

}
