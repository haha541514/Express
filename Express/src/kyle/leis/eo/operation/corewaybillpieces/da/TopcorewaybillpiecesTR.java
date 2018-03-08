package kyle.leis.eo.operation.corewaybillpieces.da;

import kyle.common.dbaccess.table.ITableRecord;
import kyle.common.dbaccess.table.TableRecord;

/**
 * ±í¼ÇÂ¼Àà
 */
public class TopcorewaybillpiecesTR extends TableRecord {

	public TopcorewaybillpiecesTR() {
		super(TopcorewaybillpiecesTD.getDatabaseInstance());
	}

	public ITableRecord createInstance() {
		return new TopcorewaybillpiecesTR();
	}

	public void setCp_id(String CP_ID) {
		this.setFieldValue(0, CP_ID);
	}

	public String getCp_id() {
		return this.getFieldValue(0);
	}

	public void setCp_idCondition(String CP_ID) {
		this.setConditionValue(0, CP_ID);
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

	public void setCp_grossweight(String CP_GROSSWEIGHT) {
		this.setFieldValue(2, CP_GROSSWEIGHT);
	}

	public String getCp_grossweight() {
		return this.getFieldValue(2);
	}

	public void setCp_grossweightCondition(String CP_GROSSWEIGHT) {
		this.setConditionValue(2, CP_GROSSWEIGHT);
	}

	public void setCp_length(String CP_LENGTH) {
		this.setFieldValue(3, CP_LENGTH);
	}

	public String getCp_length() {
		return this.getFieldValue(3);
	}

	public void setCp_lengthCondition(String CP_LENGTH) {
		this.setConditionValue(3, CP_LENGTH);
	}

	public void setCp_width(String CP_WIDTH) {
		this.setFieldValue(4, CP_WIDTH);
	}

	public String getCp_width() {
		return this.getFieldValue(4);
	}

	public void setCp_widthCondition(String CP_WIDTH) {
		this.setConditionValue(4, CP_WIDTH);
	}

	public void setCp_height(String CP_HEIGHT) {
		this.setFieldValue(5, CP_HEIGHT);
	}

	public String getCp_height() {
		return this.getFieldValue(5);
	}

	public void setCp_heightCondition(String CP_HEIGHT) {
		this.setConditionValue(5, CP_HEIGHT);
	}

	public void setCp_labelcode(String CP_LABELCODE) {
		this.setFieldValue(6, CP_LABELCODE);
	}

	public String getCp_labelcode() {
		return this.getFieldValue(6);
	}

	public void setCp_labelcodeCondition(String CP_LABELCODE) {
		this.setConditionValue(6, CP_LABELCODE);
	}

	public void setCp_baglabelcode(String CP_BAGLABELCODE) {
		this.setFieldValue(7, CP_BAGLABELCODE);
	}

	public String getCp_baglabelcode() {
		return this.getFieldValue(7);
	}

	public void setCp_baglabelcodeCondition(String CP_BAGLABELCODE) {
		this.setConditionValue(7, CP_BAGLABELCODE);
	}

	public void setCp_sibaglabelcode(String CP_SIBAGLABELCODE) {
		this.setFieldValue(8, CP_SIBAGLABELCODE);
	}

	public String getCp_sibaglabelcode() {
		return this.getFieldValue(8);
	}

	public void setCp_sibaglabelcodeCondition(String CP_SIBAGLABELCODE) {
		this.setConditionValue(8, CP_SIBAGLABELCODE);
	}

	public void setCp_barcodelabelcode(String CP_BARCODELABELCODE) {
		this.setFieldValue(9, CP_BARCODELABELCODE);
	}

	public String getCp_barcodelabelcode() {
		return this.getFieldValue(9);
	}

	public void setCp_barcodelabelcodeCondition(String CP_BARCODELABELCODE) {
		this.setConditionValue(9, CP_BARCODELABELCODE);
	}

	public void setCws_code(String CWS_CODE) {
		this.setFieldValue(10, CWS_CODE);
	}

	public String getCws_code() {
		return this.getFieldValue(10);
	}

	public void setCws_codeCondition(String CWS_CODE) {
		this.setConditionValue(10, CWS_CODE);
	}

	public void setCp_selflabelcode(String CP_SELFLABELCODE) {
		this.setFieldValue(11, CP_SELFLABELCODE);
	}

	public String getCp_selflabelcode() {
		return this.getFieldValue(11);
	}

	public void setCp_selflabelcodeCondition(String CP_SELFLABELCODE) {
		this.setConditionValue(11, CP_SELFLABELCODE);
	}


}
