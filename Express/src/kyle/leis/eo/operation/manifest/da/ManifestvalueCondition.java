package kyle.leis.eo.operation.manifest.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ManifestvalueCondition extends GeneralCondition {
	public ManifestvalueCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setMvid(String mvId) {
		this.setField(0, mvId);
	}

	public String getMvid() {
		return this.getField(0);
	}

	public void setMfcode(String mfCode) {
		this.setField(1, mfCode);
	}

	public String getMfcode() {
		return this.getField(1);
	}

}
