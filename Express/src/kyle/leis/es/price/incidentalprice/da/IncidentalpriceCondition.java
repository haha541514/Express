package kyle.leis.es.price.incidentalprice.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class IncidentalpriceCondition extends GeneralCondition {
	public IncidentalpriceCondition() {
		m_astrConditions = new String[16];
	}	
	
	public void setEpcode(String epCode) {
		this.setField(0, epCode);
	}

	public String getEpcode() {
		return this.getField(0);
	}

	public void setEpstartdate(String epStartdate) {
		this.setField(1, epStartdate);
	}

	public String getEpstartdate() {
		return this.getField(1);
	}

	public void setEpstartdate2(String epStartdate2) {
		this.setField(2, epStartdate2);
	}

	public String getEpstartdate2() {
		return this.getField(2);
	}

	public void setPscode(String psCode) {
		this.setField(3, psCode);
	}

	public String getPscode() {
		return this.getField(3);
	}

	public void setCopid(String copId) {
		this.setField(4, copId);
	}

	public String getCopid() {
		return this.getField(4);
	}

	public void setMopid(String mopId) {
		this.setField(5, mopId);
	}

	public String getMopid() {
		return this.getField(5);
	}

	public void setPkcode(String pkCode) {
		this.setField(6, pkCode);
	}

	public String getPkcode() {
		return this.getField(6);
	}

	public void setDtcode(String dtCode) {
		this.setField(7, dtCode);
	}

	public String getDtcode() {
		return this.getField(7);
	}

	public void setPdcode(String pdCode) {
		this.setField(8, pdCode);
	}

	public String getPdcode() {
		return this.getField(8);
	}

	public void setCocode(String coCode) {
		this.setField(9, coCode);
	}

	public String getCocode() {
		return this.getField(9);
	}

	public void setPgcode(String pgCode) {
		this.setField(10, pgCode);
	}

	public String getPgcode() {
		return this.getField(10);
	}

	public void setChncode(String chnCode) {
		this.setField(11, chnCode);
	}

	public String getChncode() {
		return this.getField(11);
	}

	public void setEpkcode(String epkCode) {
		this.setField(12, epkCode);
	}

	public String getEpkcode() {
		return this.getField(12);
	}

	public void setEecode(String eeCode) {
		this.setField(13, eeCode);
	}

	public String getEecode() {
		return this.getField(13);
	}

	public void setEpecode(String epeCode) {
		this.setField(14, epeCode);
	}

	public String getEpecode() {
		return this.getField(14);
	}

}
