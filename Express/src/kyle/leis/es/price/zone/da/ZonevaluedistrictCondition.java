package kyle.leis.es.price.zone.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ZonevaluedistrictCondition extends GeneralCondition {
	public ZonevaluedistrictCondition() {
		m_astrConditions = new String[5];
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

	public void setDtcode(String dtCode) {
		this.setField(2, dtCode);
	}

	public String getDtcode() {
		return this.getField(2);
	}

	public void setZfcode(String zfCode) {
		this.setField(3, zfCode);
	}

	public String getZfcode() {
		return this.getField(3);
	}

}
