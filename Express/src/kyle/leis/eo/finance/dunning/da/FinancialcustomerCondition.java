package kyle.leis.eo.finance.dunning.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class FinancialcustomerCondition extends GeneralCondition {
	public FinancialcustomerCondition() {
		m_astrConditions = new String[3];
	}	
	
	public void setCocolablecode(String cocoLablecode) {
		this.setField(0, cocoLablecode);
	}

	public String getCocolablecode() {
		return this.getField(0);
	}

	public void setCocosname(String cocoSname) {
		this.setField(1, cocoSname);
	}

	public String getCocosname() {
		return this.getField(1);
	}

}
