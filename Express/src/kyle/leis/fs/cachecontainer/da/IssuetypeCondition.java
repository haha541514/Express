package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class IssuetypeCondition extends GeneralCondition {
	public IssuetypeCondition() {
		m_astrConditions = new String[1];
	}	
	
	public void setIsutcode(String isutCode) {
		this.setField(0, isutCode);
	}

	public String getIsutcode() {
		return this.getField(0);
	}

}
