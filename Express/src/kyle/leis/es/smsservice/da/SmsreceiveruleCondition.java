package kyle.leis.es.smsservice.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class SmsreceiveruleCondition extends GeneralCondition {
	public SmsreceiveruleCondition() {
		m_astrConditions = new String[7];
	}	
	
	public void setSnkcode(String snkCode) {
		this.setField(0, snkCode);
	}

	public String getSnkcode() {
		return this.getField(0);
	}

	public void setOpid(String opId) {
		this.setField(1, opId);
	}

	public String getOpid() {
		return this.getField(1);
	}

	public void setOpmobile(String Opmobile) {
		this.setField(2, Opmobile);
	}

	public String getOpmobile() {
		return this.getField(2);
	}

	public void setCocode(String Cocode) {
		this.setField(3, Cocode);
	}

	public String getCocode() {
		return this.getField(3);
	}

	public void setSnttcode(String snttCode) {
		this.setField(4, snttCode);
	}

	public String getSnttcode() {
		return this.getField(4);
	}

	public void setSnttrestrictsign(String snttRestrictsign) {
		this.setField(5, snttRestrictsign);
	}

	public String getSnttrestrictsign() {
		return this.getField(5);
	}

}
