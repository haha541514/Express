package kyle.leis.es.company.channel.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ChanneladdressCondition extends GeneralCondition {
	public ChanneladdressCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setChncode(String chnCode) {
		this.setField(0, chnCode);
	}

	public String getChncode() {
		return this.getField(0);
	}

}
