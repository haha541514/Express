package kyle.leis.es.businessrule.manifestexportformat.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class FixedcolumnformulaColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public FixedcolumnformulaColumns() {
		m_astrColumns = new String[5];
	}
	
	public FixedcolumnformulaColumns(String mecMefccaptionname, 
            String mecMefcfixedcolumnformula, Integer meccomp_idMefcid, 
            String mecMefcstructruecode, Long mefMefcode){
		m_astrColumns = new String[5];
		setMecmefccaptionname(mecMefccaptionname);
		setMecmefcfixedcolumnformula(mecMefcfixedcolumnformula);
		setMeccomp_idmefcid(meccomp_idMefcid);
		setMecmefcstructruecode(mecMefcstructruecode);
		setMefmefcode(mefMefcode);
	}

	public void setMecmefccaptionname(String mecMefccaptionname) {
		this.setField(0, mecMefccaptionname);
	}

	public String getMecmefccaptionname() {
		return this.getField(0);
	}

	public void setMecmefcfixedcolumnformula(String mecMefcfixedcolumnformula) {
		this.setField(1, mecMefcfixedcolumnformula);
	}

	public String getMecmefcfixedcolumnformula() {
		return this.getField(1);
	}

	public void setMeccomp_idmefcid(Integer meccomp_idMefcid) {
		this.setField(2, meccomp_idMefcid);
	}

	public String getMeccomp_idmefcid() {
		return this.getField(2);
	}

	public void setMecmefcstructruecode(String mecMefcstructruecode) {
		this.setField(3, mecMefcstructruecode);
	}

	public String getMecmefcstructruecode() {
		return this.getField(3);
	}

	public void setMefmefcode(Long mefMefcode) {
		this.setField(4, mefMefcode);
	}

	public String getMefmefcode() {
		return this.getField(4);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
