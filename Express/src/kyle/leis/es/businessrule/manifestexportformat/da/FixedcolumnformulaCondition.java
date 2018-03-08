package kyle.leis.es.businessrule.manifestexportformat.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class FixedcolumnformulaCondition extends GeneralCondition {
	public FixedcolumnformulaCondition() {
		m_astrConditions = new String[5];
	}	
	
	public void setMefccaptionname(String mefcCaptionname) {
		this.setField(0, mefcCaptionname);
	}

	public String getMefccaptionname() {
		return this.getField(0);
	}

	public void setMefcfixedcolumnformula(String mefcFixedcolumnformula) {
		this.setField(1, mefcFixedcolumnformula);
	}

	public String getMefcfixedcolumnformula() {
		return this.getField(1);
	}

	public void setMefcode(String mefCode) {
		this.setField(2, mefCode);
	}

	public String getMefcode() {
		return this.getField(2);
	}

	public void setMefcstructruecode(String mefcStructruecode) {
		this.setField(3, mefcStructruecode);
	}

	public String getMefcstructruecode() {
		return this.getField(3);
	}

}
