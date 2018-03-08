package kyle.leis.es.businessrule.manifest.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ManifestefcolumnColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ManifestefcolumnColumns() {
		m_astrColumns = new String[8];
	}
	
	public ManifestefcolumnColumns(Integer mefccomp_idMefcid, 
            Long mefccomp_idMefcode, Long mscMsccode, 
            String mscMsccolumnname, String mscMscsqlcolumnname, 
            String mefcMefccaptionname, String mefcMefcstructruecode, 
            String mefcMefcfixedcolumnformula){
		m_astrColumns = new String[8];
		setMefccomp_idmefcid(mefccomp_idMefcid);
		setMefccomp_idmefcode(mefccomp_idMefcode);
		setMscmsccode(mscMsccode);
		setMscmsccolumnname(mscMsccolumnname);
		setMscmscsqlcolumnname(mscMscsqlcolumnname);
		setMefcmefccaptionname(mefcMefccaptionname);
		setMefcmefcstructruecode(mefcMefcstructruecode);
		setMefcmefcfixedcolumnformula(mefcMefcfixedcolumnformula);
	}

	public void setMefccomp_idmefcid(Integer mefccomp_idMefcid) {
		this.setField(0, mefccomp_idMefcid);
	}

	public String getMefccomp_idmefcid() {
		return this.getField(0);
	}

	public void setMefccomp_idmefcode(Long mefccomp_idMefcode) {
		this.setField(1, mefccomp_idMefcode);
	}

	public String getMefccomp_idmefcode() {
		return this.getField(1);
	}

	public void setMscmsccode(Long mscMsccode) {
		this.setField(2, mscMsccode);
	}

	public String getMscmsccode() {
		return this.getField(2);
	}

	public void setMscmsccolumnname(String mscMsccolumnname) {
		this.setField(3, mscMsccolumnname);
	}

	public String getMscmsccolumnname() {
		return this.getField(3);
	}

	public void setMscmscsqlcolumnname(String mscMscsqlcolumnname) {
		this.setField(4, mscMscsqlcolumnname);
	}

	public String getMscmscsqlcolumnname() {
		return this.getField(4);
	}

	public void setMefcmefccaptionname(String mefcMefccaptionname) {
		this.setField(5, mefcMefccaptionname);
	}

	public String getMefcmefccaptionname() {
		return this.getField(5);
	}

	public void setMefcmefcstructruecode(String mefcMefcstructruecode) {
		this.setField(6, mefcMefcstructruecode);
	}

	public String getMefcmefcstructruecode() {
		return this.getField(6);
	}

	public void setMefcmefcfixedcolumnformula(String mefcMefcfixedcolumnformula) {
		this.setField(7, mefcMefcfixedcolumnformula);
	}

	public String getMefcmefcfixedcolumnformula() {
		return this.getField(7);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
