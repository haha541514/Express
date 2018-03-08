package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class Dhlconnect4Condition extends GeneralCondition {
	public Dhlconnect4Condition() {
		m_astrConditions = new String[10];
	}	
	
	public void setCwcode(String cwcode) {
		this.setField(0, cwcode);
	}

	public String getCwcode() {
		return this.getField(0);
	}

	public void setCwcustomerewbcode(String cwcustomerewbcode) {
		this.setField(1, cwcustomerewbcode);
	}

	public String getCwcustomerewbcode() {
		return this.getField(1);
	}

	public void setCwserverewbcode(String cwserverewbcode) {
		this.setField(2, cwserverewbcode);
	}

	public String getCwserverewbcode() {
		return this.getField(2);
	}

	public void setBwlabelcode(String bwlabelcode) {
		this.setField(3, bwlabelcode);
	}

	public String getBwlabelcode() {
		return this.getField(3);
	}

	public void setStartrecorddate(String StartRecordDate) {
		this.setField(4, StartRecordDate);
	}

	public String getStartrecorddate() {
		return this.getField(4);
	}

	public void setEndrecorddate(String EndRecordDate) {
		this.setField(5, EndRecordDate);
	}

	public String getEndrecorddate() {
		return this.getField(5);
	}

	public void setPkcode(String pkcode) {
		this.setField(6, pkcode);
	}

	public String getPkcode() {
		return this.getField(6);
	}

	public void setOpidrecord(String opidrecord) {
		this.setField(7, opidrecord);
	}

	public String getOpidrecord() {
		return this.getField(7);
	}

	public void setSsgcode(String ssgcode) {
		this.setField(8, ssgcode);
	}

	public String getSsgcode() {
		return this.getField(8);
	}

}
