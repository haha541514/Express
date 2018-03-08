package kyle.leis.es.bulletin.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class WechatitemCondition extends GeneralCondition {
	public WechatitemCondition() {
		m_astrConditions = new String[3];
	}	
	
	public void setWciid(String wciId) {
		this.setField(0, wciId);
	}

	public String getWciid() {
		return this.getField(0);
	}

	public void setBlid(String blId) {
		this.setField(1, blId);
	}

	public String getBlid() {
		return this.getField(1);
	}

}
