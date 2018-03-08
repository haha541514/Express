package kyle.leis.fs.authoritys.role.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class RoleCondition extends GeneralCondition {
	public RoleCondition() {
		m_astrConditions = new String[11];
	}	
	
	public void setRlcode(String rlCode) {
		this.setField(0, rlCode);
	}

	public String getRlcode() {
		return this.getField(0);
	}

	public void setRlname(String rlName) {
		this.setField(1, rlName);
	}

	public String getRlname() {
		return this.getField(1);
	}

	public void setRladministratorsign(String rlAdministratorsign) {
		this.setField(2, rlAdministratorsign);
	}

	public String getRladministratorsign() {
		return this.getField(2);
	}

	public void setRlopidcreator(String rlOpIdCreator) {
		this.setField(3, rlOpIdCreator);
	}

	public String getRlopidcreator() {
		return this.getField(3);
	}

	public void setStartrlcreatedate(String startRlCreatedate) {
		this.setField(4, startRlCreatedate);
	}

	public String getStartrlcreatedate() {
		return this.getField(4);
	}

	public void setEndrlcreatedate(String endRlCreatedate) {
		this.setField(5, endRlCreatedate);
	}

	public String getEndrlcreatedate() {
		return this.getField(5);
	}

	public void setRlopidmodifier(String rlOpIdModifier) {
		this.setField(6, rlOpIdModifier);
	}

	public String getRlopidmodifier() {
		return this.getField(6);
	}

	public void setStartrlmodifydate(String startRlModifydate) {
		this.setField(7, startRlModifydate);
	}

	public String getStartrlmodifydate() {
		return this.getField(7);
	}

	public void setEndrlmodifydate(String endRlModifydate) {
		this.setField(8, endRlModifydate);
	}

	public String getEndrlmodifydate() {
		return this.getField(8);
	}

	public void setRlstructurecode(String rlStructurecode) {
		this.setField(9, rlStructurecode);
	}

	public String getRlstructurecode() {
		return this.getField(9);
	}

	public void setIsk_code(String isk_code) {
		this.setField(10, isk_code);
	}

	public String getIsk_code() {
		return this.getField(10);
	}

}
