package kyle.leis.es.businessrule.productrule.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ChannelproductruleCondition extends GeneralCondition {
	public ChannelproductruleCondition() {
		m_astrConditions = new String[5];
	}	
	
	public void setCprid(String cprId) {
		this.setField(0, cprId);
	}

	public String getCprid() {
		return this.getField(0);
	}

	public void setBrid(String brId) {
		this.setField(1, brId);
	}

	public String getBrid() {
		return this.getField(1);
	}

	public void setZnid(String znId) {
		this.setField(2, znId);
	}

	public String getZnid() {
		return this.getField(2);
	}

	public void setZnvid(String znvId) {
		this.setField(3, znvId);
	}

	public String getZnvid() {
		return this.getField(3);
	}

	public void setChncode(String chnCode) {
		this.setField(4, chnCode);
	}

	public String getChncode() {
		return this.getField(4);
	}

}
