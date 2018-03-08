package kyle.leis.eo.customerservice.track.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class WaybillbatchtrackCondition extends GeneralCondition {
	public WaybillbatchtrackCondition() {
		m_astrConditions = new String[7];
	}	
	
	public void setCwcode(String cwCode) {
		this.setField(0, cwCode);
	}

	public String getCwcode() {
		return this.getField(0);
	}

	public void setWbbtlatesttrackdesc(String wbbtLatesttrackdesc) {
		this.setField(1, wbbtLatesttrackdesc);
	}

	public String getWbbtlatesttrackdesc() {
		return this.getField(1);
	}

	public void setWbbtlatestcslogdesc(String wbbtLatestcslogdesc) {
		this.setField(2, wbbtLatestcslogdesc);
	}

	public String getWbbtlatestcslogdesc() {
		return this.getField(2);
	}

	public void setNotsignforuser(String NotSignforuser) {
		this.setField(3, NotSignforuser);
	}

	public String getNotsignforuser() {
		return this.getField(3);
	}

	public void setStartsignfordate(String StartSignfordate) {
		this.setField(4, StartSignfordate);
	}

	public String getStartsignfordate() {
		return this.getField(4);
	}

	public void setEndsignfordate(String EndSignfordate) {
		this.setField(5, EndSignfordate);
	}

	public String getEndsignfordate() {
		return this.getField(5);
	}

}
