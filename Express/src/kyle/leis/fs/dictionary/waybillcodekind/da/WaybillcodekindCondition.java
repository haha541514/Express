package kyle.leis.fs.dictionary.waybillcodekind.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class WaybillcodekindCondition extends GeneralCondition {
	public WaybillcodekindCondition() {
		m_astrConditions = new String[7];
	}	
	
	public void setBckcode(String bckCode) {
		this.setField(0, bckCode);
	}

	public String getBckcode() {
		return this.getField(0);
	}

	public void setBckname(String bckName) {
		this.setField(1, bckName);
	}

	public String getBckname() {
		return this.getField(1);
	}

	public void setBckename(String bckEname) {
		this.setField(2, bckEname);
	}

	public String getBckename() {
		return this.getField(2);
	}

	public void setBckgroupcode(String bckGroupcode) {
		this.setField(3, bckGroupcode);
	}

	public String getBckgroupcode() {
		return this.getField(3);
	}

	public void setBckbuildvaluesign(String bckBuildvaluesign) {
		this.setField(4, bckBuildvaluesign);
	}

	public String getBckbuildvaluesign() {
		return this.getField(4);
	}

	public void setBckfromwebservicesign(String bckFromwebservicesign) {
		this.setField(5, bckFromwebservicesign);
	}

	public String getBckfromwebservicesign() {
		return this.getField(5);
	}

	public void setLfcode(String lfCode) {
		this.setField(6, lfCode);
	}

	public String getLfcode() {
		return this.getField(6);
	}

}
