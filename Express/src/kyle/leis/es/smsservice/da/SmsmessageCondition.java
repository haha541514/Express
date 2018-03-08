package kyle.leis.es.smsservice.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class SmsmessageCondition extends GeneralCondition {
	public SmsmessageCondition() {
		m_astrConditions = new String[7];
	}	
	
	public void setSmsid(String smsId) {
		this.setField(0, smsId);
	}

	public String getSmsid() {
		return this.getField(0);
	}

	public void setCocode(String coCode) {
		this.setField(1, coCode);
	}

	public String getCocode() {
		return this.getField(1);
	}

	public void setSmsmobilenumber(String smsMobilenumber) {
		this.setField(2, smsMobilenumber);
	}

	public String getSmsmobilenumber() {
		return this.getField(2);
	}

	public void setSmsreceivecocode(String smsReceivecocode) {
		this.setField(3, smsReceivecocode);
	}

	public String getSmsreceivecocode() {
		return this.getField(3);
	}

	public void setSmsstatus(String smsStatus) {
		this.setField(4, smsStatus);
	}

	public String getSmsstatus() {
		return this.getField(4);
	}

	public void setStartsmscreatedate(String startsmsCreatedate) {
		this.setField(5, startsmsCreatedate);
	}

	public String getStartsmscreatedate() {
		return this.getField(5);
	}

	public void setEndsmscreatedate(String endsmsCreatedate) {
		this.setField(6, endsmsCreatedate);
	}

	public String getEndsmscreatedate() {
		return this.getField(6);
	}

}
