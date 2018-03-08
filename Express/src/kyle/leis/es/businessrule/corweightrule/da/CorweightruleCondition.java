package kyle.leis.es.businessrule.corweightrule.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class CorweightruleCondition extends GeneralCondition {
	public CorweightruleCondition() {
		m_astrConditions = new String[14];
	}	
	
	public void setBrid(String brId) {
		this.setField(0, brId);
	}

	public String getBrid() {
		return this.getField(0);
	}

	public void setBrname(String brName) {
		this.setField(1, brName);
	}

	public String getBrname() {
		return this.getField(1);
	}

	public void setBrename(String brEname) {
		this.setField(2, brEname);
	}

	public String getBrename() {
		return this.getField(2);
	}

	public void setWrkname(String wrkName) {
		this.setField(3, wrkName);
	}

	public String getWrkname() {
		return this.getField(3);
	}

	public void setStartcreatedate(String StartCreatedate) {
		this.setField(4, StartCreatedate);
	}

	public String getStartcreatedate() {
		return this.getField(4);
	}

	public void setEndcreatedate(String EndCreatedate) {
		this.setField(5, EndCreatedate);
	}

	public String getEndcreatedate() {
		return this.getField(5);
	}

	public void setStartmodifydate(String StartModifydate) {
		this.setField(6, StartModifydate);
	}

	public String getStartmodifydate() {
		return this.getField(6);
	}

	public void setEndmodifydate(String EndModifydate) {
		this.setField(7, EndModifydate);
	}

	public String getEndmodifydate() {
		return this.getField(7);
	}

	public void setValiddate1(String validDate1) {
		this.setField(8, validDate1);
	}

	public String getValiddate1() {
		return this.getField(8);
	}

	public void setValiddate2(String validDate2) {
		this.setField(9, validDate2);
	}

	public String getValiddate2() {
		return this.getField(9);
	}

	public void setSscode(String ssCode) {
		this.setField(10, ssCode);
	}

	public String getSscode() {
		return this.getField(10);
	}

	public void setChncode(String chnCode) {
		this.setField(11, chnCode);
	}

	public String getChncode() {
		return this.getField(11);
	}

	public void setCocode(String coCode) {
		this.setField(12, coCode);
	}

	public String getCocode() {
		return this.getField(12);
	}

	public void setPdcode(String pdCode) {
		this.setField(13, pdCode);
	}

	public String getPdcode() {
		return this.getField(13);
	}

}
