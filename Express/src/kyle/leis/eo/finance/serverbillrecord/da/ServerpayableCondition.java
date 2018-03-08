package kyle.leis.eo.finance.serverbillrecord.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ServerpayableCondition extends GeneralCondition {
	public ServerpayableCondition() {
		m_astrConditions = new String[10];
	}	
	
	public void setSpyid(String spyId) {
		this.setField(0, spyId);
	}

	public String getSpyid() {
		return this.getField(0);
	}

	public void setFkcode(String fkCode) {
		this.setField(1, fkCode);
	}

	public String getFkcode() {
		return this.getField(1);
	}

	public void setSbrid(String sbrId) {
		this.setField(2, sbrId);
	}

	public String getSbrid() {
		return this.getField(2);
	}

	public void setSwbcode(String swbCode) {
		this.setField(3, swbCode);
	}

	public String getSwbcode() {
		return this.getField(3);
	}

	public void setSbdserverewbcode(String sbdServerewbcode) {
		this.setField(4, sbdServerewbcode);
	}

	public String getSbdserverewbcode() {
		return this.getField(4);
	}

	public void setSbdcustomerewbcode(String sbdCustomerewbcode) {
		this.setField(5, sbdCustomerewbcode);
	}

	public String getSbdcustomerewbcode() {
		return this.getField(5);
	}

	public void setSbdreferencecode(String sbdReferencecode) {
		this.setField(6, sbdReferencecode);
	}

	public String getSbdreferencecode() {
		return this.getField(6);
	}

	public void setChncode(String chnCode) {
		this.setField(7, chnCode);
	}

	public String getChncode() {
		return this.getField(7);
	}

	public void setSbrlabelcode(String sbrLabelcode) {
		this.setField(8, sbrLabelcode);
	}

	public String getSbrlabelcode() {
		return this.getField(8);
	}

}
