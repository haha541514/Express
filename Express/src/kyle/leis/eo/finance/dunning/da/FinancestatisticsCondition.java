package kyle.leis.eo.finance.dunning.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class FinancestatisticsCondition extends GeneralCondition {
	public FinancestatisticsCondition() {
		m_astrConditions = new String[14];
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

	public void setCosname(String coSname) {
		this.setField(2, coSname);
	}

	public String getCosname() {
		return this.getField(2);
	}

	public void setColabelcode(String coLabelcode) {
		this.setField(3, coLabelcode);
	}

	public String getColabelcode() {
		return this.getField(3);
	}

	public void setBegincarryoversign(String BeginCarryoversign) {
		this.setField(4, BeginCarryoversign);
	}

	public String getBegincarryoversign() {
		return this.getField(4);
	}

	public void setEndcarryoversigin(String EndCarryoversigin) {
		this.setField(5, EndCarryoversigin);
	}

	public String getEndcarryoversigin() {
		return this.getField(5);
	}

	public void setFscarryoverenterprise(String fsCarryoverenterprise) {
		this.setField(6, fsCarryoverenterprise);
	}

	public String getFscarryoverenterprise() {
		return this.getField(6);
	}

	public void setCtcode(String ctCode) {
		this.setField(7, ctCode);
	}

	public String getCtcode() {
		return this.getField(7);
	}

	public void setDunoperid(String DunOperId) {
		this.setField(8, DunOperId);
	}

	public String getDunoperid() {
		return this.getField(8);
	}

	public void setStartdate(String startdate) {
		this.setField(9, startdate);
	}

	public String getStartdate() {
		return this.getField(9);
	}

	public void setEnddate(String enddate) {
		this.setField(10, enddate);
	}

	public String getEnddate() {
		return this.getField(10);
	}

	public void setSaleoperid(String saleoperid) {
		this.setField(11, saleoperid);
	}

	public String getSaleoperid() {
		return this.getField(11);
	}

	public void setEestructurecode(String eeStructurecode) {
		this.setField(12, eeStructurecode);
	}

	public String getEestructurecode() {
		return this.getField(12);
	}

}
