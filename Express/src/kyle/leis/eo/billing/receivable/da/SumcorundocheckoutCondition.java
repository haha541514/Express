package kyle.leis.eo.billing.receivable.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class SumcorundocheckoutCondition extends GeneralCondition {
	public SumcorundocheckoutCondition() {
		m_astrConditions = new String[5];
	}	
	
	public void setCocode(String coCode) {
		this.setField(0, coCode);
	}

	public String getCocode() {
		return this.getField(0);
	}

	public void setStartsignindate(String StartSignindate) {
		this.setField(1, StartSignindate);
	}

	public String getStartsignindate() {
		return this.getField(1);
	}

	public void setEndsignindate(String EndSignindate) {
		this.setField(2, EndSignindate);
	}

	public String getEndsignindate() {
		return this.getField(2);
	}

	public void setInihscode(String Inihscode) {
		this.setField(3, Inihscode);
	}

	public String getInihscode() {
		return this.getField(3);
	}

}
