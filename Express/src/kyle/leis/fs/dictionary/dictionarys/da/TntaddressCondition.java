package kyle.leis.fs.dictionary.dictionarys.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class TntaddressCondition extends GeneralCondition {
	public TntaddressCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setCountryhubcode(String countryhubcode) {
		this.setField(0, countryhubcode);
	}

	public String getCountryhubcode() {
		return this.getField(0);
	}

}
