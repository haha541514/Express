package kyle.leis.fs.dictionary.enterpriseelement.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class EnterpriseelementCondition extends GeneralCondition {
	public EnterpriseelementCondition() {
		m_astrConditions = new String[16];
	}	
	
	public void setEecode(String eeCode) {
		this.setField(0, eeCode);
	}

	public String getEecode() {
		return this.getField(0);
	}

	public void setEelevel(String eeLevel) {
		this.setField(1, eeLevel);
	}

	public String getEelevel() {
		return this.getField(1);
	}

	public void setEestructurecode(String eeStructurecode) {
		this.setField(2, eeStructurecode);
	}

	public String getEestructurecode() {
		return this.getField(2);
	}

	public void setEesname(String eeSname) {
		this.setField(3, eeSname);
	}

	public String getEesname() {
		return this.getField(3);
	}

	public void setEeesname(String eeEsname) {
		this.setField(4, eeEsname);
	}

	public String getEeesname() {
		return this.getField(4);
	}

	public void setEepostcode(String eePostcode) {
		this.setField(5, eePostcode);
	}

	public String getEepostcode() {
		return this.getField(5);
	}

	public void setEeopidcreator(String eeOpIdCreator) {
		this.setField(6, eeOpIdCreator);
	}

	public String getEeopidcreator() {
		return this.getField(6);
	}

	public void setEeopidmodifier(String eeOpIdModifier) {
		this.setField(7, eeOpIdModifier);
	}

	public String getEeopidmodifier() {
		return this.getField(7);
	}

	public void setStartcreatedate(String startCreateDate) {
		this.setField(8, startCreateDate);
	}

	public String getStartcreatedate() {
		return this.getField(8);
	}

	public void setEndcreatedate(String endCreatedate) {
		this.setField(9, endCreatedate);
	}

	public String getEndcreatedate() {
		return this.getField(9);
	}

	public void setStartmodifydate(String startModifyDate) {
		this.setField(10, startModifyDate);
	}

	public String getStartmodifydate() {
		return this.getField(10);
	}

	public void setEndmodifydate(String endModifydate) {
		this.setField(11, endModifydate);
	}

	public String getEndmodifydate() {
		return this.getField(11);
	}

	public void setDtcode(String dtCode) {
		this.setField(12, dtCode);
	}

	public String getDtcode() {
		return this.getField(12);
	}

	public void setRgcode(String rgCode) {
		this.setField(13, rgCode);
	}

	public String getRgcode() {
		return this.getField(13);
	}

	public void setEekcode(String eekCode) {
		this.setField(14, eekCode);
	}

	public String getEekcode() {
		return this.getField(14);
	}
	
	public void setLikeeestructurecode(String Likeeestructurecode) {
		this.setField(15, Likeeestructurecode);
	}

	public String getLikeeestructurecode() {
		return this.getField(15);
	}	
}
