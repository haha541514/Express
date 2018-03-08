package kyle.leis.fs.dictionary.enterpriseelement.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class EecityCondition extends GeneralCondition {
	public EecityCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setEecode(String eeCode) {
		this.setField(0, eeCode);
	}

	public String getEecode() {
		return this.getField(0);
	}

}
