package kyle.leis.es.businessrule.manifestexportformat.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ManifestefcolumnColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ManifestefcolumnColumns() {
		m_astrColumns = new String[10];
	}
	
	public ManifestefcolumnColumns(Integer meccomp_idMefcid, 
            Long meccomp_idMefcode, String mecMefccaptionname, 
            String mecMefcstructruecode, String mecMefcfixedcolumnformula, 
            Long mscMsccode, String mscMsccolumnname, 
            String mscMscsqlcolumnname, String mscMsccolumnename, 
            Long mefMefcode){
		m_astrColumns = new String[10];
		setMeccomp_idmefcid(meccomp_idMefcid);
		setMeccomp_idmefcode(meccomp_idMefcode);
		setMecmefccaptionname(mecMefccaptionname);
		setMecmefcstructruecode(mecMefcstructruecode);
		setMecmefcfixedcolumnformula(mecMefcfixedcolumnformula);
		setMscmsccode(mscMsccode);
		setMscmsccolumnname(mscMsccolumnname);
		setMscmscsqlcolumnname(mscMscsqlcolumnname);
		setMscmsccolumnename(mscMsccolumnename);
		setMefmefcode(mefMefcode);
	}

	public void setMeccomp_idmefcid(Integer meccomp_idMefcid) {
		this.setField(0, meccomp_idMefcid);
	}

	public String getMeccomp_idmefcid() {
		return this.getField(0);
	}

	public void setMeccomp_idmefcode(Long meccomp_idMefcode) {
		this.setField(1, meccomp_idMefcode);
	}

	public String getMeccomp_idmefcode() {
		return this.getField(1);
	}

	public void setMecmefccaptionname(String mecMefccaptionname) {
		this.setField(2, mecMefccaptionname);
	}

	public String getMecmefccaptionname() {
		return this.getField(2);
	}

	public void setMecmefcstructruecode(String mecMefcstructruecode) {
		this.setField(3, mecMefcstructruecode);
	}

	public String getMecmefcstructruecode() {
		return this.getField(3);
	}

	public void setMecmefcfixedcolumnformula(String mecMefcfixedcolumnformula) {
		this.setField(4, mecMefcfixedcolumnformula);
	}

	public String getMecmefcfixedcolumnformula() {
		return this.getField(4);
	}

	public void setMscmsccode(Long mscMsccode) {
		this.setField(5, mscMsccode);
	}

	public String getMscmsccode() {
		return this.getField(5);
	}

	public void setMscmsccolumnname(String mscMsccolumnname) {
		this.setField(6, mscMsccolumnname);
	}

	public String getMscmsccolumnname() {
		return this.getField(6);
	}

	public void setMscmscsqlcolumnname(String mscMscsqlcolumnname) {
		this.setField(7, mscMscsqlcolumnname);
	}

	public String getMscmscsqlcolumnname() {
		return this.getField(7);
	}

	public void setMscmsccolumnename(String mscMsccolumnename) {
		this.setField(8, mscMsccolumnename);
	}

	public String getMscmsccolumnename() {
		return this.getField(8);
	}

	public void setMefmefcode(Long mefMefcode) {
		this.setField(9, mefMefcode);
	}

	public String getMefmefcode() {
		return this.getField(9);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
