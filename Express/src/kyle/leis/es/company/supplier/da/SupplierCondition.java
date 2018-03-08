package kyle.leis.es.company.supplier.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class SupplierCondition extends GeneralCondition {
	public SupplierCondition() {
		m_astrConditions = new String[13];
	}	
	
	public void setCocode(String coCode) {
		this.setField(0, coCode);
	}

	public String getCocode() {
		return this.getField(0);
	}

	public void setConame(String coName) {
		this.setField(1, coName);
	}

	public String getConame() {
		return this.getField(1);
	}

	public void setCoename(String coEname) {
		this.setField(2, coEname);
	}

	public String getCoename() {
		return this.getField(2);
	}

	public void setCosname(String coSname) {
		this.setField(3, coSname);
	}

	public String getCosname() {
		return this.getField(3);
	}

	public void setCosename(String coSename) {
		this.setField(4, coSename);
	}

	public String getCosename() {
		return this.getField(4);
	}

	public void setColabelcode(String coLabelcode) {
		this.setField(5, coLabelcode);
	}

	public String getColabelcode() {
		return this.getField(5);
	}

	public void setCtcode(String ctCode) {
		this.setField(6, ctCode);
	}

	public String getCtcode() {
		return this.getField(6);
	}

	public void setIncscode(String InCscode) {
		this.setField(7, InCscode);
	}

	public String getIncscode() {
		return this.getField(7);
	}

	public void setNotincscode(String NotInCscode) {
		this.setField(8, NotInCscode);
	}

	public String getNotincscode() {
		return this.getField(8);
	}

	public void setBrscode(String brsCode) {
		this.setField(9, brsCode);
	}

	public String getBrscode() {
		return this.getField(9);
	}
	public void setCrscode(String crsCode) {
		this.setField(10, crsCode);
	}

	public String getCrscode() {
		return this.getField(10);
	}

	public void setBegincarryoversign(String BeginCarryoversign) {
		this.setField(11, BeginCarryoversign);
	}

	public String getBegincarryoversign() {
		return this.getField(11);
	}

	public void setEndcarryoversigin(String EndCarryoversigin) {
		this.setField(11, EndCarryoversigin);
	}

	public String getEndcarryoversigin() {
		return this.getField(11);
	}

}
