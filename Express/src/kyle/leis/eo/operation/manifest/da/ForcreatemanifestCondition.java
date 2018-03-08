package kyle.leis.eo.operation.manifest.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ForcreatemanifestCondition extends GeneralCondition {
	public ForcreatemanifestCondition() {
		m_astrConditions = new String[13];
	}	
	
	public void setChn_code(String chn_code) {
		this.setField(0, chn_code);
	}

	public String getChn_code() {
		return this.getField(0);
	}

	public void setOpidrecord(String OpIdRecord) {
		this.setField(1, OpIdRecord);
	}

	public String getOpidrecord() {
		return this.getField(1);
	}

	public void setRecordstartdate(String RecordStartdate) {
		this.setField(2, RecordStartdate);
	}

	public String getRecordstartdate() {
		return this.getField(2);
	}

	public void setRecordenddate(String RecordEnddate) {
		this.setField(3, RecordEnddate);
	}

	public String getRecordenddate() {
		return this.getField(3);
	}

	public void setBw_labelcode(String bw_labelcode) {
		this.setField(4, bw_labelcode);
	}

	public String getBw_labelcode() {
		return this.getField(4);
	}

	public void setCo_code(String co_code) {
		this.setField(5, co_code);
	}

	public String getCo_code() {
		return this.getField(5);
	}

	public void setCws_code(String cws_code) {
		this.setField(6, cws_code);
	}

	public String getCws_code() {
		return this.getField(6);
	}

	public void setSsg_code(String ssg_code) {
		this.setField(7, ssg_code);
	}

	public String getSsg_code() {
		return this.getField(7);
	}

	public void setNotexistsmanifestsign(String notexistsmanifestsign) {
		this.setField(8, notexistsmanifestsign);
	}

	public String getNotexistsmanifestsign() {
		return this.getField(8);
	}

	public void setEe_structurecode(String ee_structurecode) {
		this.setField(9, ee_structurecode);
	}

	public String getEe_structurecode() {
		return this.getField(9);
	}

	public void setCwserverewbcode(String cwserverewbcode) {
		this.setField(10, cwserverewbcode);
	}

	public String getCwserverewbcode() {
		return this.getField(10);
	}

	public void setCwcustomerewbcode(String cwcustomerewbcode) {
		this.setField(11, cwcustomerewbcode);
	}

	public String getCwcustomerewbcode() {
		return this.getField(11);
	}

}
