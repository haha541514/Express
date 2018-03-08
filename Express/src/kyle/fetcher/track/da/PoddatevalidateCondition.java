package kyle.fetcher.track.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class PoddatevalidateCondition extends GeneralCondition {
	public PoddatevalidateCondition() {
		m_astrConditions = new String[3];
	}	
	
	public void setCwcode(String cwCode) {
		this.setField(0, cwCode);
	}

	public String getCwcode() {
		return this.getField(0);
	}

	public void setStartarrivaldate(String StartArrivalDate) {
		this.setField(1, StartArrivalDate);
	}

	public String getStartarrivaldate() {
		return this.getField(1);
	}

	public void setEndarrivaldate(String EndArrivalDate) {
		this.setField(2, EndArrivalDate);
	}

	public String getEndarrivaldate() {
		return this.getField(2);
	}

}
