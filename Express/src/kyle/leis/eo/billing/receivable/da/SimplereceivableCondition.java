package kyle.leis.eo.billing.receivable.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class SimplereceivableCondition extends GeneralCondition {
	public SimplereceivableCondition() {
		m_astrConditions = new String[6];
	}	
	
	public void setCocode(String coCode) {
		this.setField(0, coCode);
	}

	public String getCocode() {
		return this.getField(0);
	}

	public void setInfscode(String InfsCode) {
		this.setField(1, InfsCode);
	}

	public String getInfscode() {
		return this.getField(1);
	}

	public void setNotinfscode(String NotInfsCode) {
		this.setField(2, NotInfsCode);
	}

	public String getNotinfscode() {
		return this.getField(2);
	}

	public void setCw_code(String cw_code) {
		this.setField(3, cw_code);
	}

	public String getCw_code() {
		return this.getField(3);
	}

	public void setBrid(String brId) {
		this.setField(4, brId);
	}

	public String getBrid() {
		return this.getField(4);
	}

	public void setFkcode(String fkCode) {
		this.setField(5, fkCode);
	}

	public String getFkcode() {
		return this.getField(5);
	}

}
