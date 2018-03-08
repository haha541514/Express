package kyle.leis.eo.finance.cashrecord.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class CashrecordCondition extends GeneralCondition {
	public CashrecordCondition() {
		m_astrConditions = new String[19];
	}	
	
	public void setCrid(String crId) {
		this.setField(0, crId);
	}

	public String getCrid() {
		return this.getField(0);
	}

	public void setCrlabelcode(String crLabelcode) {
		this.setField(1, crLabelcode);
	}

	public String getCrlabelcode() {
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

	public void setCrscode(String crsCode) {
		this.setField(4, crsCode);
	}

	public String getCrscode() {
		return this.getField(4);
	}

	public void setNotincrscode(String NotInCrsCode) {
		this.setField(5, NotInCrsCode);
	}

	public String getNotincrscode() {
		return this.getField(5);
	}

	public void setCrreceiptlabelcode(String crReceiptlabelcode) {
		this.setField(6, crReceiptlabelcode);
	}

	public String getCrreceiptlabelcode() {
		return this.getField(6);
	}

	public void setCrremark(String crRemark) {
		this.setField(7, crRemark);
	}

	public String getCrremark() {
		return this.getField(7);
	}

	public void setPtcode(String ptCode) {
		this.setField(8, ptCode);
	}

	public String getPtcode() {
		return this.getField(8);
	}

	public void setCkcode(String ckCode) {
		this.setField(9, ckCode);
	}

	public String getCkcode() {
		return this.getField(9);
	}

	public void setEecode(String eeCode) {
		this.setField(10, eeCode);
	}

	public String getEecode() {
		return this.getField(10);
	}

	public void setCocode(String coCode) {
		this.setField(11, coCode);
	}

	public String getCocode() {
		return this.getField(11);
	}

	public void setCrkcode(String crkCode) {
		this.setField(12, crkCode);
	}

	public String getCrkcode() {
		return this.getField(12);
	}

	public void setWoid(String woId) {
		this.setField(13, woId);
	}

	public String getWoid() {
		return this.getField(13);
	}

	public void setBegincarryoversign(String Begincarryoversign) {
		this.setField(14, Begincarryoversign);
	}

	public String getBegincarryoversign() {
		return this.getField(14);
	}

	public void setEndcarryoversigin(String Endcarryoversigin) {
		this.setField(15, Endcarryoversigin);
	}

	public String getEndcarryoversigin() {
		return this.getField(15);
	}

	public void setEestructurecode(String eeStructurecode) {
		this.setField(16, eeStructurecode);
	}

	public String getEestructurecode() {
		return this.getField(16);
	}

	public void setOpidsale(String OpIDSale) {
		this.setField(17, OpIDSale);
	}

	public String getOpidsale() {
		return this.getField(17);
	}

}
