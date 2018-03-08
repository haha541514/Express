package kyle.leis.es.businessrule.manifest.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ManifestexportformatCondition extends GeneralCondition {
	public ManifestexportformatCondition() {
		m_astrConditions = new String[11];
	}	
	
	public void setMefcode(String mefCode) {
		this.setField(0, mefCode);
	}

	public String getMefcode() {
		return this.getField(0);
	}

	public void setMefname(String mefName) {
		this.setField(1, mefName);
	}

	public String getMefname() {
		return this.getField(1);
	}

	public void setOpcid(String opcId) {
		this.setField(2, opcId);
	}

	public String getOpcid() {
		return this.getField(2);
	}

	public void setOpcname(String opcName) {
		this.setField(3, opcName);
	}

	public String getOpcname() {
		return this.getField(3);
	}

	public void setOpmid(String opmId) {
		this.setField(4, opmId);
	}

	public String getOpmid() {
		return this.getField(4);
	}

	public void setOpmname(String opmName) {
		this.setField(5, opmName);
	}

	public String getOpmname() {
		return this.getField(5);
	}

	public void setStartmefcreatedate(String StartMefCreatedate) {
		this.setField(6, StartMefCreatedate);
	}

	public String getStartmefcreatedate() {
		return this.getField(6);
	}

	public void setEndmefcreatedate(String EndMefCreatedate) {
		this.setField(7, EndMefCreatedate);
	}

	public String getEndmefcreatedate() {
		return this.getField(7);
	}

	public void setStartmefmodifydate(String StartMefModifydate) {
		this.setField(8, StartMefModifydate);
	}

	public String getStartmefmodifydate() {
		return this.getField(8);
	}

	public void setEndmefmodifydate(String EndMefModifydate) {
		this.setField(9, EndMefModifydate);
	}

	public String getEndmefmodifydate() {
		return this.getField(9);
	}

}
