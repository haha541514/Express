package kyle.leis.es.price.adjustiveprice.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class AdjustivepricevalueCondition extends GeneralCondition {
	public AdjustivepricevalueCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setEpcode(String epCode) {
		this.setField(0, epCode);
	}

	public String getEpcode() {
		return this.getField(0);
	}

	public void setApvid(String apvId) {
		this.setField(1, apvId);
	}

	public String getApvid() {
		return this.getField(1);
	}

}
