package kyle.leis.es.price.zone.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ZonevalueCondition extends GeneralCondition {
	public ZonevalueCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setZnvid(String znvId) {
		this.setField(0, znvId);
	}

	public String getZnvid() {
		return this.getField(0);
	}

	public void setZncode(String znCode) {
		this.setField(1, znCode);
	}

	public String getZncode() {
		return this.getField(1);
	}

}
