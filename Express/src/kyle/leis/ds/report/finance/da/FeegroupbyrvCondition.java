package kyle.leis.ds.report.finance.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class FeegroupbyrvCondition extends GeneralCondition {
	public FeegroupbyrvCondition() {
		m_astrConditions = new String[10];
	}	
	
	public void setFkcode(String fkcode) {
		this.setField(0, fkcode);
	}

	public String getFkcode() {
		return this.getField(0);
	}

	public void setCocode(String cocode) {
		this.setField(1, cocode);
	}

	public String getCocode() {
		return this.getField(1);
	}

	public void setStartrvoccurdate(String startrvoccurdate) {
		this.setField(2, startrvoccurdate);
	}

	public String getStartrvoccurdate() {
		return this.getField(2);
	}

	public void setEndrvoccurdate(String Endrvoccurdate) {
		this.setField(3, Endrvoccurdate);
	}

	public String getEndrvoccurdate() {
		return this.getField(3);
	}

	public void setPkcode(String pkcode) {
		this.setField(4, pkcode);
	}

	public String getPkcode() {
		return this.getField(4);
	}

	public void setIncustomerewbcode(String InCustomerewbcode) {
		this.setField(5, InCustomerewbcode);
	}

	public String getIncustomerewbcode() {
		return this.getField(5);
	}
	public void setInserverewbcode(String InServerewbcode) {
		this.setField(6, InServerewbcode);
	}

	public String getInserverewbcode() {
		return this.getField(6);
	}

	public void setCocodesupplier(String cocodesupplier) {
		this.setField(7, cocodesupplier);
	}

	public String getCocodesupplier() {
		return this.getField(7);
	}

	public void setEestructurecode(String eeStructurecode) {
		this.setField(8, eeStructurecode);
	}

	public String getEestructurecode() {
		return this.getField(8);
	}

}
