package kyle.leis.eo.operation.corewaybill.da;

import kyle.common.dbaccess.table.ITableRecord;
import kyle.common.dbaccess.table.TableRecord;

/**
 * ±í¼ÇÂ¼Àà
 */
public class TopcorewaybillTR extends TableRecord {

	public TopcorewaybillTR() {
		super(TopcorewaybillTD.getDatabaseInstance());
	}

	public ITableRecord createInstance() {
		return new TopcorewaybillTR();
	}

	public void setCw_code(String CW_CODE) {
		this.setFieldValue(0, CW_CODE);
	}

	public String getCw_code() {
		return this.getFieldValue(0);
	}

	public void setCw_codeCondition(String CW_CODE) {
		this.setConditionValue(0, CW_CODE);
	}

	public void setCws_code(String CWS_CODE) {
		this.setFieldValue(1, CWS_CODE);
	}

	public String getCws_code() {
		return this.getFieldValue(1);
	}

	public void setCws_codeCondition(String CWS_CODE) {
		this.setConditionValue(1, CWS_CODE);
	}

	public void setDt_code_origin(String DT_CODE_ORIGIN) {
		this.setFieldValue(2, DT_CODE_ORIGIN);
	}

	public String getDt_code_origin() {
		return this.getFieldValue(2);
	}

	public void setDt_code_originCondition(String DT_CODE_ORIGIN) {
		this.setConditionValue(2, DT_CODE_ORIGIN);
	}

	public void setDt_code_destination(String DT_CODE_DESTINATION) {
		this.setFieldValue(3, DT_CODE_DESTINATION);
	}

	public String getDt_code_destination() {
		return this.getFieldValue(3);
	}

	public void setDt_code_destinationCondition(String DT_CODE_DESTINATION) {
		this.setConditionValue(3, DT_CODE_DESTINATION);
	}

	public void setCw_postcode_destination(String CW_POSTCODE_DESTINATION) {
		this.setFieldValue(4, CW_POSTCODE_DESTINATION);
	}

	public String getCw_postcode_destination() {
		return this.getFieldValue(4);
	}

	public void setCw_postcode_destinationCondition(String CW_POSTCODE_DESTINATION) {
		this.setConditionValue(4, CW_POSTCODE_DESTINATION);
	}

	public void setCt_code(String CT_CODE) {
		this.setFieldValue(5, CT_CODE);
	}

	public String getCt_code() {
		return this.getFieldValue(5);
	}

	public void setCt_codeCondition(String CT_CODE) {
		this.setConditionValue(5, CT_CODE);
	}

	public void setPm_code(String PM_CODE) {
		this.setFieldValue(6, PM_CODE);
	}

	public String getPm_code() {
		return this.getFieldValue(6);
	}

	public void setPm_codeCondition(String PM_CODE) {
		this.setConditionValue(6, PM_CODE);
	}

	public void setPk_code(String PK_CODE) {
		this.setFieldValue(7, PK_CODE);
	}

	public String getPk_code() {
		return this.getFieldValue(7);
	}

	public void setPk_codeCondition(String PK_CODE) {
		this.setConditionValue(7, PK_CODE);
	}

	public void setBw_code_arrival(String BW_CODE_ARRIVAL) {
		this.setFieldValue(8, BW_CODE_ARRIVAL);
	}

	public String getBw_code_arrival() {
		return this.getFieldValue(8);
	}

	public void setBw_code_arrivalCondition(String BW_CODE_ARRIVAL) {
		this.setConditionValue(8, BW_CODE_ARRIVAL);
	}

	public void setBw_code_departure(String BW_CODE_DEPARTURE) {
		this.setFieldValue(9, BW_CODE_DEPARTURE);
	}

	public String getBw_code_departure() {
		return this.getFieldValue(9);
	}

	public void setBw_code_departureCondition(String BW_CODE_DEPARTURE) {
		this.setConditionValue(9, BW_CODE_DEPARTURE);
	}

	public void setCo_code_customer(String CO_CODE_CUSTOMER) {
		this.setFieldValue(10, CO_CODE_CUSTOMER);
	}

	public String getCo_code_customer() {
		return this.getFieldValue(10);
	}

	public void setCo_code_customerCondition(String CO_CODE_CUSTOMER) {
		this.setConditionValue(10, CO_CODE_CUSTOMER);
	}

