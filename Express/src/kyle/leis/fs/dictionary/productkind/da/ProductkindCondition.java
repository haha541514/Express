package kyle.leis.fs.dictionary.productkind.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ProductkindCondition extends GeneralCondition {
	public ProductkindCondition() {
		m_astrConditions = new String[7];
	}	
	
	public void setPkcode(String pkCode) {
		this.setField(0, pkCode);
	}

	public String getPkcode() {
		return this.getField(0);
	}

	public void setSscode(String ssCode) {
		this.setField(1, ssCode);
	}

	public String getSscode() {
		return this.getField(1);
	}

	public void setPkename(String pkEname) {
		this.setField(2, pkEname);
	}

	public String getPkename() {
		return this.getField(2);
	}
	
	public void setPkName(String PkName) {
		this.setField(3, PkName);
	}

	public String getPkName() {
		return this.getField(3);
	}
	
	public void setPkSname(String PkSname) {
		this.setField(4, PkSname);
	}

	public String getPkSname() {
		return this.getField(4);
	}
	
	public void setPkSename(String PkSename) {
		this.setField(5, PkSename);
	}

	public String getPkSename() {
		return this.getField(5);
	}	
	
	public void setEestructurecode(String Eestructurecode) {
		this.setField(6, Eestructurecode);
	}

	public String getEestructurecode() {
		return this.getField(6);
	}		
}
