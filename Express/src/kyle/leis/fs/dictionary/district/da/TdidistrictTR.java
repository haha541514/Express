package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.table.ITableRecord;
import kyle.common.dbaccess.table.TableRecord;

/**
 * ±í¼ÇÂ¼Àà
 */
public class TdidistrictTR extends TableRecord {

	public TdidistrictTR() {
		super(TdidistrictTD.getDatabaseInstance());
	}

	public ITableRecord createInstance() {
		return new TdidistrictTR();
	}

	public void setDt_code(String DT_CODE) {
		this.setFieldValue(0, DT_CODE);
	}

	public String getDt_code() {
		return this.getFieldValue(0);
	}

	public void setDt_codeCondition(String DT_CODE) {
		this.setConditionValue(0, DT_CODE);
	}

	public void setDt_countcode(String DT_COUNTCODE) {
		this.setFieldValue(1, DT_COUNTCODE);
	}

	public String getDt_countcode() {
		return this.getFieldValue(1);
	}

	public void setDt_countcodeCondition(String DT_COUNTCODE) {
		this.setConditionValue(1, DT_COUNTCODE);
	}

	public void setDk_code(String DK_CODE) {
		this.setFieldValue(2, DK_CODE);
	}

	public String getDk_code() {
		return this.getFieldValue(2);
	}

	public void setDk_codeCondition(String DK_CODE) {
		this.setConditionValue(2, DK_CODE);
	}

	public void setDt_hubcode(String DT_HUBCODE) {
		this.setFieldValue(3, DT_HUBCODE);
	}

	public String getDt_hubcode() {
		return this.getFieldValue(3);
	}

	public void setDt_hubcodeCondition(String DT_HUBCODE) {
		this.setConditionValue(3, DT_HUBCODE);
	}

	public void setDt_name(String DT_NAME) {
		this.setFieldValue(4, DT_NAME);
	}

	public String getDt_name() {
		return this.getFieldValue(4);
	}

	public void setDt_nameCondition(String DT_NAME) {
		this.setConditionValue(4, DT_NAME);
	}

	public void setDt_ename(String DT_ENAME) {
		this.setFieldValue(5, DT_ENAME);
	}

	public String getDt_ename() {
		return this.getFieldValue(5);
	}

	public void setDt_enameCondition(String DT_ENAME) {
		this.setConditionValue(5, DT_ENAME);
	}

	public void setDt_statecode(String DT_STATECODE) {
		this.setFieldValue(6, DT_STATECODE);
	}

	public String getDt_statecode() {
		return this.getFieldValue(6);
	}

	public void setDt_statecodeCondition(String DT_STATECODE) {
		this.setConditionValue(6, DT_STATECODE);
	}

	public void setDt_statename(String DT_STATENAME) {
		this.setFieldValue(7, DT_STATENAME);
	}

	public String getDt_statename() {
		return this.getFieldValue(7);
	}

	public void setDt_statenameCondition(String DT_STATENAME) {
		this.setConditionValue(7, DT_STATENAME);
	}

	public void setDt_grade(String DT_GRADE) {
		this.setFieldValue(8, DT_GRADE);
	}

	public String getDt_grade() {
		return this.getFieldValue(8);
	}

	public void setDt_gradeCondition(String DT_GRADE) {
		this.setConditionValue(8, DT_GRADE);
	}

	public void setDt_startpostcode(String DT_STARTPOSTCODE) {
		this.setFieldValue(9, DT_STARTPOSTCODE);
	}

	public String getDt_startpostcode() {
		return this.getFieldValue(9);
	}

	public void setDt_startpostcodeCondition(String DT_STARTPOSTCODE) {
		this.setConditionValue(9, DT_STARTPOSTCODE);
	}

	public void setDt_endpostcode(String DT_ENDPOSTCODE) {
		this.setFieldValue(10, DT_ENDPOSTCODE);
	}

	public String getDt_endpostcode() {
		return this.getFieldValue(10);
	}

	public void setDt_endpostcodeCondition(String DT_ENDPOSTCODE) {
		this.setConditionValue(10, DT_ENDPOSTCODE);
	}

	public void setDt_op_code_creator(String DT_OP_CODE_CREATOR) {
		this.setFieldValue(11, DT_OP_CODE_CREATOR);
	}

	public String getDt_op_code_creator() {
		return this.getFieldValue(11);
	}

	public void setDt_op_code_creatorCondition(String DT_OP_CODE_CREATOR) {
		this.setConditionValue(11, DT_OP_CODE_CREATOR);
	}

	public void setDt_createdate(String DT_CREATEDATE) {
		this.setFieldValue(12, DT_CREATEDATE);
	}

	public String getDt_createdate() {
		return this.getFieldValue(12);
	}

	public void setDt_createdateCondition(String DT_CREATEDATE) {
		this.setConditionValue(12, DT_CREATEDATE);
	}

	public void setDt_op_code_modifier(String DT_OP_CODE_MODIFIER) {
		this.setFieldValue(13, DT_OP_CODE_MODIFIER);
	}

	public String getDt_op_code_modifier() {
		return this.getFieldValue(13);
	}

	public void setDt_op_code_modifierCondition(String DT_OP_CODE_MODIFIER) {
		this.setConditionValue(13, DT_OP_CODE_MODIFIER);
	}

	public void setDt_modifydate(String DT_MODIFYDATE) {
		this.setFieldValue(14, DT_MODIFYDATE);
	}

	public String getDt_modifydate() {
		return this.getFieldValue(14);
	}

	public void setDt_modifydateCondition(String DT_MODIFYDATE) {
		this.setConditionValue(14, DT_MODIFYDATE);
	}

	public void setDt_remark(String DT_REMARK) {
		this.setFieldValue(15, DT_REMARK);
	}

	public String getDt_remark() {
		return this.getFieldValue(15);
	}

	public void setDt_remarkCondition(String DT_REMARK) {
		this.setConditionValue(15, DT_REMARK);
	}

	public void setDt_startcitysign(String DT_STARTCITYSIGN) {
		this.setFieldValue(16, DT_STARTCITYSIGN);
	}

	public String getDt_startcitysign() {
		return this.getFieldValue(16);
	}

	public void setDt_startcitysignCondition(String DT_STARTCITYSIGN) {
		this.setConditionValue(16, DT_STARTCITYSIGN);
	}

	public void setDt_elevatedrisksign(String DT_ELEVATEDRISKSIGN) {
		this.setFieldValue(17, DT_ELEVATEDRISKSIGN);
	}

	public String getDt_elevatedrisksign() {
		return this.getFieldValue(17);
	}

	public void setDt_elevatedrisksignCondition(String DT_ELEVATEDRISKSIGN) {
		this.setConditionValue(17, DT_ELEVATEDRISKSIGN);
	}

	public void setDt_restrictedsign(String DT_RESTRICTEDSIGN) {
		this.setFieldValue(18, DT_RESTRICTEDSIGN);
	}

	public String getDt_restrictedsign() {
		return this.getFieldValue(18);
	}

	public void setDt_restrictedsignCondition(String DT_RESTRICTEDSIGN) {
		this.setConditionValue(18, DT_RESTRICTEDSIGN);
	}


}
