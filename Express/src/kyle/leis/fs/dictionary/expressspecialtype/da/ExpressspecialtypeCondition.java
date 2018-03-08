package kyle.leis.fs.dictionary.expressspecialtype.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ExpressspecialtypeCondition extends GeneralCondition {
	public ExpressspecialtypeCondition() {
		m_astrConditions = new String[3];
	}	
	
	public void setEstcode(String EstCode) {
		this.setField(0, EstCode);
	}

	public String getEstcode() {
		return this.getField(0);
	}

	public void setEststructurecode(String EstStructurecode) {
		this.setField(1, EstStructurecode);
	}

	public String getEststructurecode() {
		return this.getField(1);
	}

}
