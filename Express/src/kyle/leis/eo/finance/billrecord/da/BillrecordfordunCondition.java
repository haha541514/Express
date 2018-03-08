package kyle.leis.eo.finance.billrecord.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class BillrecordfordunCondition extends GeneralCondition {
	public BillrecordfordunCondition() {
		m_astrConditions = new String[7];
	}	
	
	public void setCo_code(String co_code) {
		this.setField(0, co_code);
	}

	public String getCo_code() {
		return this.getField(0);
	}

	public void setStartoccurdate(String StartOccurdate) {
		this.setField(1, StartOccurdate);
	}

	public String getStartoccurdate() {
		return this.getField(1);
	}

	public void setEndoccurdate(String EndOccurdate) {
		this.setField(2, EndOccurdate);
	}

	public String getEndoccurdate() {
		return this.getField(2);
	}

	public void setBegincarryoversign(String Begincarryoversign) {
		this.setField(3, Begincarryoversign);
	}

	public String getBegincarryoversign() {
		return this.getField(3);
	}

	public void setEndcarryoversigin(String Endcarryoversigin) {
		this.setField(4, Endcarryoversigin);
	}

	public String getEndcarryoversigin() {
		return this.getField(4);
	}

	public void setCk_code(String ck_code) {
		this.setField(5, ck_code);
	}

	public String getCk_code() {
		return this.getField(5);
	}

}
