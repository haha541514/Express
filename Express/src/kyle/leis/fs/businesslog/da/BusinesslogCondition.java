package kyle.leis.fs.businesslog.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class BusinesslogCondition extends GeneralCondition {
	public BusinesslogCondition() {
		m_astrConditions = new String[7];
	}	
	
	public void setBlid(String blId) {
		this.setField(0, blId);
	}

	public String getBlid() {
		return this.getField(0);
	}

	public void setAkcode(String akCode) {
		this.setField(1, akCode);
	}

	public String getAkcode() {
		return this.getField(1);
	}

	public void setOpid(String opId) {
		this.setField(2, opId);
	}

	public String getOpid() {
		return this.getField(2);
	}

	public void setStartcreatedate(String startCreatedate) {
		this.setField(3, startCreatedate);
	}

	public String getStartcreatedate() {
		return this.getField(3);
	}

	public void setEndcreatedate(String endCreatedate) {
		this.setField(4, endCreatedate);
	}

	public String getEndcreatedate() {
		return this.getField(4);
	}

	public void setBlkcode(String blkCode) {
		this.setField(5, blkCode);
	}

	public String getBlkcode() {
		return this.getField(5);
	}

	public void setBlbusinesscode(String blBusinesscode) {
		this.setField(6, blBusinesscode);
	}

	public String getBlbusinesscode() {
		return this.getField(6);
	}

}