	public void setCo_code_supplier(String CO_CODE_SUPPLIER) {
		this.setFieldValue(11, CO_CODE_SUPPLIER);
	}

	public String getCo_code_supplier() {
		return this.getFieldValue(11);
	}

	public void setCo_code_supplierCondition(String CO_CODE_SUPPLIER) {
		this.setConditionValue(11, CO_CODE_SUPPLIER);
	}

	public void setChn_code_customer(String CHN_CODE_CUSTOMER) {
		this.setFieldValue(12, CHN_CODE_CUSTOMER);
	}

	public String getChn_code_customer() {
		return this.getFieldValue(12);
	}

	public void setChn_code_customerCondition(String CHN_CODE_CUSTOMER) {
		this.setConditionValue(12, CHN_CODE_CUSTOMER);
	}

	public void setChn_code_supplier(String CHN_CODE_SUPPLIER) {
		this.setFieldValue(13, CHN_CODE_SUPPLIER);
	}

	public String getChn_code_supplier() {
		return this.getFieldValue(13);
	}

	public void setChn_code_supplierCondition(String CHN_CODE_SUPPLIER) {
		this.setConditionValue(13, CHN_CODE_SUPPLIER);
	}

	public void setCw_pieces(String CW_PIECES) {
		this.setFieldValue(14, CW_PIECES);
	}

	public String getCw_pieces() {
		return this.getFieldValue(14);
	}

	public void setCw_piecesCondition(String CW_PIECES) {
		this.setConditionValue(14, CW_PIECES);
	}

	public void setCw_grossweight(String CW_GROSSWEIGHT) {
		this.setFieldValue(15, CW_GROSSWEIGHT);
	}

	public String getCw_grossweight() {
		return this.getFieldValue(15);
	}

	public void setCw_grossweightCondition(String CW_GROSSWEIGHT) {
		this.setConditionValue(15, CW_GROSSWEIGHT);
	}

	public void setCw_chargeweight(String CW_CHARGEWEIGHT) {
		this.setFieldValue(16, CW_CHARGEWEIGHT);
	}

	public String getCw_chargeweight() {
		return this.getFieldValue(16);
	}

	public void setCw_chargeweightCondition(String CW_CHARGEWEIGHT) {
		this.setConditionValue(16, CW_CHARGEWEIGHT);
	}

	public void setCw_transferpieces(String CW_TRANSFERPIECES) {
		this.setFieldValue(17, CW_TRANSFERPIECES);
	}

	public String getCw_transferpieces() {
		return this.getFieldValue(17);
	}

	public void setCw_transferpiecesCondition(String CW_TRANSFERPIECES) {
		this.setConditionValue(17, CW_TRANSFERPIECES);
	}

	public void setCw_transfergrossweight(String CW_TRANSFERGROSSWEIGHT) {
		this.setFieldValue(18, CW_TRANSFERGROSSWEIGHT);
	}

	public String getCw_transfergrossweight() {
		return this.getFieldValue(18);
	}

	public void setCw_transfergrossweightCondition(String CW_TRANSFERGROSSWEIGHT) {
		this.setConditionValue(18, CW_TRANSFERGROSSWEIGHT);
	}

	public void setCw_transferchargeweight(String CW_TRANSFERCHARGEWEIGHT) {
		this.setFieldValue(19, CW_TRANSFERCHARGEWEIGHT);
	}

	public String getCw_transferchargeweight() {
		return this.getFieldValue(19);
	}

	public void setCw_transferchargeweightCondition(String CW_TRANSFERCHARGEWEIGHT) {
		this.setConditionValue(19, CW_TRANSFERCHARGEWEIGHT);
	}

	public void setCw_serverchargeweight(String CW_SERVERCHARGEWEIGHT) {
		this.setFieldValue(20, CW_SERVERCHARGEWEIGHT);
	}

	public String getCw_serverchargeweight() {
		return this.getFieldValue(20);
	}

	public void setCw_serverchargeweightCondition(String CW_SERVERCHARGEWEIGHT) {
		this.setConditionValue(20, CW_SERVERCHARGEWEIGHT);
	}

	public void setCw_customerewbcode(String CW_CUSTOMEREWBCODE) {
		this.setFieldValue(21, CW_CUSTOMEREWBCODE);
	}

	public String getCw_customerewbcode() {
		return this.getFieldValue(21);
	}

