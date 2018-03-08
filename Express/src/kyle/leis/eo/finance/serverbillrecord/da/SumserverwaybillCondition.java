package kyle.leis.eo.finance.serverbillrecord.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class SumserverwaybillCondition extends GeneralCondition {
	public SumserverwaybillCondition() {
		m_astrConditions = new String[5];
	}	
	
	public void setSwbserverewbcode(String swbServerewbcode) {
		this.setField(0, swbServerewbcode);
	}

	public String getSwbserverewbcode() {
		return this.getField(0);
	}

	public void setInfkcode(String InFkcode) {
		this.setField(1, InFkcode);
	}

	public String getInfkcode() {
		return this.getField(1);
	}

	public void setNotinfkcode(String NotInFkcode) {
		this.setField(2, NotInFkcode);
	}

	public String getNotinfkcode() {
		return this.getField(2);
	}
	
	public void setSwbreferencecode(String swbReferencecode) {
		this.setField(3, swbReferencecode);
	}

	public String getSwbreferencecode() {
		return this.getField(3);
	}	
}
