package kyle.leis.es.price.adjustiveprice.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class AdjustivepriceCondition extends GeneralCondition {
	public AdjustivepriceCondition() {
		m_astrConditions = new String[9];
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

	public void setEpkcode(String epkCode) {
		this.setField(6, epkCode);
	}

	public String getEpkcode() {
		return this.getField(6);
	}

	public void setEecode(String eeCode) {
		this.setField(7, eeCode);
	}

	public String getEecode() {
		return this.getField(7);
	}

	public void setChncode(String chnCode) {
		this.setField(8, chnCode);
	}

	public String getChncode() {
		return this.getField(8);
	}

}
