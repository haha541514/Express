package kyle.leis.eo.operation.transportwaybill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class TransportwaybillCondition extends GeneralCondition {
	public TransportwaybillCondition() {
		m_astrConditions = new String[15];
	}	
	
	public void setTwbid(String twbId) {
		this.setField(0, twbId);
	}

	public String getTwbid() {
		return this.getField(0);
	}

	public void setTwblabelcode(String twbLabelcode) {
		this.setField(1, twbLabelcode);
	}

	public String getTwblabelcode() {
		return this.getField(1);
	}

	public void setTwbscode(String twbsCode) {
		this.setField(2, twbsCode);
	}

	public String getTwbscode() {
		return this.getField(2);
	}

	public void setTtcode(String ttCode) {
		this.setField(3, ttCode);
	}

	public String getTtcode() {
		return this.getField(3);
	}

	public void setTtlabelcode(String ttLabelcode) {
		this.setField(4, ttLabelcode);
	}

	public String getTtlabelcode() {
		return this.getField(4);
	}

	public void setDdtcode(String ddtCode) {
		this.setField(5, ddtCode);
	}

	public String getDdtcode() {
		return this.getField(5);
	}

	public void setAdtcode(String adtCode) {
		this.setField(6, adtCode);
	}

	public String getAdtcode() {
		return this.getField(6);
	}

	public void setCopid(String copId) {
		this.setField(7, copId);
	}

	public String getCopid() {
		return this.getField(7);
	}

	public void setMopid(String mopId) {
		this.setField(8, mopId);
	}

	public String getMopid() {
		return this.getField(8);
	}

	public void setStartcreatedate(String StartCreatedate) {
		this.setField(9, StartCreatedate);
	}

	public String getStartcreatedate() {
		return this.getField(9);
	}

	public void setEndcreatedate(String EndCreatedate) {
		this.setField(10, EndCreatedate);
	}

	public String getEndcreatedate() {
		return this.getField(10);
	}

	public void setStartmodifydate(String StartModifydate) {
		this.setField(11, StartModifydate);
	}

	public String getStartmodifydate() {
		return this.getField(11);
	}

	public void setEndmodifydate(String EndModifydate) {
		this.setField(12, EndModifydate);
	}

	public String getEndmodifydate() {
		return this.getField(12);
	}

	public void setEestructurecode(String eeStructurecode) {
		this.setField(13, eeStructurecode);
	}

	public String getEestructurecode() {
		return this.getField(13);
	}

}
