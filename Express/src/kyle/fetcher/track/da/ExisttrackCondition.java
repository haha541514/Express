package kyle.fetcher.track.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ExisttrackCondition extends GeneralCondition {
	public ExisttrackCondition() {
		m_astrConditions = new String[5];
	}	
	
	public void setCwcode(String cwCode) {
		this.setField(0, cwCode);
	}

	public String getCwcode() {
		return this.getField(0);
	}

	public void setCocode(String coCode) {
		this.setField(1, coCode);
	}

	public String getCocode() {
		return this.getField(1);
	}

	public void setWbtdescription(String wbtdescription) {
		this.setField(2, wbtdescription);
	}

	public String getWbtdescription() {
		return this.getField(2);
	}

	public void setWbtlocation(String wbtlocation) {
		this.setField(3, wbtlocation);
	}

	public String getWbtlocation() {
		return this.getField(3);
	}

	public void setWbtoccurdate(String wbtoccurdate) {
		this.setField(4, wbtoccurdate);
	}

	public String getWbtoccurdate() {
		return this.getField(4);
	}

}