	public void setCw_customerewbcodeCondition(String CW_CUSTOMEREWBCODE) {
		this.setConditionValue(21, CW_CUSTOMEREWBCODE);
	}

	public void setCw_serverewbcode(String CW_SERVEREWBCODE) {
		this.setFieldValue(22, CW_SERVEREWBCODE);
	}

	public String getCw_serverewbcode() {
		return this.getFieldValue(22);
	}

	public void setCw_serverewbcodeCondition(String CW_SERVEREWBCODE) {
		this.setConditionValue(22, CW_SERVEREWBCODE);
	}

	public void setCw_channelewbcode(String CW_CHANNELEWBCODE) {
		this.setFieldValue(23, CW_CHANNELEWBCODE);
	}

	public String getCw_channelewbcode() {
		return this.getFieldValue(23);
	}

	public void setCw_channelewbcodeCondition(String CW_CHANNELEWBCODE) {
		this.setConditionValue(23, CW_CHANNELEWBCODE);
	}

	public void setCw_ewbcode(String CW_EWBCODE) {
		this.setFieldValue(24, CW_EWBCODE);
	}

	public String getCw_ewbcode() {
		return this.getFieldValue(24);
	}

	public void setCw_ewbcodeCondition(String CW_EWBCODE) {
		this.setConditionValue(24, CW_EWBCODE);
	}

	public void setCw_op_id_creator(String CW_OP_ID_CREATOR) {
		this.setFieldValue(25, CW_OP_ID_CREATOR);
	}

	public String getCw_op_id_creator() {
		return this.getFieldValue(25);
	}

	public void setCw_op_id_creatorCondition(String CW_OP_ID_CREATOR) {
		this.setConditionValue(25, CW_OP_ID_CREATOR);
	}

	public void setCw_createdate(String CW_CREATEDATE) {
		this.setFieldValue(26, CW_CREATEDATE);
	}

	public String getCw_createdate() {
		return this.getFieldValue(26);
	}

	public void setCw_createdateCondition(String CW_CREATEDATE) {
		this.setConditionValue(26, CW_CREATEDATE);
	}

	public void setCw_op_id_modifier(String CW_OP_ID_MODIFIER) {
		this.setFieldValue(27, CW_OP_ID_MODIFIER);
	}

	public String getCw_op_id_modifier() {
		return this.getFieldValue(27);
	}

	public void setCw_op_id_modifierCondition(String CW_OP_ID_MODIFIER) {
		this.setConditionValue(27, CW_OP_ID_MODIFIER);
	}

	public void setCw_modifydate(String CW_MODIFYDATE) {
		this.setFieldValue(28, CW_MODIFYDATE);
	}

	public String getCw_modifydate() {
		return this.getFieldValue(28);
	}

	public void setCw_modifydateCondition(String CW_MODIFYDATE) {
		this.setConditionValue(28, CW_MODIFYDATE);
	}

	public void setCw_transfervolumeweight(String CW_TRANSFERVOLUMEWEIGHT) {
		this.setFieldValue(29, CW_TRANSFERVOLUMEWEIGHT);
	}

	public String getCw_transfervolumeweight() {
		return this.getFieldValue(29);
	}

	public void setCw_transfervolumeweightCondition(String CW_TRANSFERVOLUMEWEIGHT) {
		this.setConditionValue(29, CW_TRANSFERVOLUMEWEIGHT);
	}

	public void setEe_code(String EE_CODE) {
		this.setFieldValue(30, EE_CODE);
	}

	public String getEe_code() {
		return this.getFieldValue(30);
	}

	public void setEe_codeCondition(String EE_CODE) {
		this.setConditionValue(30, EE_CODE);
	}

	public void setDt_code_signin(String DT_CODE_SIGNIN) {
		this.setFieldValue(31, DT_CODE_SIGNIN);
	}

	public String getDt_code_signin() {
		return this.getFieldValue(31);
	}

	public void setDt_code_signinCondition(String DT_CODE_SIGNIN) {
		this.setConditionValue(31, DT_CODE_SIGNIN);
	}

	public void setIhs_code(String IHS_CODE) {
		this.setFieldValue(32, IHS_CODE);
	}

	public String getIhs_code() {
		return this.getFieldValue(32);
	}

	public void setIhs_codeCondition(String IHS_CODE) {
		this.setConditionValue(32, IHS_CODE);
	}

