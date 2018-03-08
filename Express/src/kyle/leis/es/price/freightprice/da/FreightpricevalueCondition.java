package kyle.leis.es.price.freightprice.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class FreightpricevalueCondition extends GeneralCondition {
	public FreightpricevalueCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setEpcode(String epCode) {
		this.setField(0, epCode);
	}

	public String getEpcode() {
		return this.getField(0);
	}

	public void setFpvid(String fpvId) {
		this.setField(1, fpvId);
	}

	public String getFpvid() {
		return this.getField(1);
	}

}
