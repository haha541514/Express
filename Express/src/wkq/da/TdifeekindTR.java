package wkq.da;

import kyle.common.dbaccess.table.ITableRecord;
import kyle.common.dbaccess.table.TableRecord;

/**
 * ±í¼ÇÂ¼Àà
 */
public class TdifeekindTR extends TableRecord {

	public TdifeekindTR() {
		super(TdifeekindTD.getDatabaseInstance());
	}

	public ITableRecord createInstance() {
		return new TdifeekindTR();
	}

	public void setFk_code(String FK_CODE) {
		this.setFieldValue(0, FK_CODE);
	}

	public String getFk_code() {
		return this.getFieldValue(0);
	}

	public void setFk_codeCondition(String FK_CODE) {
		this.setConditionValue(0, FK_CODE);
	}

	public void setSs_code(String SS_CODE) {
		this.setFieldValue(1, SS_CODE);
	}

	public String getSs_code() {
		return this.getFieldValue(1);
	}

	public void setSs_codeCondition(String SS_CODE) {
		this.setConditionValue(1, SS_CODE);
	}

	public void setFk_name(String FK_NAME) {
		this.setFieldValue(2, FK_NAME);
	}

	public String getFk_name() {
		return this.getFieldValue(2);
	}

	public void setFk_nameCondition(String FK_NAME) {
		this.setConditionValue(2, FK_NAME);
	}

	public void setFk_ename(String FK_ENAME) {
		this.setFieldValue(3, FK_ENAME);
	}

	public String getFk_ename() {
		return this.getFieldValue(3);
	}

	public void setFk_enameCondition(String FK_ENAME) {
		this.setConditionValue(3, FK_ENAME);
	}

	public void setFk_referenceposition(String FK_REFERENCEPOSITION) {
		this.setFieldValue(4, FK_REFERENCEPOSITION);
	}

	public String getFk_referenceposition() {
		return this.getFieldValue(4);
	}

	public void setFk_referencepositionCondition(String FK_REFERENCEPOSITION) {
		this.setConditionValue(4, FK_REFERENCEPOSITION);
	}

	public void setFk_manualmodifysign(String FK_MANUALMODIFYSIGN) {
		this.setFieldValue(5, FK_MANUALMODIFYSIGN);
	}

	public String getFk_manualmodifysign() {
		return this.getFieldValue(5);
	}

	public void setFk_manualmodifysignCondition(String FK_MANUALMODIFYSIGN) {
		this.setConditionValue(5, FK_MANUALMODIFYSIGN);
	}

	public void setFk_basesign(String FK_BASESIGN) {
		this.setFieldValue(6, FK_BASESIGN);
	}

	public String getFk_basesign() {
		return this.getFieldValue(6);
	}

	public void setFk_basesignCondition(String FK_BASESIGN) {
		this.setConditionValue(6, FK_BASESIGN);
	}

	public void setFk_remark(String FK_REMARK) {
		this.setFieldValue(7, FK_REMARK);
	}

	public String getFk_remark() {
		return this.getFieldValue(7);
	}

	public void setFk_remarkCondition(String FK_REMARK) {
		this.setConditionValue(7, FK_REMARK);
	}

	public void setEst_code(String EST_CODE) {
		this.setFieldValue(8, EST_CODE);
	}

	public String getEst_code() {
		return this.getFieldValue(8);
	}

	public void setEst_codeCondition(String EST_CODE) {
		this.setConditionValue(8, EST_CODE);
	}

	public void setFk_accountingonlysign(String FK_ACCOUNTINGONLYSIGN) {
		this.setFieldValue(9, FK_ACCOUNTINGONLYSIGN);
	}

	public String getFk_accountingonlysign() {
		return this.getFieldValue(9);
	}

	public void setFk_accountingonlysignCondition(String FK_ACCOUNTINGONLYSIGN) {
		this.setConditionValue(9, FK_ACCOUNTINGONLYSIGN);
	}

	public void setFk_declarevaluesign(String FK_DECLAREVALUESIGN) {
		this.setFieldValue(10, FK_DECLAREVALUESIGN);
	}

	public String getFk_declarevaluesign() {
		return this.getFieldValue(10);
	}

	public void setFk_declarevaluesignCondition(String FK_DECLAREVALUESIGN) {
		this.setConditionValue(10, FK_DECLAREVALUESIGN);
	}


}
