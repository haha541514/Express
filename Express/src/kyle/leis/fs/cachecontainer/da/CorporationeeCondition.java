package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class CorporationeeCondition extends GeneralCondition {
	public CorporationeeCondition() {
		m_astrConditions = new String[4];
	}	
	
	public void setEestructurecode(String eeStructurecode) {
		this.setField(0, eeStructurecode);
	}

	public String getEestructurecode() {
		return this.getField(0);
	}

	public void setStartbatchnumber(String startBatchnumber) {
		this.setField(1, startBatchnumber);
	}

	public String getStartbatchnumber() {
		return this.getField(1);
	}

	public void setEndbatchnumber(String EndBatchnumber) {
		this.setField(2, EndBatchnumber);
	}

	public String getEndbatchnumber() {
		return this.getField(2);
	}

}
