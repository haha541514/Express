package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class SumweightcheckweightCondition extends GeneralCondition {
	public SumweightcheckweightCondition() {
		m_astrConditions = new String[4];
	}	
	
	public void setBwcodeweightcheck(String bwcodeweightcheck) {
		this.setField(0, bwcodeweightcheck);
	}

	public String getBwcodeweightcheck() {
		return this.getField(0);
	}

	public void setBwcodearrival(String bwcodearrival) {
		this.setField(1, bwcodearrival);
	}

	public String getBwcodearrival() {
		return this.getField(1);
	}

	public void setIsnullsign(String Isnullsign) {
		this.setField(2, Isnullsign);
	}

	public String getIsnullsign() {
		return this.getField(2);
	}

}
