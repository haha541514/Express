package kyle.leis.es.businessrule.manifestexportformat.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ManifestefcolumnCondition extends GeneralCondition {
	public ManifestefcolumnCondition() {
		m_astrConditions = new String[7];
	}	
	
	public void setMefccaptionname(String mefcCaptionname) {
		this.setField(0, mefcCaptionname);
	}

	public String getMefccaptionname() {
		return this.getField(0);
	}

	public void setMefcstructruecode(String mefcStructruecode) {
		this.setField(1, mefcStructruecode);
	}

	public String getMefcstructruecode() {
		return this.getField(1);
	}

	public void setMefcfixedcolumnformula(String mefcFixedcolumnformula) {
		this.setField(2, mefcFixedcolumnformula);
	}

	public String getMefcfixedcolumnformula() {
		return this.getField(2);
	}

	public void setMefcode(String mefCode) {
		this.setField(3, mefCode);
	}

	public String getMefcode() {
		return this.getField(3);
	}

	public void setMsccode(String mscCode) {
		this.setField(4, mscCode);
	}

	public String getMsccode() {
		return this.getField(4);
	}

	public void setMsccolumnename(String mscColumnename) {
		this.setField(5, mscColumnename);
	}

	public String getMsccolumnename() {
		return this.getField(5);
	}

}
