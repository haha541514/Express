package kyle.leis.eo.operation.cargoinfo.da;

import kyle.common.dbaccess.table.ITableRecord;
import kyle.common.dbaccess.table.TableRecord;

/**
 * ±í¼ÇÂ¼Àà
 */
public class TophwbcargoinfoTR extends TableRecord {

	public TophwbcargoinfoTR() {
		super(TophwbcargoinfoTD.getDatabaseInstance());
	}

	public ITableRecord createInstance() {
		return new TophwbcargoinfoTR();
	}

	public void setCi_id(String CI_ID) {
		this.setFieldValue(0, CI_ID);
	}

	public String getCi_id() {
		return this.getFieldValue(0);
	}

	public void setCi_idCondition(String CI_ID) {
		this.setConditionValue(0, CI_ID);
	}

	public void setCw_code(String CW_CODE) {
		this.setFieldValue(1, CW_CODE);
	}

	public String getCw_code() {
		return this.getFieldValue(1);
	}

	public void setCw_codeCondition(String CW_CODE) {
		this.setConditionValue(1, CW_CODE);
	}

	public void setCi_name(String CI_NAME) {
		this.setFieldValue(2, CI_NAME);
	}

	public String getCi_name() {
		return this.getFieldValue(2);
	}

	public void setCi_nameCondition(String CI_NAME) {
		this.setConditionValue(2, CI_NAME);
	}

	public void setCi_ename(String CI_ENAME) {
		this.setFieldValue(3, CI_ENAME);
	}

	public String getCi_ename() {
		return this.getFieldValue(3);
	}

	public void setCi_enameCondition(String CI_ENAME) {
		this.setConditionValue(3, CI_ENAME);
	}

	public void setCi_pieces(String CI_PIECES) {
		this.setFieldValue(4, CI_PIECES);
	}

	public String getCi_pieces() {
		return this.getFieldValue(4);
	}

	public void setCi_piecesCondition(String CI_PIECES) {
		this.setConditionValue(4, CI_PIECES);
	}

	public void setCi_unitprice(String CI_UNITPRICE) {
		this.setFieldValue(5, CI_UNITPRICE);
	}

	public String getCi_unitprice() {
		return this.getFieldValue(5);
	}

	public void setCi_unitpriceCondition(String CI_UNITPRICE) {
		this.setConditionValue(5, CI_UNITPRICE);
	}

	public void setCi_totalprice(String CI_TOTALPRICE) {
		this.setFieldValue(6, CI_TOTALPRICE);
	}

	public String getCi_totalprice() {
		return this.getFieldValue(6);
	}

	public void setCi_totalpriceCondition(String CI_TOTALPRICE) {
		this.setConditionValue(6, CI_TOTALPRICE);
	}

	public void setCi_hscode(String CI_HSCODE) {
		this.setFieldValue(7, CI_HSCODE);
	}

	public String getCi_hscode() {
		return this.getFieldValue(7);
	}

	public void setCi_hscodeCondition(String CI_HSCODE) {
		this.setConditionValue(7, CI_HSCODE);
	}

	public void setCk_code(String CK_CODE) {
		this.setFieldValue(8, CK_CODE);
	}

	public String getCk_code() {
		return this.getFieldValue(8);
	}

	public void setCk_codeCondition(String CK_CODE) {
		this.setConditionValue(8, CK_CODE);
	}

	public void setCi_attacheinfo(String CI_ATTACHEINFO) {
		this.setFieldValue(9, CI_ATTACHEINFO);
	}

	public String getCi_attacheinfo() {
		return this.getFieldValue(9);
	}

	public void setCi_attacheinfoCondition(String CI_ATTACHEINFO) {
		this.setConditionValue(9, CI_ATTACHEINFO);
	}

	public void setCi_remark(String CI_REMARK) {
		this.setFieldValue(10, CI_REMARK);
	}

	public String getCi_remark() {
		return this.getFieldValue(10);
	}

	public void setCi_remarkCondition(String CI_REMARK) {
		this.setConditionValue(10, CI_REMARK);
	}

	public void setCi_weight(String CI_WEIGHT) {
		this.setFieldValue(11, CI_WEIGHT);
	}

	public String getCi_weight() {
		return this.getFieldValue(11);
	}

	public void setCi_weightCondition(String CI_WEIGHT) {
		this.setConditionValue(11, CI_WEIGHT);
	}


}
