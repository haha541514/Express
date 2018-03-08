package kyle.leis.eo.finance.dunning.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class FinancialdetailsCondition extends GeneralCondition {
	public FinancialdetailsCondition() {
		m_astrConditions = new String[5];
	}	
	
	public void setCocolablecode(String cocoLablecode) {
		this.setField(0, cocoLablecode);
	}

	public String getCocolablecode() {
		return this.getField(0);
	}

	public void setCocosname(String cocoSname) {
		this.setField(1, cocoSname);
	}

	public String getCocosname() {
		return this.getField(1);
	}

	public void setStartsignoutdate(String StartSignoutdate) {
		this.setField(2, StartSignoutdate);
	}

	public String getStartsignoutdate() {
		return this.getField(2);
	}

	public void setEndsignoutdate(String EndSignoutdate) {
		this.setField(3, EndSignoutdate);
	}

	public String getEndsignoutdate() {
		return this.getField(3);
	}

}
