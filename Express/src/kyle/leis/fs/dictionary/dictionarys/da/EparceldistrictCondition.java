package kyle.leis.fs.dictionary.dictionarys.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class EparceldistrictCondition extends GeneralCondition {
	public EparceldistrictCondition() {
		m_astrConditions = new String[3];
	}	
	
	public void setStartpostcode(String startpostcode) {
		this.setField(0, startpostcode);
	}

	public String getStartpostcode() {
		return this.getField(0);
	}

	public void setEndpostcode(String endpostcode) {
		this.setField(1, endpostcode);
	}

	public String getEndpostcode() {
		return this.getField(1);
	}

}
