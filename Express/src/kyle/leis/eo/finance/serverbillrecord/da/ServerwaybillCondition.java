package kyle.leis.eo.finance.serverbillrecord.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ServerwaybillCondition extends GeneralCondition {
	public ServerwaybillCondition() {
		m_astrConditions = new String[5];
	}	
	
	public void setSwbcode(String swbCode) {
		this.setField(0, swbCode);
	}

	public String getSwbcode() {
		return this.getField(0);
	}

	public void setChncode(String chnCode) {
		this.setField(1, chnCode);
	}

	public String getChncode() {
		return this.getField(1);
	}

	public void setSbdserverewbcode(String sbdServerEwbcode) {
		this.setField(2, sbdServerEwbcode);
	}

	public String getSbdserverewbcode() {
		return this.getField(2);
	}

	public void setSbdreferencecode(String sbdReferencecode) {
		this.setField(3, sbdReferencecode);
	}

	public String getSbdreferencecode() {
		return this.getField(3);
	}

}
