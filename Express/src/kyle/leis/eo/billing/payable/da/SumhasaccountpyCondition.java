package kyle.leis.eo.billing.payable.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class SumhasaccountpyCondition extends GeneralCondition {
	public SumhasaccountpyCondition() {
		m_astrConditions = new String[3];
	}	
	
	public void setCocode(String coCode) {
		this.setField(0, coCode);
	}

	public String getCocode() {
		return this.getField(0);
	}

	public void setBrid(String brId) {
		this.setField(1, brId);
	}

	public String getBrid() {
		return this.getField(1);
	}

}
