package kyle.leis.fs.waybillcode.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class WaybillcodeCondition extends GeneralCondition {
	public WaybillcodeCondition() {
		m_astrConditions = new String[9];
	}	
	
	public void setBcid(String bcId) {
		this.setField(0, bcId);
	}

	public String getBcid() {
		return this.getField(0);
	}

	public void setValidcode(String ValidCode) {
		this.setField(1, ValidCode);
	}

	public String getValidcode() {
		return this.getField(1);
	}

	public void setValidcode2(String ValidCode) {
		this.setField(2, ValidCode);
	}

	public String getValidcode2() {
		return this.getField(2);
	}

	public void setStartmodifydate(String StartModifydate) {
		this.setField(3, StartModifydate);
	}

	public String getStartmodifydate() {
		return this.getField(3);
	}

	public void setEndmodifydate(String EndModifydate) {
		this.setField(4, EndModifydate);
	}

	public String getEndmodifydate() {
		return this.getField(4);
	}

	public void setCscode(String csCode) {
		this.setField(5, csCode);
	}

	public String getCscode() {
		return this.getField(5);
	}

	public void setBcprefix(String bcPrefix) {
		this.setField(6, bcPrefix);
	}

	public String getBcprefix() {
		return this.getField(6);
	}

	public void setBcsuffix(String bcSuffix) {
		this.setField(7, bcSuffix);
	}

	public String getBcsuffix() {
		return this.getField(7);
	}

	public void setBckcode(String bckCode) {
		this.setField(8, bckCode);
	}

	public String getBckcode() {
		return this.getField(8);
	}

}
