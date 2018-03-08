package kyle.leis.es.businessrule.weightrule.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class WeightrulevalueCondition extends GeneralCondition {
	public WeightrulevalueCondition() {
		m_astrConditions = new String[4];
	}	
	
	public void setBrid(String brId) {
		this.setField(0, brId);
	}

	public String getBrid() {
		return this.getField(0);
	}

	public void setBrvid(String brvId) {
		this.setField(1, brvId);
	}

	public String getBrvid() {
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
