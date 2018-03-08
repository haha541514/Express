package kyle.leis.fs.dictionary.productkind.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class PkcargokindCondition extends GeneralCondition {
	public PkcargokindCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setPkcode(String pkcode) {
		this.setField(0, pkcode);
	}

	public String getPkcode() {
		return this.getField(0);
	}

}
