package kyle.leis.eo.operation.cargoinfo.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class CargoinfoCondition extends GeneralCondition {
	public CargoinfoCondition() {
		m_astrConditions = new String[4];
	}	
	
	public void setCwcode(String cwCode) {
		this.setField(0, cwCode);
	}

	public String getCwcode() {
		return this.getField(0);
	}

	public void setCiciename(String ciciEName) {
		this.setField(1, ciciEName);
	}

	public String getCiciename() {
		return this.getField(1);
	}

	public void setCiciattacheinfo(String ciciAttacheinfo) {
		this.setField(2, ciciAttacheinfo);
	}

	public String getCiciattacheinfo() {
		return this.getField(2);
	}

}
