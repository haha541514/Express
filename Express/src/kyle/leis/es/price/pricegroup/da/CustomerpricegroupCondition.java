package kyle.leis.es.price.pricegroup.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class CustomerpricegroupCondition extends GeneralCondition {
	public CustomerpricegroupCondition() {
		m_astrConditions = new String[15];
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

	public void setEpcreatedatestart(String epCreateDateStart) {
		this.setField(3, epCreateDateStart);
	}

	public String getEpcreatedatestart() {
		return this.getField(3);
	}

	public void setEpcreatedateend(String epCreateDateEnd) {
		this.setField(4, epCreateDateEnd);
	}

	public String getEpcreatedateend() {
		return this.getField(4);
	}

	public void setEpmodifydatestart(String epModifyDateStart) {
		this.setField(5, epModifyDateStart);
	}

	public String getEpmodifydatestart() {
		return this.getField(5);
	}

	public void setEpmodifydateend(String epModifyDateEnd) {
		this.setField(6, epModifyDateEnd);
	}

	public String getEpmodifydateend() {
		return this.getField(6);
	}

	public void setPscode(String psCode) {
		this.setField(7, psCode);
	}

	public String getPscode() {
		return this.getField(7);
	}

	public void setCopid(String copId) {
		this.setField(8, copId);
	}

	public String getCopid() {
		return this.getField(8);
	}

	public void setMopid(String mopId) {
		this.setField(9, mopId);
	}

	public String getMopid() {
		return this.getField(9);
	}

	public void setPkcode(String pkCode) {
		this.setField(10, pkCode);
	}

	public String getPkcode() {
		return this.getField(10);
	}

	public void setPgkcode(String pgkCode) {
		this.setField(11, pgkCode);
	}

	public String getPgkcode() {
		return this.getField(11);
	}

	public void setCocode(String coCode) {
		this.setField(12, coCode);
	}

	public String getCocode() {
		return this.getField(12);
	}

	public void setEpkcode(String epkCode) {
		this.setField(13, epkCode);
	}

	public String getEpkcode() {
		return this.getField(13);
	}

	public void setEecode(String eeCode) {
		this.setField(14, eeCode);
	}

	public String getEecode() {
		return this.getField(14);
	}

}
