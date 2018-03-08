package kyle.leis.es.smsservice.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class SmsserviceCondition extends GeneralCondition {
	public SmsserviceCondition() {
		m_astrConditions = new String[10];
	}	
	
	public void setSsid(String ssId) {
		this.setField(0, ssId);
	}

	public String getSsid() {
		return this.getField(0);
	}

	public void setCocode(String coCode) {
		this.setField(1, coCode);
	}

	public String getCocode() {
		return this.getField(1);
	}

	public void setStartssstartdate(String startssStartdate) {
		this.setField(2, startssStartdate);
	}

	public String getStartssstartdate() {
		return this.getField(2);
	}

	public void setEndssstartdate(String endssStartdate) {
		this.setField(3, endssStartdate);
	}

	public String getEndssstartdate() {
		return this.getField(3);
	}

	public void setStartssenddate(String startssEnddate) {
		this.setField(4, startssEnddate);
	}

	public String getStartssenddate() {
		return this.getField(4);
	}

	public void setEndssenddate(String endssEnddate) {
		this.setField(5, endssEnddate);
	}

	public String getEndssenddate() {
		return this.getField(5);
	}

	public void setStartsscreatedate(String startssCreatedate) {
		this.setField(6, startssCreatedate);
	}

	public String getStartsscreatedate() {
		return this.getField(6);
	}

	public void setEndsscreatedate(String endssCreatedate) {
		this.setField(7, endssCreatedate);
	}

	public String getEndsscreatedate() {
		return this.getField(7);
	}

	public void setStartssmodifydate(String startssModifydate) {
		this.setField(8, startssModifydate);
	}

	public String getStartssmodifydate() {
		return this.getField(8);
	}

	public void setEndssmodifydate(String endssModifydate) {
		this.setField(9, endssModifydate);
	}

	public String getEndssmodifydate() {
		return this.getField(9);
	}

}
