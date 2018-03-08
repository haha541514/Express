package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class SobagstatisticCondition extends GeneralCondition {
	public SobagstatisticCondition() {
		m_astrConditions = new String[7];
	}	
	
	public void setStartdate(String StartDate) {
		this.setField(0, StartDate);
	}

	public String getStartdate() {
		return this.getField(0);
	}

	public void setEnddate(String EndDate) {
		this.setField(1, EndDate);
	}

	public String getEnddate() {
		return this.getField(1);
	}

	public void setChncodesupplier(String chncodeSupplier) {
		this.setField(2, chncodeSupplier);
	}

	public String getChncodesupplier() {
		return this.getField(2);
	}

	public void setBwcodedeparture(String bwcodeDeparture) {
		this.setField(3, bwcodeDeparture);
	}

	public String getBwcodedeparture() {
		return this.getField(3);
	}

	public void setCpbaglabelcode(String cpbaglabelcode) {
		this.setField(4, cpbaglabelcode);
	}

	public String getCpbaglabelcode() {
		return this.getField(4);
	}

	public void setTwblabelcode(String twbLabelcode) {
		this.setField(5, twbLabelcode);
	}

	public String getTwblabelcode() {
		return this.getField(5);
	}

}
