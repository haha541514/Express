package kyle.leis.eo.billing.receivable.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ReceivableforfaxCondition extends GeneralCondition {
	public ReceivableforfaxCondition() {
		m_astrConditions = new String[11];
	}	
	
	public void setCo_code_supplier(String co_code_supplier) {
		this.setField(0, co_code_supplier);
	}

	public String getCo_code_supplier() {
		return this.getField(0);
	}

	public void setCocode(String coCode) {
		this.setField(1, coCode);
	}

	public String getCocode() {
		return this.getField(1);
	}

	public void setInfscode(String InfsCode) {
		this.setField(2, InfsCode);
	}

	public String getInfscode() {
		return this.getField(2);
	}

	public void setNotinfscode(String NotInfsCode) {
		this.setField(3, NotInfsCode);
	}

	public String getNotinfscode() {
		return this.getField(3);
	}

	public void setCw_code(String cw_code) {
		this.setField(4, cw_code);
	}

	public String getCw_code() {
		return this.getField(4);
	}

	public void setBrid(String brId) {
		this.setField(5, brId);
	}

	public String getBrid() {
		return this.getField(5);
	}

	public void setIsnullsign(String IsNullSign) {
		this.setField(6, IsNullSign);
	}

	public String getIsnullsign() {
		return this.getField(6);
	}

	public void setFkcode(String fkCode) {
		this.setField(7, fkCode);
	}

	public String getFkcode() {
		return this.getField(7);
	}

	public void setStartrvoccurdate(String StartRvOccurdate) {
		this.setField(8, StartRvOccurdate);
	}

	public String getStartrvoccurdate() {
		return this.getField(8);
	}

	public void setEndrvoccurdate(String EndRvOccurdate) {
		this.setField(9, EndRvOccurdate);
	}

	public String getEndrvoccurdate() {
		return this.getField(9);
	}

}
