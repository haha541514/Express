package kyle.leis.es.price.zone.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ZonezfcodeCondition extends GeneralCondition {
	public ZonezfcodeCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setZnzfcode(String znzfCode) {
		this.setField(0, znzfCode);
	}

	public String getZnzfcode() {
		return this.getField(0);
	}

}
