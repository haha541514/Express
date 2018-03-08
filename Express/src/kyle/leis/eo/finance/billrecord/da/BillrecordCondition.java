package kyle.leis.eo.finance.billrecord.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class BillrecordCondition extends GeneralCondition {
	public BillrecordCondition() {
		m_astrConditions = new String[16];
	}	
	
	public void setBrid(String brId) {
		this.setField(0, brId);
	}

	public String getBrid() {
		return this.getField(0);
	}

	public void setBrlablecode(String brLablecode) {
		this.setField(1, brLablecode);
	}

	public String getBrlablecode() {
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

	public void setBrscode(String brsCode) {
		this.setField(4, brsCode);
	}

	public String getBrscode() {
		return this.getField(4);
	}

	public void setNotinbrscode(String NotInBrsCode) {
		this.setField(5, NotInBrsCode);
	}

	public String getNotinbrscode() {
		return this.getField(5);
	}

	public void setEecode(String eeCode) {
		this.setField(6, eeCode);
	}

	public String getEecode() {
		return this.getField(6);
	}

	public void setCocode(String coCode) {
		this.setField(7, coCode);
	}

	public String getCocode() {
		return this.getField(7);
	}

	public void setBkcode(String bkCode) {
		this.setField(8, bkCode);
	}

	public String getBkcode() {
		return this.getField(8);
	}

	public void setWoid(String woId) {
		this.setField(9, woId);
	}

	public String getWoid() {
		return this.getField(9);
	}

	public void setBegincarryoversign(String Begincarryoversign) {
		this.setField(10, Begincarryoversign);
	}

	public String getBegincarryoversign() {
		return this.getField(10);
	}

	public void setEndcarryoversigin(String Endcarryoversigin) {
		this.setField(11, Endcarryoversigin);
	}

	public String getEndcarryoversigin() {
		return this.getField(11);
	}

	public void setEestructurecode(String eeStructurecode) {
		this.setField(12, eeStructurecode);
	}

	public String getEestructurecode() {
		return this.getField(12);
	}

	public void setSbrid(String sbrId) {
		this.setField(13, sbrId);
	}

	public String getSbrid() {
		return this.getField(13);
	}

	public void setSbrlabelcode(String sbrLabelcode) {
		this.setField(14, sbrLabelcode);
	}

	public String getSbrlabelcode() {
		return this.getField(14);
	}

}
