package kyle.leis.es.businessrule.manifestexportformat.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ManifeststandardcolumnCondition extends GeneralCondition {
	public ManifeststandardcolumnCondition() {
		m_astrConditions = new String[3];
	}	
	
	public void setMsccode(String mscCode) {
		this.setField(0, mscCode);
	}

	public String getMsccode() {
		return this.getField(0);
	}

	public void setMsccolumnname(String mscColumnname) {
		this.setField(1, mscColumnname);
	}

	public String getMsccolumnname() {
		return this.getField(1);
	}

}
