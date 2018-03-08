package kyle.leis.eo.operation.predictwaybill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class PredictwaybillCondition extends GeneralCondition {
	public PredictwaybillCondition() {
		m_astrConditions = new String[20];
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

	public void setStartdeclaredate(String StartDeclareDate) {
		this.setField(2, StartDeclareDate);
	}

	public String getStartdeclaredate() {
		return this.getField(2);
	}

	public void setEnddeclaredate(String EndDeclareDate) {
		this.setField(3, EndDeclareDate);
	}

	public String getEnddeclaredate() {
		return this.getField(3);
	}

	public void setStartprintdate(String StartPrintDate) {
		this.setField(4, StartPrintDate);
	}

	public String getStartprintdate() {
		return this.getField(4);
	}

	public void setEndprintdate(String EndPrintDate) {
		this.setField(5, EndPrintDate);
	}

	public String getEndprintdate() {
		return this.getField(5);
	}

	public void setCo_code_customer(String co_code_customer) {
		this.setField(6, co_code_customer);
	}

	public String getCo_code_customer() {
		return this.getField(6);
	}

	public void setOp_id_creator(String op_id_creator) {
		this.setField(7, op_id_creator);
	}

	public String getOp_id_creator() {
		return this.getField(7);
	}

	public void setPwb_code(String pwb_code) {
		this.setField(8, pwb_code);
	}

	public String getPwb_code() {
		return this.getField(8);
	}

	public void setPwb_orderid(String pwb_orderid) {
		this.setField(9, pwb_orderid);
	}

	public String getPwb_orderid() {
		return this.getField(9);
	}

	public void setPwbs_code(String pwbs_code) {
		this.setField(10, pwbs_code);
	}

	public String getPwbs_code() {
		return this.getField(10);
	}

	public void setPk_code(String pk_code) {
		this.setField(11, pk_code);
	}

	public String getPk_code() {
		return this.getField(11);
	}

	public void setDt_code(String dt_code) {
		this.setField(12, dt_code);
	}

	public String getDt_code() {
		return this.getField(12);
	}

	public void setPwb_customerremark(String pwb_customerremark) {
		this.setField(13, pwb_customerremark);
	}

	public String getPwb_customerremark() {
		return this.getField(13);
	}

	public void setPwb_consigneename(String pwb_consigneename) {
		this.setField(14, pwb_consigneename);
	}

	public String getPwb_consigneename() {
		return this.getField(14);
	}

	public void setLikepwbconsigneeaddress(String likepwbconsigneeaddress) {
		this.setField(15, likepwbconsigneeaddress);
	}

	public String getLikepwbconsigneeaddress() {
		return this.getField(15);
	}

	public void setLikepwbcargoename(String likepwbcargoename) {
		this.setField(16, likepwbcargoename);
	}

	public String getLikepwbcargoename() {
		return this.getField(16);
	}

	public void setCwcode(String cwcode) {
		this.setField(17, cwcode);
	}

	public String getCwcode() {
		return this.getField(17);
	}

	public void setLikecocode(String likecocode) {
		this.setField(18, likecocode);
	}

	public String getLikecocode() {
		return this.getField(18);
	}

}
