package kyle.leis.fs.authority.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class RolemenusCondition extends GeneralCondition {
	public RolemenusCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setRlcode(String strRlcode) {
		this.setField(0, strRlcode);
	}

	public String getRlcode() {
		return this.getField(0);
	}

	public void setEqualrlcode(String strEqualrlcode) {
		this.setField(1, strEqualrlcode);
	}

	public String getEqualrlcode() {
		return this.getField(1);
	}	

}
