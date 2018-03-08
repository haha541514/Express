package kyle.leis.eo.operation.manifest.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ManifestCondition extends GeneralCondition {
	public ManifestCondition() {
		m_astrConditions = new String[8];
	}	
	
	public void setMfcode(String mfCode) {
		this.setField(0, mfCode);
	}

	public String getMfcode() {
		return this.getField(0);
	}

	public void setOpidmodify(String OpIdModify) {
		this.setField(1, OpIdModify);
	}

	public String getOpidmodify() {
		return this.getField(1);
	}

	public void setSscode(String SsCode) {
		this.setField(2, SsCode);
	}

	public String getSscode() {
		return this.getField(2);
	}

	public void setModifystartdate(String ModifyStartdate) {
		this.setField(3, ModifyStartdate);
	}

	public String getModifystartdate() {
		return this.getField(3);
	}

	public void setModifyenddate(String ModifyEnddate) {
		this.setField(4, ModifyEnddate);
	}

	public String getModifyenddate() {
		return this.getField(4);
	}

	public void setCwserverewbcode(String cwServerewbcode) {
		this.setField(5, cwServerewbcode);
	}

	public String getCwserverewbcode() {
		return this.getField(5);
	}
	
	public void setEe_structurecode(String Ee_structurecode) {
		this.setField(6, Ee_structurecode);
	}

	public String getEe_structurecode() {
		return this.getField(6);
	}	
}