	public void setCw_customerchargeweight(String CW_CUSTOMERCHARGEWEIGHT) {
		this.setFieldValue(33, CW_CUSTOMERCHARGEWEIGHT);
	}

	public String getCw_customerchargeweight() {
		return this.getFieldValue(33);
	}

	public void setCw_customerchargeweightCondition(String CW_CUSTOMERCHARGEWEIGHT) {
		this.setConditionValue(33, CW_CUSTOMERCHARGEWEIGHT);
	}

	public void setCw_volumerate(String CW_VOLUMERATE) {
		this.setFieldValue(34, CW_VOLUMERATE);
	}

	public String getCw_volumerate() {
		return this.getFieldValue(34);
	}

	public void setCw_volumerateCondition(String CW_VOLUMERATE) {
		this.setConditionValue(34, CW_VOLUMERATE);
	}

	public void setCw_transfervolumerate(String CW_TRANSFERVOLUMERATE) {
		this.setFieldValue(35, CW_TRANSFERVOLUMERATE);
	}

	public String getCw_transfervolumerate() {
		return this.getFieldValue(35);
	}

	public void setCw_transfervolumerateCondition(String CW_TRANSFERVOLUMERATE) {
		this.setConditionValue(35, CW_TRANSFERVOLUMERATE);
	}

	public void setZnv_name(String ZNV_NAME) {
		this.setFieldValue(36, ZNV_NAME);
	}

	public String getZnv_name() {
		return this.getFieldValue(36);
	}

	public void setZnv_nameCondition(String ZNV_NAME) {
		this.setConditionValue(36, ZNV_NAME);
	}

	public void setFas_code(String FAS_CODE) {
		this.setFieldValue(37, FAS_CODE);
	}

	public String getFas_code() {
		return this.getFieldValue(37);
	}

	public void setFas_codeCondition(String FAS_CODE) {
		this.setConditionValue(37, FAS_CODE);
	}

	public void setBw_code_weightcheck(String BW_CODE_WEIGHTCHECK) {
		this.setFieldValue(38, BW_CODE_WEIGHTCHECK);
	}

	public String getBw_code_weightcheck() {
		return this.getFieldValue(38);
	}

	public void setBw_code_weightcheckCondition(String BW_CODE_WEIGHTCHECK) {
		this.setConditionValue(38, BW_CODE_WEIGHTCHECK);
	}

	public void setCw_billcounts(String CW_BILLCOUNTS) {
		this.setFieldValue(39, CW_BILLCOUNTS);
	}

	public String getCw_billcounts() {
		return this.getFieldValue(39);
	}

	public void setCw_billcountsCondition(String CW_BILLCOUNTS) {
		this.setConditionValue(39, CW_BILLCOUNTS);
	}

	public void setCw_bagcounts(String CW_BAGCOUNTS) {
		this.setFieldValue(40, CW_BAGCOUNTS);
	}

	public String getCw_bagcounts() {
		return this.getFieldValue(40);
	}

	public void setCw_bagcountsCondition(String CW_BAGCOUNTS) {
		this.setConditionValue(40, CW_BAGCOUNTS);
	}

	public void setBwbv_id_arrival(String BWBV_ID_ARRIVAL) {
		this.setFieldValue(41, BWBV_ID_ARRIVAL);
	}

	public String getBwbv_id_arrival() {
		return this.getFieldValue(41);
	}

	public void setBwbv_id_arrivalCondition(String BWBV_ID_ARRIVAL) {
		this.setConditionValue(41, BWBV_ID_ARRIVAL);
	}

	public void setBwbv_id__departure(String BWBV_ID__DEPARTURE) {
		this.setFieldValue(42, BWBV_ID__DEPARTURE);
	}

	public String getBwbv_id__departure() {
		return this.getFieldValue(42);
	}

	public void setBwbv_id__departureCondition(String BWBV_ID__DEPARTURE) {
		this.setConditionValue(42, BWBV_ID__DEPARTURE);
	}

	public void setCw_batchwaybillsign(String CW_BATCHWAYBILLSIGN) {
		this.setFieldValue(43, CW_BATCHWAYBILLSIGN);
	}

	public String getCw_batchwaybillsign() {
		return this.getFieldValue(43);
	}

	public void setCw_batchwaybillsignCondition(String CW_BATCHWAYBILLSIGN) {
		this.setConditionValue(43, CW_BATCHWAYBILLSIGN);
	}


}
