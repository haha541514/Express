package kyle.leis.es.smsservice.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class SmsrechargerecordCondition extends GeneralCondition {
	public SmsrechargerecordCondition() {
		m_astrConditions = new String[5];
	}	
	
	public void setSrrid(String srrId) {
		this.setField(0, srrId);
	}

	public String getSrrid() {
		return this.getField(0);
	}

	public void setSsid(String ssId) {
		this.setField(1, ssId);
	}

	public String getSsid() {
		return this.getField(1);
	}

	public void setCocode(String coCode) {
		this.setField(2, coCode);
	}

	public String getCocode() {
		return this.getField(2);
	}

	public void setStartsrrcreatedate(String startsrrCreatedate) {
		this.setField(3, startsrrCreatedate);
	}

	public String getStartsrrcreatedate() {
		return this.getField(3);
	}

	public void setEndsrrcreatedate(String endsrrCreatedate) {
		this.setField(4, endsrrCreatedate);
	}

	public String getEndsrrcreatedate() {
		return this.getField(4);
	}

}
