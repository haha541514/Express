package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ChanneltransferweightCondition extends GeneralCondition {
	public ChanneltransferweightCondition() {
		m_astrConditions = new String[3];
	}	
	
	public void setChncodesupplier(String chnCodeSupplier) {
		this.setField(0, chnCodeSupplier);
	}

	public String getChncodesupplier() {
		return this.getField(0);
	}

	public void setCreatedate(String CreateDate) {
		this.setField(1, CreateDate);
	}

	public String getCreatedate() {
		return this.getField(1);
	}

}
