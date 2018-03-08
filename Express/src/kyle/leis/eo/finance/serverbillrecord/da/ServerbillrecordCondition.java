package kyle.leis.eo.finance.serverbillrecord.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ServerbillrecordCondition extends GeneralCondition {
	public ServerbillrecordCondition() {
		m_astrConditions = new String[13];
	}	
	
	public void setSbrid(String sbrId) {
		this.setField(0, sbrId);
	}

	public String getSbrid() {
		return this.getField(0);
	}

	public void setSbrlabelcode(String sbrLabelcode) {
		this.setField(1, sbrLabelcode);
	}

	public String getSbrlabelcode() {
		return this.getField(1);
	}

	public void setStartoccurdate(String StartOccurdate) {
		this.setField(2, StartOccurdate);
	}

	public String getStartoccurdate() {
		return this.getField(2);
	}

	public void setEndoccurdate(String EndOccurdate) {
		this.setField(3, EndOccurdate);
	}

	public String getEndoccurdate() {
		return this.getField(3);
	}

	public void setChncode(String chnCode) {
		this.setField(4, chnCode);
	}

	public String getChncode() {
		return this.getField(4);
	}

	public void setNotinsscode(String NotInSscode) {
		this.setField(5, NotInSscode);
	}

	public String getNotinsscode() {
		return this.getField(5);
	}

	public void setEecode(String eeCode) {
		this.setField(6, eeCode);
	}

	public String getEecode() {
		return this.getField(6);
	}

	public void setOpidcreate(String OpIdCreate) {
		this.setField(7, OpIdCreate);
	}

	public String getOpidcreate() {
		return this.getField(7);
	}

	public void setOpidmodify(String OpIdModify) {
		this.setField(8, OpIdModify);
	}

	public String getOpidmodify() {
		return this.getField(8);
	}

	public void setWoid(String woId) {
		this.setField(9, woId);
	}

	public String getWoid() {
		return this.getField(9);
	}

	public void setCocode(String coCode) {
		this.setField(10, coCode);
	}

	public String getCocode() {
		return this.getField(10);
	}

	public void setIsnullsign(String IsNullsign) {
		this.setField(11, IsNullsign);
	}

	public String getIsnullsign() {
		return this.getField(11);
	}

}
