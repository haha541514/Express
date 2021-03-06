package kyle.leis.es.businessrule.maniexport.da;


import kyle.common.dbaccess.query.GeneralCondition;

public class ManifestexportformatCondition extends GeneralCondition {
	public ManifestexportformatCondition() {
		m_astrConditions = new String[12];
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

	public void setOpcid(String opcid) {
		this.setField(2, opcid);
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

	public void setSscode(String ssCode) {
		this.setField(6, ssCode);
	}

	public String getSscode() {
		return this.getField(6);
	}

	public void setStartmefcreatedate(String Startmefcreatedate) {
		this.setField(7, Startmefcreatedate);
	}

	public String getStartmefcreatedate() {
		return this.getField(7);
	}

	public void setEndmefcreatedate(String Endmefcreatedate) {
		this.setField(8, Endmefcreatedate);
	}

	public String getEndmefcreatedate() {
		return this.getField(8);
	}

	public void setStartmefmodifydate(String Startmefmodifydate) {
		this.setField(9, Startmefmodifydate);
	}

	public String getStartmefmodifydate() {
		return this.getField(9);
	}

	public void setEndmefmodifydate(String Endmefmodifydate) {
		this.setField(10, Endmefmodifydate);
	}

	public String getEndmefmodifydate() {
		return this.getField(10);
	}

}
