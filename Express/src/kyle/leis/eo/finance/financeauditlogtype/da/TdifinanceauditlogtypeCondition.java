package kyle.leis.eo.finance.financeauditlogtype.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class TdifinanceauditlogtypeCondition extends GeneralCondition {
	public TdifinanceauditlogtypeCondition() {
		m_astrConditions = new String[3];
	}	
	
	public void setFaltcode(String faltCode) {
		this.setField(0, faltCode);
	}

	public String getFaltcode() {
		return this.getField(0);
	}

	public void setFaltcontent(String faltContent) {
		this.setField(1, faltContent);
	}

	public String getFaltcontent() {
		return this.getField(1);
	}

}
