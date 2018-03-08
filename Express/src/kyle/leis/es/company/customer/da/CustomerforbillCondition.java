package kyle.leis.es.company.customer.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class CustomerforbillCondition extends GeneralCondition {
	public CustomerforbillCondition() {
		m_astrConditions = new String[7];
	}	
	
	public void setCocode(String coCode) {
		this.setField(0, coCode);
	}

	public String getCocode() {
		return this.getField(0);
	}

	public void setStartdate(String startdate) {
		this.setField(1, startdate);
	}

	public String getStartdate() {
		return this.getField(1);
	}
	public void setEnddate(String enddate) {
		this.setField(2, enddate);
	}

	public String getEnddate() {
		return this.getField(2);
	}

	public void setOpiddunner(String opidDunner) {
		this.setField(3, opidDunner);
	}

	public String getOpiddunner() {
		return this.getField(3);
	}

	public void setCtcode(String ctCode) {
		this.setField(4, ctCode);
	}

	public String getCtcode() {
		return this.getField(4);
	}

	public void setEestructurecode(String eeStructurecode) {
		this.setField(5, eeStructurecode);
	}

	public String getEestructurecode() {
		return this.getField(5);
	}

}
