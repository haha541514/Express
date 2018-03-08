package kyle.leis.es.company.customer.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class CustomerCondition extends GeneralCondition {
	public CustomerCondition() {
		m_astrConditions = new String[19];
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

	public void setOpidservice(String opidService) {
		this.setField(7, opidService);
	}

	public String getOpidservice() {
		return this.getField(7);
	}

	public void setOpidsales(String opidSales) {
		this.setField(8, opidSales);
	}

	public String getOpidsales() {
		return this.getField(8);
	}

	public void setOpiddunner(String opidDunner) {
		this.setField(9, opidDunner);
	}

	public String getOpiddunner() {
		return this.getField(9);
	}

	public void setIncscode(String InCsCode) {
		this.setField(10, InCsCode);
	}

	public String getIncscode() {
		return this.getField(10);
	}

	public void setNotincscode(String NotInCsCode) {
		this.setField(11, NotInCsCode);
	}

	public String getNotincscode() {
		return this.getField(11);
	}

	public void setBrscode(String brsCode) {
		this.setField(12, brsCode);
	}

	public String getBrscode() {
		return this.getField(12);
	}
	public void setCrscode(String crsCode) {
		this.setField(13, crsCode);
	}

	public String getCrscode() {
		return this.getField(13);
	}

	public void setBegincarryoversign(String BeginCarryoversign) {
		this.setField(14, BeginCarryoversign);
	}

	public String getBegincarryoversign() {
		return this.getField(14);
	}

	public void setEndcarryoversigin(String EndCarryoversigin) {
		this.setField(15, EndCarryoversigin);
	}

	public String getEndcarryoversigin() {
		return this.getField(15);
	}

	public void setCmparent(String cmparent) {
		this.setField(16, cmparent);
	}

	public String getCmparent() {
		return this.getField(16);
	}

	public void setEestructurecode(String eeStructurecode) {
		this.setField(17, eeStructurecode);
	}

	public String getEestructurecode() {
		return this.getField(17);
	}

}
