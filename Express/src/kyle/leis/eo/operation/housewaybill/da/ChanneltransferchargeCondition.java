package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ChanneltransferchargeCondition extends GeneralCondition {
	public ChanneltransferchargeCondition() {
		m_astrConditions = new String[4];
	}	
	
	public void setChncodesupplier(String chnCodeSupplier) {
		this.setField(0, chnCodeSupplier);
	}

	public String getChncodesupplier() {
		return this.getField(0);
	}

	public void setDayoccurdate(String dayoccurdate) {
		this.setField(1, dayoccurdate);
	}

	public String getDayoccurdate() {
		return this.getField(1);
	}

	public void setMonthoccurdate(String monthoccurdate) {
		this.setField(2, monthoccurdate);
	}

	public String getMonthoccurdate() {
		return this.getField(2);
	}

}
