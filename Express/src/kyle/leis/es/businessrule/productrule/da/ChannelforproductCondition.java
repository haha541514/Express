package kyle.leis.es.businessrule.productrule.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ChannelforproductCondition extends GeneralCondition {
	public ChannelforproductCondition() {
		m_astrConditions = new String[4];
	}	
	
	public void setStartdate(String StartDate) {
		this.setField(0, StartDate);
	}

	public String getStartdate() {
		return this.getField(0);
	}

	public void setEnddate(String EndDate) {
		this.setField(1, EndDate);
	}

	public String getEnddate() {
		return this.getField(1);
	}

	public void setChncode(String chncode) {
		this.setField(2, chncode);
	}

	public String getChncode() {
		return this.getField(2);
	}

}
