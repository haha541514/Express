package kyle.leis.eo.operation.batchwaybill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class SimplebatchwaybillCondition extends GeneralCondition {
	public SimplebatchwaybillCondition() {
		m_astrConditions = new String[13];
	}	
	
	public void setBwcode(String bwCode) {
		this.setField(0, bwCode);
	}

	public String getBwcode() {
		return this.getField(0);
	}

	public void setStartadddate(String StartAddDate) {
		this.setField(1, StartAddDate);
	}

	public String getStartadddate() {
		return this.getField(1);
	}

	public void setEndadddate(String EndAddDate) {
		this.setField(2, EndAddDate);
	}

	public String getEndadddate() {
		return this.getField(2);
	}

	public void setBwlabelcode(String bwLabelcode) {
		this.setField(3, bwLabelcode);
	}

	public String getBwlabelcode() {
		return this.getField(3);
	}

	public void setBwscode(String bwsCode) {
		this.setField(4, bwsCode);
	}

	public String getBwscode() {
		return this.getField(4);
	}

	public void setChncode(String chnCode) {
		this.setField(5, chnCode);
	}

	public String getChncode() {
		return this.getField(5);
	}

	public void setEecode(String eeCode) {
		this.setField(6, eeCode);
	}

	public String getEecode() {
		return this.getField(6);
	}

	public void setAdtcode(String adtCode) {
		this.setField(7, adtCode);
	}

	public String getAdtcode() {
		return this.getField(7);
	}

	public void setCocode(String coCode) {
		this.setField(8, coCode);
	}

	public String getCocode() {
		return this.getField(8);
	}

	public void setNotinbwscode(String NotInbwsCode) {
		this.setField(9, NotInbwsCode);
	}

	public String getNotinbwscode() {
		return this.getField(9);
	}

	public void setBwbvid(String Bwbvid) {
		this.setField(10, Bwbvid);
	}

	public String getBwbvid() {
		return this.getField(10);
	}

	public void setEestructurecode(String eeStructurecode) {
		this.setField(11, eeStructurecode);
	}

	public String getEestructurecode() {
		return this.getField(11);
	}

}
