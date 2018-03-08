package kyle.leis.es.price.freightprice.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class FreightpriceCondition extends GeneralCondition {
	public FreightpriceCondition() {
		m_astrConditions = new String[23];
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

	public void setCkcode(String ckCode) {
		this.setField(8, ckCode);
	}

	public String getCkcode() {
		return this.getField(8);
	}

	public void setPdcode(String pdCode) {
		this.setField(9, pdCode);
	}

	public String getPdcode() {
		return this.getField(9);
	}

	public void setPmcode(String pmCode) {
		this.setField(10, pmCode);
	}

	public String getPmcode() {
		return this.getField(10);
	}

	public void setUtcode(String utCode) {
		this.setField(11, utCode);
	}

	public String getUtcode() {
		return this.getField(11);
	}

	public void setCtcode(String ctCode) {
		this.setField(12, ctCode);
	}

	public String getCtcode() {
		return this.getField(12);
	}

	public void setZncode(String znCode) {
		this.setField(13, znCode);
	}

	public String getZncode() {
		return this.getField(13);
	}

	public void setZnkeywords(String znKeywords) {
		this.setField(14, znKeywords);
	}

	public String getZnkeywords() {
		return this.getField(14);
	}

	public void setCocode(String coCode) {
		this.setField(15, coCode);
	}

	public String getCocode() {
		return this.getField(15);
	}

	public void setPgcode(String pgCode) {
		this.setField(16, pgCode);
	}

	public String getPgcode() {
		return this.getField(16);
	}

	public void setChncode(String chnCode) {
		this.setField(17, chnCode);
	}

	public String getChncode() {
		return this.getField(17);
	}

	public void setEpkcode(String epkCode) {
		this.setField(18, epkCode);
	}

	public String getEpkcode() {
		return this.getField(18);
	}

	public void setEecode(String eeCode) {
		this.setField(19, eeCode);
	}

	public String getEecode() {
		return this.getField(19);
	}

	public void setIsnullsign(String IsNullSign) {
		this.setField(20, IsNullSign);
	}

	public String getIsnullsign() {
		return this.getField(20);
	}

	public void setEpecode(String epeCode) {
		this.setField(21, epeCode);
	}

	public String getEpecode() {
		return this.getField(21);
	}

}
