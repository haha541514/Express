package kyle.fetcher.track.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class WaybillforfetcherCondition extends GeneralCondition {
	public WaybillforfetcherCondition() {
		m_astrConditions = new String[8];
	}	
	
	public void setCocodesupplier(String cocodesupplier) {
		this.setField(0, cocodesupplier);
	}

	public String getCocodesupplier() {
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

	public void setCwcode(String cwcode) {
		this.setField(3, cwcode);
	}

	public String getCwcode() {
		return this.getField(3);
	}

	public void setCwserverewbcode(String cwserverewbcode) {
		this.setField(4, cwserverewbcode);
	}

	public String getCwserverewbcode() {
		return this.getField(4);
	}

	public void setChncodesupplier(String chncodesupplier) {
		this.setField(5, chncodesupplier);
	}

	public String getChncodesupplier() {
		return this.getField(5);
	}

	public void setWpacode(String wpacode) {
		this.setField(6, wpacode);
	}

	public String getWpacode() {
		return this.getField(6);
	}

	public void setSsgcode(String ssgcode) {
		this.setField(7, ssgcode);
	}

	public String getSsgcode() {
		return this.getField(7);
	}

}
