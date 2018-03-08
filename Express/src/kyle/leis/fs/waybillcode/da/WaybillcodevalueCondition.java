package kyle.leis.fs.waybillcode.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class WaybillcodevalueCondition extends GeneralCondition {
	public WaybillcodevalueCondition() {
		m_astrConditions = new String[5];
	}	
	
	public void setBcvid(String bcvId) {
		this.setField(0, bcvId);
	}

	public String getBcvid() {
		return this.getField(0);
	}

	public void setBcid(String bcId) {
		this.setField(1, bcId);
	}

	public String getBcid() {
		return this.getField(1);
	}

	public void setBckcode(String bckCode) {
		this.setField(2, bckCode);
	}

	public String getBckcode() {
		return this.getField(2);
	}

	public void setBcvlabelcode(String bcvLabelcode) {
		this.setField(3, bcvLabelcode);
	}

	public String getBcvlabelcode() {
		return this.getField(3);
	}
	
	public void setBcscscode(String bcsCscode) {
		this.setField(4, bcsCscode);
	}

	public String getBcscscode() {
		return this.getField(4);
	}		

}
