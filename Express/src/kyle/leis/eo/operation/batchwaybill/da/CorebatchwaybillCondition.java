package kyle.leis.eo.operation.batchwaybill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class CorebatchwaybillCondition extends GeneralCondition {
	public CorebatchwaybillCondition() {
		m_astrConditions = new String[6];
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

	public void setBwcode(String bwCode) {
		this.setField(2, bwCode);
	}

	public String getBwcode() {
		return this.getField(2);
	}

	public void setBwlabelcode(String bwlabelcode) {
		this.setField(3, bwlabelcode);
	}

	public String getBwlabelcode() {
		return this.getField(3);
	}

	public void setChnsename(String chnsename) {
		this.setField(4, chnsename);
	}

	public String getChnsename() {
		return this.getField(4);
	}

}
