package kyle.fetcher.track.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ChanneltrackmappingCondition extends GeneralCondition {
	public ChanneltrackmappingCondition() {
		m_astrConditions = new String[5];
	}	
	
	public void setCtmid(String ctmId) {
		this.setField(0, ctmId);
	}

	public String getCtmid() {
		return this.getField(0);
	}

	public void setCtmsourcetrackdesc(String ctmSourcetrackdesc) {
		this.setField(1, ctmSourcetrackdesc);
	}

	public String getCtmsourcetrackdesc() {
		return this.getField(1);
	}

	public void setChncode(String chnCode) {
		this.setField(2, chnCode);
	}

	public String getChncode() {
		return this.getField(2);
	}

	public void setWbtscode(String wbtsCode) {
		this.setField(3, wbtsCode);
	}

	public String getWbtscode() {
		return this.getField(3);
	}

	public void setCtmeqsourcetrackdesc(String ctmSourcetrackdesc) {
		this.setField(4, ctmSourcetrackdesc);
	}

	public String getCtmeqsourcetrackdesc() {
		return this.getField(4);
	}		
	
}
