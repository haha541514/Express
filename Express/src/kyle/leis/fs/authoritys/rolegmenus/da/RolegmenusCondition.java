package kyle.leis.fs.authoritys.rolegmenus.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class RolegmenusCondition extends GeneralCondition {
	public RolegmenusCondition() {
		m_astrConditions = new String[6];
	}	
	
	public void setRlcode(String rlCode) {
		this.setField(0, rlCode);
	}

	public String getRlcode() {
		return this.getField(0);
	}

	public void setGmcode(String gmCode) {
		this.setField(1, gmCode);
	}

	public String getGmcode() {
		return this.getField(1);
	}

	public void setRmopidcreator(String rmOpIdCreator) {
		this.setField(2, rmOpIdCreator);
	}

	public String getRmopidcreator() {
		return this.getField(2);
	}

	public void setRmopidmodifier(String rmOpIdModifier) {
		this.setField(3, rmOpIdModifier);
	}

	public String getRmopidmodifier() {
		return this.getField(3);
	}

	public void setRlstructurecode(String rlStructurecode) {
		this.setField(4, rlStructurecode);
	}

	public String getRlstructurecode() {
		return this.getField(4);
	}

	public void setIskcode(String iskCode) {
		this.setField(5, iskCode);
	}

	public String getIskcode() {
		return this.getField(5);
	}

}
