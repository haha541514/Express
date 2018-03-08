package kyle.leis.eo.operation.batchwaybill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class SimplebatchwbvalueCondition extends GeneralCondition {
	public SimplebatchwbvalueCondition() {
		m_astrConditions = new String[11];
	}	
	
	public void setStartadddate(String StartAddDate) {
		this.setField(0, StartAddDate);
	}

	public String getStartadddate() {
		return this.getField(0);
	}

	public void setEndadddate(String EndAddDate) {
		this.setField(1, EndAddDate);
	}

	public String getEndadddate() {
		return this.getField(1);
	}

	public void setCocodecustomer(String cocodecustomer) {
		this.setField(2, cocodecustomer);
	}

	public String getCocodecustomer() {
		return this.getField(2);
	}

	public void setChncodesupplier(String chncodesupplier) {
		this.setField(3, chncodesupplier);
	}

	public String getChncodesupplier() {
		return this.getField(3);
	}

	public void setBwbvid(String bwbvid) {
		this.setField(4, bwbvid);
	}

	public String getBwbvid() {
		return this.getField(4);
	}

	public void setPkcode(String pkcode) {
		this.setField(5, pkcode);
	}

	public String getPkcode() {
		return this.getField(5);
	}

	public void setCwcode(String cwcode) {
		this.setField(6, cwcode);
	}

	public String getCwcode() {
		return this.getField(6);
	}

	public void setBwcode(String bwcode) {
		this.setField(7, bwcode);
	}

	public String getBwcode() {
		return this.getField(7);
	}

	public void setBwlabelcode(String bwlabelcode) {
		this.setField(8, bwlabelcode);
	}

	public String getBwlabelcode() {
		return this.getField(8);
	}

	public void setAdtcode(String adtcode) {
		this.setField(9, adtcode);
	}

	public String getAdtcode() {
		return this.getField(9);
	}

}
