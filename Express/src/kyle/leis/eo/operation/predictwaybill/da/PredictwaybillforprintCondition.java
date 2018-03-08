package kyle.leis.eo.operation.predictwaybill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class PredictwaybillforprintCondition extends GeneralCondition {
	public PredictwaybillforprintCondition() {
		m_astrConditions = new String[6];
	}	
	
	public void setStartcreatedate(String StartCreateDate) {
		this.setField(0, StartCreateDate);
	}

	public String getStartcreatedate() {
		return this.getField(0);
	}

	public void setEndcreatedate(String EndCreateDate) {
		this.setField(1, EndCreateDate);
	}

	public String getEndcreatedate() {
		return this.getField(1);
	}

	public void setCo_code_customer(String co_code_customer) {
		this.setField(2, co_code_customer);
	}

	public String getCo_code_customer() {
		return this.getField(2);
	}

	public void setOp_id_creator(String op_id_creator) {
		this.setField(3, op_id_creator);
	}

	public String getOp_id_creator() {
		return this.getField(3);
	}

	public void setPwb_code(String pwb_code) {
		this.setField(4, pwb_code);
	}

	public String getPwb_code() {
		return this.getField(4);
	}

}
