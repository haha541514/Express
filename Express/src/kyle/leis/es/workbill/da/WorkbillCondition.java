package kyle.leis.es.workbill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class WorkbillCondition extends GeneralCondition {
	public WorkbillCondition() {
		m_astrConditions = new String[5];
	}	
	
	public void setSurveillant(String surveillant) {
		this.setField(0, surveillant);
	}

	public String getSurveillant() {
		return this.getField(0);
	}

	public void setExecutor(String executor) {
		this.setField(1, executor);
	}

	public String getExecutor() {
		return this.getField(1);
	}

	public void setWbscode(String wbsCode) {
		this.setField(2, wbsCode);
	}

	public String getWbscode() {
		return this.getField(2);
	}

	public void setWbid(String wbId) {
		this.setField(3, wbId);
	}

	public String getWbid() {
		return this.getField(3);
	}

}
