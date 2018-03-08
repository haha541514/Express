package kyle.leis.es.businessrule.productrule.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ProductruleCondition extends GeneralCondition {
	public ProductruleCondition() {
		m_astrConditions = new String[9];
	}	
	
	public void setBrid(String brId) {
		this.setField(0, brId);
	}

	public String getBrid() {
		return this.getField(0);
	}

	public void setPkcode(String pkCode) {
		this.setField(1, pkCode);
	}

	public String getPkcode() {
		return this.getField(1);
	}

	public void setStartcreatedate(String StartCreatedate) {
		this.setField(2, StartCreatedate);
	}

	public String getStartcreatedate() {
		return this.getField(2);
	}

	public void setEndcreatedate(String EndCreatedate) {
		this.setField(3, EndCreatedate);
	}

	public String getEndcreatedate() {
		return this.getField(3);
	}

	public void setStartmodifydate(String StartModifydate) {
		this.setField(4, StartModifydate);
	}

	public String getStartmodifydate() {
		return this.getField(4);
	}

	public void setEndmodifydate(String EndModifydate) {
		this.setField(5, EndModifydate);
	}

	public String getEndmodifydate() {
		return this.getField(5);
	}

	public void setValiddate1(String validDate1) {
		this.setField(6, validDate1);
	}

	public String getValiddate1() {
		return this.getField(6);
	}

	public void setValiddate2(String validDate2) {
		this.setField(7, validDate2);
	}

	public String getValiddate2() {
		return this.getField(7);
	}

	public void setSscode(String ssCode) {
		this.setField(8, ssCode);
	}

	public String getSscode() {
		return this.getField(8);
	}

}
