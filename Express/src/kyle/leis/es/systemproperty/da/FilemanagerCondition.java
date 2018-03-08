package kyle.leis.es.systemproperty.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class FilemanagerCondition extends GeneralCondition {
	public FilemanagerCondition() {
		m_astrConditions = new String[4];
	}	
	
	public void setName(String name) {
		this.setField(0, name);
	}

	public String getName() {
		return this.getField(0);
	}

	public void setCreatedate(String createdate) {
		this.setField(1, createdate);
	}

	public String getCreatedate() {
		return this.getField(1);
	}

	public void setEndcreatedate(String endcreatedate) {
		this.setField(2, endcreatedate);
	}

	public String getEndcreatedate() {
		return this.getField(2);
	}

}
