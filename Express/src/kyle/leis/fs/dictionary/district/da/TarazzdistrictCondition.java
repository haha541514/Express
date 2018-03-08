package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class TarazzdistrictCondition extends GeneralCondition {
	public TarazzdistrictCondition() {
		m_astrConditions = new String[3];
	}	
	
	public void setTdpostcode(String tdpostcode) {
		this.setField(0, tdpostcode);
	}

	public String getTdpostcode() {
		return this.getField(0);
	}

	public void setLocalityname(String localityname) {
		this.setField(1, localityname);
	}

	public String getLocalityname() {
		return this.getField(1);
	}

}
