package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class SimplecorewaybillCondition extends GeneralCondition {
	public SimplecorewaybillCondition() {
		m_astrConditions = new String[11];
	}	
	
	public void setCw_code(String CW_CODE) {
		this.setField(0, CW_CODE);
	}

	public String getCw_code() {
		return this.getField(0);
	}

	public void setCw_customerewbcode(String CW_CUSTOMEREWBCODE) {
		this.setField(1, CW_CUSTOMEREWBCODE);
	}

	public String getCw_customerewbcode() {
		return this.getField(1);
	}

	public void setCw_serverewbcode(String CW_SERVEREWBCODE) {
		this.setField(2, CW_SERVEREWBCODE);
	}

	public String getCw_serverewbcode() {
		return this.getField(2);
	}

	public void setCw_ewbcode(String CW_EWBCODE) {
		this.setField(3, CW_EWBCODE);
	}

	public String getCw_ewbcode() {
		return this.getField(3);
	}

	public void setChncodesupplier(String Chncodesupplier) {
		this.setField(4, Chncodesupplier);
	}

	public String getChncodesupplier() {
		return this.getField(4);
	}

	public void setCocodecustomer(String cocodecustomer) {
		this.setField(5, cocodecustomer);
	}

	public String getCocodecustomer() {
		return this.getField(5);
	}

	public void setStartcreatedate(String startcreatedate) {
		this.setField(6, startcreatedate);
	}

	public String getStartcreatedate() {
		return this.getField(6);
	}

	public void setEndcreatedate(String endcreatedate) {
		this.setField(7, endcreatedate);
	}

	public String getEndcreatedate() {
		return this.getField(7);
	}

	public void setNoincwscode(String Noincwscode) {
		this.setField(8, Noincwscode);
	}

	public String getNoincwscode() {
		return this.getField(8);
	}

	public void setSchildlabelcode(String schildlabelcode) {
		this.setField(9, schildlabelcode);
	}

	public String getSchildlabelcode() {
		return this.getField(9);
	}

}
