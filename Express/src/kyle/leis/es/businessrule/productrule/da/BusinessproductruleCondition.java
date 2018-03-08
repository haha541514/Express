package kyle.leis.es.businessrule.productrule.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class BusinessproductruleCondition extends GeneralCondition {
	public BusinessproductruleCondition() {
		m_astrConditions = new String[4];
	}	
	
	public void setStartdate(String StartDate) {
		this.setField(0, StartDate);
	}

	public String getStartdate() {
		return this.getField(0);
	}

	public void setEnddate(String EndDate) {
		this.setField(1, EndDate);
	}

	public String getEnddate() {
		return this.getField(1);
	}

	public void setPkcode(String pkCode) {
		this.setField(2, pkCode);
	}

	public String getPkcode() {
		return this.getField(2);
	}

	public void setSscode(String ssCode) {
		this.setField(3, ssCode);
	}

	public String getSscode() {
		return this.getField(3);
	}

}
