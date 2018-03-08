package kyle.leis.eo.customerservice.track.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class WaybilltrackCondition extends GeneralCondition {
	public WaybilltrackCondition() {
		m_astrConditions = new String[9];
	}	
	
	public void setWbtid(String wbtId) {
		this.setField(0, wbtId);
	}

	public String getWbtid() {
		return this.getField(0);
	}

	public void setCwcode(String cwCode) {
		this.setField(1, cwCode);
	}

	public String getCwcode() {
		return this.getField(1);
	}

	public void setIscwcode(String isCwCode) {
		this.setField(2, isCwCode);
	}

	public String getIscwcode() {
		return this.getField(2);
	}

	public void setDtcode(String dtCode) {
		this.setField(3, dtCode);
	}

	public String getDtcode() {
		return this.getField(3);
	}

	public void setWbtscode(String wbtsCode) {
		this.setField(4, wbtsCode);
	}

	public String getWbtscode() {
		return this.getField(4);
	}

	public void setWbtopensign(String wbtOpensign) {
		this.setField(5, wbtOpensign);
	}

	public String getWbtopensign() {
		return this.getField(5);
	}

	public void setStartoccurdate(String StartOccurdate) {
		this.setField(6, StartOccurdate);
	}

	public String getStartoccurdate() {
		return this.getField(6);
	}

	public void setEndoccurdate(String EndOccurdate) {
		this.setField(7, EndOccurdate);
	}

	public String getEndoccurdate() {
		return this.getField(7);
	}
	
	public void setWbtdescription(String Wbtdescription) {
		this.setField(8, Wbtdescription);
	}

	public String getWbtdescription() {
		return this.getField(8);
	}		
}
