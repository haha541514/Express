package kyle.leis.fs.dictionary.district.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class DistrictjdbcColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public DistrictjdbcColumns() {
		m_astrColumns = new String[30];
	}
	
	public DistrictjdbcColumns(String diDt_code, 
            String diDt_countcode, String diDt_hubcode, 
            String diDt_name, String diDt_ename, 
            String diDt_statecode, String diDt_statename, 
            String diDt_grade, String diDt_startpostcode, 
            String diDt_endpostcode, String diDt_op_code_creator, 
            String diDt_createdate, String diDt_op_code_modifier, 
            String diDt_modifydate, String diDt_remark, 
            String diDt_startcitysign, String diDt_elevatedrisksign, 
            String diDt_restrictedsign, String ddDt_code, 
            String ddDt_countcode, String ddDt_name, 
            String ddDt_hubcode, String ddDt_ename, 
            String ddDt_op_code_creator, String ddDt_op_code_modifier, 
            String dkDk_code, String dkDk_name, 
            String dkDk_ename, String crOp_name, 
            String moOp_name){
		m_astrColumns = new String[30];
		setDidt_code(diDt_code);
		setDidt_countcode(diDt_countcode);
		setDidt_hubcode(diDt_hubcode);
		setDidt_name(diDt_name);
		setDidt_ename(diDt_ename);
		setDidt_statecode(diDt_statecode);
		setDidt_statename(diDt_statename);
		setDidt_grade(diDt_grade);
		setDidt_startpostcode(diDt_startpostcode);
		setDidt_endpostcode(diDt_endpostcode);
		setDidt_op_code_creator(diDt_op_code_creator);
		setDidt_createdate(diDt_createdate);
		setDidt_op_code_modifier(diDt_op_code_modifier);
		setDidt_modifydate(diDt_modifydate);
		setDidt_remark(diDt_remark);
		setDidt_startcitysign(diDt_startcitysign);
		setDidt_elevatedrisksign(diDt_elevatedrisksign);
		setDidt_restrictedsign(diDt_restrictedsign);
		setDddt_code(ddDt_code);
		setDddt_countcode(ddDt_countcode);
		setDddt_name(ddDt_name);
		setDddt_hubcode(ddDt_hubcode);
		setDddt_ename(ddDt_ename);
		setDddt_op_code_creator(ddDt_op_code_creator);
		setDddt_op_code_modifier(ddDt_op_code_modifier);
		setDkdk_code(dkDk_code);
		setDkdk_name(dkDk_name);
		setDkdk_ename(dkDk_ename);
		setCrop_name(crOp_name);
		setMoop_name(moOp_name);
	}

	public void setDidt_code(String diDt_code) {
		this.setField(0, diDt_code);
	}

	public String getDidt_code() {
		return this.getField(0);
	}

	public void setDidt_countcode(String diDt_countcode) {
		this.setField(1, diDt_countcode);
	}

	public String getDidt_countcode() {
		return this.getField(1);
	}

	public void setDidt_hubcode(String diDt_hubcode) {
		this.setField(2, diDt_hubcode);
	}

	public String getDidt_hubcode() {
		return this.getField(2);
	}

	public void setDidt_name(String diDt_name) {
		this.setField(3, diDt_name);
	}

	public String getDidt_name() {
		return this.getField(3);
	}

	public void setDidt_ename(String diDt_ename) {
		this.setField(4, diDt_ename);
	}

	public String getDidt_ename() {
		return this.getField(4);
	}

	public void setDidt_statecode(String diDt_statecode) {
		this.setField(5, diDt_statecode);
	}

	public String getDidt_statecode() {
		return this.getField(5);
	}

	public void setDidt_statename(String diDt_statename) {
		this.setField(6, diDt_statename);
	}

	public String getDidt_statename() {
		return this.getField(6);
	}

	public void setDidt_grade(String diDt_grade) {
		this.setField(7, diDt_grade);
	}

	public String getDidt_grade() {
		return this.getField(7);
	}

	public void setDidt_startpostcode(String diDt_startpostcode) {
		this.setField(8, diDt_startpostcode);
	}

	public String getDidt_startpostcode() {
		return this.getField(8);
	}

	public void setDidt_endpostcode(String diDt_endpostcode) {
		this.setField(9, diDt_endpostcode);
	}

	public String getDidt_endpostcode() {
		return this.getField(9);
	}

	public void setDidt_op_code_creator(String diDt_op_code_creator) {
		this.setField(10, diDt_op_code_creator);
	}

	public String getDidt_op_code_creator() {
		return this.getField(10);
	}

	public void setDidt_createdate(String diDt_createdate) {
		this.setField(11, diDt_createdate);
	}

	public String getDidt_createdate() {
		return this.getField(11);
	}

	public void setDidt_op_code_modifier(String diDt_op_code_modifier) {
		this.setField(12, diDt_op_code_modifier);
	}

	public String getDidt_op_code_modifier() {
		return this.getField(12);
	}

	public void setDidt_modifydate(String diDt_modifydate) {
		this.setField(13, diDt_modifydate);
	}

	public String getDidt_modifydate() {
		return this.getField(13);
	}

	public void setDidt_remark(String diDt_remark) {
		this.setField(14, diDt_remark);
	}

	public String getDidt_remark() {
		return this.getField(14);
	}

	public void setDidt_startcitysign(String diDt_startcitysign) {
		this.setField(15, diDt_startcitysign);
	}

	public String getDidt_startcitysign() {
		return this.getField(15);
	}

	public void setDidt_elevatedrisksign(String diDt_elevatedrisksign) {
		this.setField(16, diDt_elevatedrisksign);
	}

	public String getDidt_elevatedrisksign() {
		return this.getField(16);
	}

	public void setDidt_restrictedsign(String diDt_restrictedsign) {
		this.setField(17, diDt_restrictedsign);
	}

	public String getDidt_restrictedsign() {
		return this.getField(17);
	}

	public void setDddt_code(String ddDt_code) {
		this.setField(18, ddDt_code);
	}

	public String getDddt_code() {
		return this.getField(18);
	}

	public void setDddt_countcode(String ddDt_countcode) {
		this.setField(19, ddDt_countcode);
	}

	public String getDddt_countcode() {
		return this.getField(19);
	}

	public void setDddt_name(String ddDt_name) {
		this.setField(20, ddDt_name);
	}

	public String getDddt_name() {
		return this.getField(20);
	}

	public void setDddt_hubcode(String ddDt_hubcode) {
		this.setField(21, ddDt_hubcode);
	}

	public String getDddt_hubcode() {
		return this.getField(21);
	}

	public void setDddt_ename(String ddDt_ename) {
		this.setField(22, ddDt_ename);
	}

	public String getDddt_ename() {
		return this.getField(22);
	}

	public void setDddt_op_code_creator(String ddDt_op_code_creator) {
		this.setField(23, ddDt_op_code_creator);
	}

	public String getDddt_op_code_creator() {
		return this.getField(23);
	}

	public void setDddt_op_code_modifier(String ddDt_op_code_modifier) {
		this.setField(24, ddDt_op_code_modifier);
	}

	public String getDddt_op_code_modifier() {
		return this.getField(24);
	}

	public void setDkdk_code(String dkDk_code) {
		this.setField(25, dkDk_code);
	}

	public String getDkdk_code() {
		return this.getField(25);
	}

	public void setDkdk_name(String dkDk_name) {
		this.setField(26, dkDk_name);
	}

	public String getDkdk_name() {
		return this.getField(26);
	}

	public void setDkdk_ename(String dkDk_ename) {
		this.setField(27, dkDk_ename);
	}

	public String getDkdk_ename() {
		return this.getField(27);
	}

	public void setCrop_name(String crOp_name) {
		this.setField(28, crOp_name);
	}

	public String getCrop_name() {
		return this.getField(28);
	}

	public void setMoop_name(String moOp_name) {
		this.setField(29, moOp_name);
	}

	public String getMoop_name() {
		return this.getField(29);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
