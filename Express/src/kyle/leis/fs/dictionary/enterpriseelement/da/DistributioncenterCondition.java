package kyle.leis.fs.dictionary.enterpriseelement.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class DistributioncenterCondition extends GeneralCondition {
	public DistributioncenterCondition() {
		m_astrConditions = new String[5];
	}	
	
	public void setDceecode(String Dceecode) {
		this.setField(0, Dceecode);
	}

	public String getDceecode() {
		return this.getField(0);
	}

	public void setDcbreecode(String Dcbreecode) {
		this.setField(1, Dcbreecode);
	}

	public String getDcbreecode() {
		return this.getField(1);
	}

	public void setDtbcode(String Dtbcode) {
		this.setField(2, Dtbcode);
	}

	public String getDtbcode() {
		return this.getField(2);
	}

	public void setEekcode(String Eekcode) {
		this.setField(3, Eekcode);
	}

	public String getEekcode() {
		return this.getField(3);
	}

}
