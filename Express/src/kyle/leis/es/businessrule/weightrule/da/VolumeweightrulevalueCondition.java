package kyle.leis.es.businessrule.weightrule.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class VolumeweightrulevalueCondition extends GeneralCondition {
	public VolumeweightrulevalueCondition() {
		m_astrConditions = new String[4];
	}	
	
	public void setBrid(String brId) {
		this.setField(0, brId);
	}

	public String getBrid() {
		return this.getField(0);
	}

	public void setVwrvid(String vwrvId) {
		this.setField(1, vwrvId);
	}

	public String getVwrvid() {
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

}
