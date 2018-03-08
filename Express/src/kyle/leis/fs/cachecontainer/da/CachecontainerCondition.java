package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class CachecontainerCondition extends GeneralCondition {
	public CachecontainerCondition() {
		m_astrConditions = new String[5];
	}	
	
	public void setCccode(String CcCode) {
		this.setField(0, CcCode);
	}

	public String getCccode() {
		return this.getField(0);
	}

	public void setCcname(String ccName) {
		this.setField(1, ccName);
	}

	public String getCcname() {
		return this.getField(1);
	}

	public void setIskcode(String IskCode) {
		this.setField(2, IskCode);
	}

	public String getIskcode() {
		return this.getField(2);
	}

	public void setCcbatchnumber(String ccBatchnumber) {
		this.setField(3, ccBatchnumber);
	}

	public String getCcbatchnumber() {
		return this.getField(3);
	}

	public void setCctotalsign(String ccTotalsign) {
		this.setField(4, ccTotalsign);
	}

	public String getCctotalsign() {
		return this.getField(4);
	}

}
