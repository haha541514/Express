package kyle.fetcher.track.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class TrackstatusCondition extends GeneralCondition {
	public TrackstatusCondition() {
		m_astrConditions = new String[1];
	}	
	
	public void setCwcode(String cwCode) {
		this.setField(0, cwCode);
	}

	public String getCwcode() {
		return this.getField(0);
	}

}
