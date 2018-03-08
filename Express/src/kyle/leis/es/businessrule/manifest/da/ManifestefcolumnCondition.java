package kyle.leis.es.businessrule.manifest.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ManifestefcolumnCondition extends GeneralCondition {
	public ManifestefcolumnCondition() {
		m_astrConditions = new String[3];
	}	
	
	public void setMefcode(String mefCode) {
		this.setField(0, mefCode);
	}

	public String getMefcode() {
		return this.getField(0);
	}

	public void setMefcid(String mefcId) {
		this.setField(1, mefcId);
	}

	public String getMefcid() {
		return this.getField(1);
	}

}
