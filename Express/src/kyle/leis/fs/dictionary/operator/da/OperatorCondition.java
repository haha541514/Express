package kyle.leis.fs.dictionary.operator.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class OperatorCondition extends GeneralCondition {
	public OperatorCondition() {
		m_astrConditions = new String[6];
	}	
	
	public void setOpid(String opid) {
		this.setField(0, opid);
	}

	public String getOpid() {
		return this.getField(0);
	}

	public void setOpcode(String opCode) {
		this.setField(1, opCode);
	}

	public String getOpcode() {
		return this.getField(1);
	}

	public void setOppassword(String opPassword) {
		this.setField(2, opPassword);
	}

	public String getOppassword() {
		return this.getField(2);
	}

	public void setOpename(String opEname) {
		this.setField(3, opEname);
	}

	public String getOpename() {
		return this.getField(3);
	}

	public void setOpidnumber(String opIdnumber) {
		this.setField(4, opIdnumber);
	}

	public String getOpidnumber() {
		return this.getField(4);
	}

}
