package kyle.leis.fs.dictionary.customscargo.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class MemorydeclarenameCondition extends GeneralCondition {
	public MemorydeclarenameCondition() {
		m_astrConditions = new String[5];
	}	
	
	public void setMdnename(String mdnEname) {
		this.setField(0, mdnEname);
	}

	public String getMdnename() {
		return this.getField(0);
	}

	public void setMdnname(String mdnName) {
		this.setField(1, mdnName);
	}

	public String getMdnname() {
		return this.getField(1);
	}

	public void setMdnlabelcode(String mdnLabelcode) {
		this.setField(2, mdnLabelcode);
	}

	public String getMdnlabelcode() {
		return this.getField(2);
	}

	public void setMdncode(String mdnCode) {
		this.setField(3, mdnCode);
	}

	public String getMdncode() {
		return this.getField(3);
	}

}
