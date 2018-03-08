package kyle.leis.eo.operation.batchwaybill.da;

import kyle.common.dbaccess.table.ITableRecord;
import kyle.common.dbaccess.table.TableRecord;

/**
 * ±í¼ÇÂ¼Àà
 */
public class TopbatchwaybillTR extends TableRecord {

	public TopbatchwaybillTR() {
		super(TopbatchwaybillTD.getDatabaseInstance());
	}

	public ITableRecord createInstance() {
		return new TopbatchwaybillTR();
	}

	public void setBw_code(String BW_CODE) {
		this.setFieldValue(0, BW_CODE);
	}

	public String getBw_code() {
		return this.getFieldValue(0);
	}

	public void setBw_codeCondition(String BW_CODE) {
		this.setConditionValue(0, BW_CODE);
	}

	public void setBws_code(String BWS_CODE) {
		this.setFieldValue(1, BWS_CODE);
	}

	public String getBws_code() {
		return this.getFieldValue(1);
	}

	public void setBws_codeCondition(String BWS_CODE) {
		this.setConditionValue(1, BWS_CODE);
	}

	public void setEe_code(String EE_CODE) {
		this.setFieldValue(2, EE_CODE);
	}

	public String getEe_code() {
		return this.getFieldValue(2);
	}

	public void setEe_codeCondition(String EE_CODE) {
		this.setConditionValue(2, EE_CODE);
	}

	public void setAdt_code(String ADT_CODE) {
		this.setFieldValue(3, ADT_CODE);
	}

	public String getAdt_code() {
		return this.getFieldValue(3);
	}

	public void setAdt_codeCondition(String ADT_CODE) {
		this.setConditionValue(3, ADT_CODE);
	}

	public void setBw_op_id_create(String BW_OP_ID_CREATE) {
		this.setFieldValue(4, BW_OP_ID_CREATE);
	}

	public String getBw_op_id_create() {
		return this.getFieldValue(4);
	}

	public void setBw_op_id_createCondition(String BW_OP_ID_CREATE) {
		this.setConditionValue(4, BW_OP_ID_CREATE);
	}

	public void setBw_createdate(String BW_CREATEDATE) {
		this.setFieldValue(5, BW_CREATEDATE);
	}

	public String getBw_createdate() {
		return this.getFieldValue(5);
	}

	public void setBw_createdateCondition(String BW_CREATEDATE) {
		this.setConditionValue(5, BW_CREATEDATE);
	}

	public void setBw_op_id_modify(String BW_OP_ID_MODIFY) {
		this.setFieldValue(6, BW_OP_ID_MODIFY);
	}

	public String getBw_op_id_modify() {
		return this.getFieldValue(6);
	}

	public void setBw_op_id_modifyCondition(String BW_OP_ID_MODIFY) {
		this.setConditionValue(6, BW_OP_ID_MODIFY);
	}

	public void setBw_modifydate(String BW_MODIFYDATE) {
		this.setFieldValue(7, BW_MODIFYDATE);
	}

	public String getBw_modifydate() {
		return this.getFieldValue(7);
	}

	public void setBw_modifydateCondition(String BW_MODIFYDATE) {
		this.setConditionValue(7, BW_MODIFYDATE);
	}

	public void setBw_op_id_complete(String BW_OP_ID_COMPLETE) {
		this.setFieldValue(8, BW_OP_ID_COMPLETE);
	}

	public String getBw_op_id_complete() {
		return this.getFieldValue(8);
	}

	public void setBw_op_id_completeCondition(String BW_OP_ID_COMPLETE) {
		this.setConditionValue(8, BW_OP_ID_COMPLETE);
	}

	public void setBw_completedate(String BW_COMPLETEDATE) {
		this.setFieldValue(9, BW_COMPLETEDATE);
	}

	public String getBw_completedate() {
		return this.getFieldValue(9);
	}

	public void setBw_completedateCondition(String BW_COMPLETEDATE) {
		this.setConditionValue(9, BW_COMPLETEDATE);
	}

	public void setBw_op_id_audit(String BW_OP_ID_AUDIT) {
		this.setFieldValue(10, BW_OP_ID_AUDIT);
	}

	public String getBw_op_id_audit() {
		return this.getFieldValue(10);
	}

