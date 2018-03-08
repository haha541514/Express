package kyle.leis.es.price.zone.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ZoneCondition extends GeneralCondition {
	public ZoneCondition() {
		m_astrConditions = new String[9];
	}	
	
	public void setZncode(String znCode) {
		this.setField(0, znCode);
	}

	public String getZncode() {
		return this.getField(0);
	}

	public void setZnname(String znName) {
		this.setField(1, znName);
	}

	public String getZnname() {
		return this.getField(1);
	}

	public void setZnename(String znEname) {
		this.setField(2, znEname);
	}

	public String getZnename() {
		return this.getField(2);
	}

	public void setPkcode(String pkCode) {
		this.setField(3, pkCode);
	}

	public String getPkcode() {
		return this.getField(3);
	}

	public void setSscode(String ssCode) {
		this.setField(4, ssCode);
	}

	public String getSscode() {
		return this.getField(4);
	}

	public void setZnkeywords(String znKeywords) {
		this.setField(5, znKeywords);
	}

	public String getZnkeywords() {
		return this.getField(5);
	}

	public void setZnremark(String znRemark) {
		this.setField(6, znRemark);
	}

	public String getZnremark() {
		return this.getField(6);
	}

	public void setModifystartdate(String modifyStartdate) {
		this.setField(7, modifyStartdate);
	}

	public String getModifystartdate() {
		return this.getField(7);
	}

	public void setModifyenddate(String modifyEnddate) {
		this.setField(8, modifyEnddate);
	}

	public String getModifyenddate() {
		return this.getField(8);
	}

}
