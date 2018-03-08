package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class DistrictCondition extends GeneralCondition {
	public DistrictCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setDkcode(String Dkcode) {
		this.setField(0, Dkcode);
	}

	public String getDkcode() {
		return this.getField(0);
	}

	public void setDtcode(String dtCode) {
		this.setField(1, dtCode);
	}

	public String getDtcode() {
		return this.getField(1);
	}

}
