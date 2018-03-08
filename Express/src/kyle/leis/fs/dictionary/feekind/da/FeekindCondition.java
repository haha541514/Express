package kyle.leis.fs.dictionary.feekind.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class FeekindCondition extends GeneralCondition {
	public FeekindCondition() {
		m_astrConditions = new String[7];
	}	
	
	public void setFkcode(String fkCode) {
		this.setField(0, fkCode);
	}

	public String getFkcode() {
		return this.getField(0);
	}

	public void setFkname(String fkName) {
		this.setField(1, fkName);
	}

	public String getFkname() {
		return this.getField(1);
	}

	public void setFkmanualmodifysign(String fkManualmodifysign) {
		this.setField(2, fkManualmodifysign);
	}

	public String getFkmanualmodifysign() {
		return this.getField(2);
	}

	public void setFkbasesign(String fkBasesign) {
		this.setField(3, fkBasesign);
	}

	public String getFkbasesign() {
		return this.getField(3);
	}

	public void setEstcode(String estCode) {
		this.setField(4, estCode);
	}

	public String getEstcode() {
		return this.getField(4);
	}

	public void setEstename(String estEname) {
		this.setField(5, estEname);
	}

	public String getEstename() {
		return this.getField(5);
	}

	public void setSscode(String ssCode) {
		this.setField(6, ssCode);
	}

	public String getSscode() {
		return this.getField(6);
	}

}