	public void setBw_op_id_auditCondition(String BW_OP_ID_AUDIT) {
		this.setConditionValue(10, BW_OP_ID_AUDIT);
	}

	public void setBw_auditdate(String BW_AUDITDATE) {
		this.setFieldValue(11, BW_AUDITDATE);
	}

	public String getBw_auditdate() {
		return this.getFieldValue(11);
	}

	public void setBw_auditdateCondition(String BW_AUDITDATE) {
		this.setConditionValue(11, BW_AUDITDATE);
	}

	public void setBw_op_id_approve(String BW_OP_ID_APPROVE) {
		this.setFieldValue(12, BW_OP_ID_APPROVE);
	}

	public String getBw_op_id_approve() {
		return this.getFieldValue(12);
	}

	public void setBw_op_id_approveCondition(String BW_OP_ID_APPROVE) {
		this.setConditionValue(12, BW_OP_ID_APPROVE);
	}

	public void setCo_code(String CO_CODE) {
		this.setFieldValue(13, CO_CODE);
	}

	public String getCo_code() {
		return this.getFieldValue(13);
	}

	public void setCo_codeCondition(String CO_CODE) {
		this.setConditionValue(13, CO_CODE);
	}

	public void setChn_code(String CHN_CODE) {
		this.setFieldValue(14, CHN_CODE);
	}

	public String getChn_code() {
		return this.getFieldValue(14);
	}

	public void setChn_codeCondition(String CHN_CODE) {
		this.setConditionValue(14, CHN_CODE);
	}

	public void setBw_approvedate(String BW_APPROVEDATE) {
		this.setFieldValue(15, BW_APPROVEDATE);
	}

	public String getBw_approvedate() {
		return this.getFieldValue(15);
	}

	public void setBw_approvedateCondition(String BW_APPROVEDATE) {
		this.setConditionValue(15, BW_APPROVEDATE);
	}

	public void setAdd_date(String ADD_DATE) {
		this.setFieldValue(16, ADD_DATE);
	}

	public String getAdd_date() {
		return this.getFieldValue(16);
	}

	public void setAdd_dateCondition(String ADD_DATE) {
		this.setConditionValue(16, ADD_DATE);
	}

	public void setBw_remark(String BW_REMARK) {
		this.setFieldValue(17, BW_REMARK);
	}

	public String getBw_remark() {
		return this.getFieldValue(17);
	}

	public void setBw_remarkCondition(String BW_REMARK) {
		this.setConditionValue(17, BW_REMARK);
	}

	public void setBw_totalgrossweight(String BW_TOTALGROSSWEIGHT) {
		this.setFieldValue(18, BW_TOTALGROSSWEIGHT);
	}

	public String getBw_totalgrossweight() {
		return this.getFieldValue(18);
	}

	public void setBw_totalgrossweightCondition(String BW_TOTALGROSSWEIGHT) {
		this.setConditionValue(18, BW_TOTALGROSSWEIGHT);
	}

	public void setBw_totalpieces(String BW_TOTALPIECES) {
		this.setFieldValue(19, BW_TOTALPIECES);
	}

	public String getBw_totalpieces() {
		return this.getFieldValue(19);
	}

	public void setBw_totalpiecesCondition(String BW_TOTALPIECES) {
		this.setConditionValue(19, BW_TOTALPIECES);
	}

	public void setBw_labelcode(String BW_LABELCODE) {
		this.setFieldValue(20, BW_LABELCODE);
	}

	public String getBw_labelcode() {
		return this.getFieldValue(20);
	}

	public void setBw_labelcodeCondition(String BW_LABELCODE) {
		this.setConditionValue(20, BW_LABELCODE);
	}

	public void setBw_batchnumber(String BW_BATCHNUMBER) {
		this.setFieldValue(21, BW_BATCHNUMBER);
	}

	public String getBw_batchnumber() {
		return this.getFieldValue(21);
	}

	public void setBw_batchnumberCondition(String BW_BATCHNUMBER) {
		this.setConditionValue(21, BW_BATCHNUMBER);
	}

	public void setBw_containerid(String BW_CONTAINERID) {
		this.setFieldValue(22, BW_CONTAINERID);
	}

	public String getBw_containerid() {
		return this.getFieldValue(22);
	}

	public void setBw_containeridCondition(String BW_CONTAINERID) {
		this.setConditionValue(22, BW_CONTAINERID);
	}


}
