package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class WaybillfordcustomerCondition extends GeneralCondition {
	public WaybillfordcustomerCondition() {
		m_astrConditions = new String[9];
	}	
	
	public void setCw_customerewbcode(String cw_customerewbcode) {
		this.setField(0, cw_customerewbcode);
	}

	public String getCw_customerewbcode() {
		return this.getField(0);
	}

	public void setBw_labelcode(String bw_labelcode) {
		this.setField(1, bw_labelcode);
	}

	public String getBw_labelcode() {
		return this.getField(1);
	}

	public void setPk_code(String pk_code) {
		this.setField(2, pk_code);
	}

	public String getPk_code() {
		return this.getField(2);
	}

	public void setCt_code(String ct_code) {
		this.setField(3, ct_code);
	}

	public String getCt_code() {
		return this.getField(3);
	}

	public void setStartadddate(String StartAdddate) {
		this.setField(4, StartAdddate);
	}

	public String getStartadddate() {
		return this.getField(4);
	}

	public void setEndadddate(String EndAdddate) {
		this.setField(5, EndAdddate);
	}

	public String getEndadddate() {
		return this.getField(5);
	}

	public void setCo_code_customer(String co_code_customer) {
		this.setField(6, co_code_customer);
	}

	public String getCo_code_customer() {
		return this.getField(6);
	}

	public void setBegincarryoversign(String BeginCarryoversign) {
		this.setField(7, BeginCarryoversign);
	}

	public String getBegincarryoversign() {
		return this.getField(7);
	}

	public void setEndcarryoversigin(String EndCarryoversigin) {
		this.setField(8, EndCarryoversigin);
	}

	public String getEndcarryoversigin() {
		return this.getField(8);
	}

}
