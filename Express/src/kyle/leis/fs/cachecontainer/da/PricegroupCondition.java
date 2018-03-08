package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class PricegroupCondition extends GeneralCondition {
	public PricegroupCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setPgcommonsign(String Pgcommonsign) {
		this.setField(0, Pgcommonsign);
	}

	public String getPgcommonsign() {
		return this.getField(0);
	}

}
