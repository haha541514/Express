package kyle.leis.eo.operation.predictwaybill.da;

import kyle.common.dbaccess.table.ITableRecord;
import kyle.common.dbaccess.table.TableRecord;

/**
 * ±í¼ÇÂ¼Àà
 */
public class ToppredictwaybillTR extends TableRecord {

	public ToppredictwaybillTR() {
		super(ToppredictwaybillTD.getDatabaseInstance());
	}

	public ITableRecord createInstance() {
		return new ToppredictwaybillTR();
	}

	public void setPwb_code(String PWB_CODE) {
		this.setFieldValue(0, PWB_CODE);
	}

	public String getPwb_code() {
		return this.getFieldValue(0);
	}

	public void setPwb_codeCondition(String PWB_CODE) {
		this.setConditionValue(0, PWB_CODE);
	}

	public void setPk_code(String PK_CODE) {
		this.setFieldValue(1, PK_CODE);
	}

	public String getPk_code() {
		return this.getFieldValue(1);
	}

	public void setPk_codeCondition(String PK_CODE) {
		this.setConditionValue(1, PK_CODE);
	}

	public void setOp_id_creator(String OP_ID_CREATOR) {
		this.setFieldValue(2, OP_ID_CREATOR);
	}

	public String getOp_id_creator() {
		return this.getFieldValue(2);
	}

	public void setOp_id_creatorCondition(String OP_ID_CREATOR) {
		this.setConditionValue(2, OP_ID_CREATOR);
	}

	public void setOp_id_modifier(String OP_ID_MODIFIER) {
		this.setFieldValue(3, OP_ID_MODIFIER);
	}

	public String getOp_id_modifier() {
		return this.getFieldValue(3);
	}

	public void setOp_id_modifierCondition(String OP_ID_MODIFIER) {
		this.setConditionValue(3, OP_ID_MODIFIER);
	}

	public void setPwbs_code(String PWBS_CODE) {
		this.setFieldValue(4, PWBS_CODE);
	}

	public String getPwbs_code() {
		return this.getFieldValue(4);
	}

	public void setPwbs_codeCondition(String PWBS_CODE) {
		this.setConditionValue(4, PWBS_CODE);
	}

	public void setCo_code_customer(String CO_CODE_CUSTOMER) {
		this.setFieldValue(5, CO_CODE_CUSTOMER);
	}

	public String getCo_code_customer() {
		return this.getFieldValue(5);
	}

	public void setCo_code_customerCondition(String CO_CODE_CUSTOMER) {
		this.setConditionValue(5, CO_CODE_CUSTOMER);
	}

	public void setChn_code(String CHN_CODE) {
		this.setFieldValue(6, CHN_CODE);
	}

	public String getChn_code() {
		return this.getFieldValue(6);
	}

	public void setChn_codeCondition(String CHN_CODE) {
		this.setConditionValue(6, CHN_CODE);
	}

	public void setPwb_destination(String PWB_DESTINATION) {
		this.setFieldValue(7, PWB_DESTINATION);
	}

	public String getPwb_destination() {
		return this.getFieldValue(7);
	}

	public void setPwb_destinationCondition(String PWB_DESTINATION) {
		this.setConditionValue(7, PWB_DESTINATION);
	}

	public void setCw_code(String CW_CODE) {
		this.setFieldValue(8, CW_CODE);
	}

	public String getCw_code() {
		return this.getFieldValue(8);
	}

	public void setCw_codeCondition(String CW_CODE) {
		this.setConditionValue(8, CW_CODE);
	}

	public void setOp_id_printer(String OP_ID_PRINTER) {
		this.setFieldValue(9, OP_ID_PRINTER);
	}

	public String getOp_id_printer() {
		return this.getFieldValue(9);
	}

	public void setOp_id_printerCondition(String OP_ID_PRINTER) {
		this.setConditionValue(9, OP_ID_PRINTER);
	}

	public void setOp_id_declarer(String OP_ID_DECLARER) {
		this.setFieldValue(10, OP_ID_DECLARER);
	}

	public String getOp_id_declarer() {
		return this.getFieldValue(10);
	}

	public void setOp_id_declarerCondition(String OP_ID_DECLARER) {
		this.setConditionValue(10, OP_ID_DECLARER);
	}

	public void setCk_code(String CK_CODE) {
		this.setFieldValue(11, CK_CODE);
	}

	public String getCk_code() {
		return this.getFieldValue(11);
	}

	public void setCk_codeCondition(String CK_CODE) {
		this.setConditionValue(11, CK_CODE);
	}

	public void setPwb_consigneename(String PWB_CONSIGNEENAME) {
		this.setFieldValue(12, PWB_CONSIGNEENAME);
	}

	public String getPwb_consigneename() {
		return this.getFieldValue(12);
	}

	public void setPwb_consigneenameCondition(String PWB_CONSIGNEENAME) {
		this.setConditionValue(12, PWB_CONSIGNEENAME);
	}

	public void setPwb_consigneetel(String PWB_CONSIGNEETEL) {
		this.setFieldValue(13, PWB_CONSIGNEETEL);
	}

	public String getPwb_consigneetel() {
		return this.getFieldValue(13);
	}

	public void setPwb_consigneetelCondition(String PWB_CONSIGNEETEL) {
		this.setConditionValue(13, PWB_CONSIGNEETEL);
	}

	public void setPwb_consigneeaddress1(String PWB_CONSIGNEEADDRESS1) {
		this.setFieldValue(14, PWB_CONSIGNEEADDRESS1);
	}

	public String getPwb_consigneeaddress1() {
		return this.getFieldValue(14);
	}

	public void setPwb_consigneeaddress1Condition(String PWB_CONSIGNEEADDRESS1) {
		this.setConditionValue(14, PWB_CONSIGNEEADDRESS1);
	}

	public void setPwb_consigneeaddress2(String PWB_CONSIGNEEADDRESS2) {
		this.setFieldValue(15, PWB_CONSIGNEEADDRESS2);
	}

	public String getPwb_consigneeaddress2() {
		return this.getFieldValue(15);
	}

	public void setPwb_consigneeaddress2Condition(String PWB_CONSIGNEEADDRESS2) {
		this.setConditionValue(15, PWB_CONSIGNEEADDRESS2);
	}

	public void setPwb_consigneecity(String PWB_CONSIGNEECITY) {
		this.setFieldValue(16, PWB_CONSIGNEECITY);
	}

	public String getPwb_consigneecity() {
		return this.getFieldValue(16);
	}

	public void setPwb_consigneecityCondition(String PWB_CONSIGNEECITY) {
		this.setConditionValue(16, PWB_CONSIGNEECITY);
	}

	public void setPwb_consigneestate(String PWB_CONSIGNEESTATE) {
		this.setFieldValue(17, PWB_CONSIGNEESTATE);
	}

	public String getPwb_consigneestate() {
		return this.getFieldValue(17);
	}

	public void setPwb_consigneestateCondition(String PWB_CONSIGNEESTATE) {
		this.setConditionValue(17, PWB_CONSIGNEESTATE);
	}

	public void setPwb_consigneepostcode(String PWB_CONSIGNEEPOSTCODE) {
		this.setFieldValue(18, PWB_CONSIGNEEPOSTCODE);
	}

	public String getPwb_consigneepostcode() {
		return this.getFieldValue(18);
	}

	public void setPwb_consigneepostcodeCondition(String PWB_CONSIGNEEPOSTCODE) {
		this.setConditionValue(18, PWB_CONSIGNEEPOSTCODE);
	}

	public void setPwb_cargoename(String PWB_CARGOENAME) {
		this.setFieldValue(19, PWB_CARGOENAME);
	}

	public String getPwb_cargoename() {
		return this.getFieldValue(19);
	}

	public void setPwb_cargoenameCondition(String PWB_CARGOENAME) {
		this.setConditionValue(19, PWB_CARGOENAME);
	}

	public void setPwb_cargopieces(String PWB_CARGOPIECES) {
		this.setFieldValue(20, PWB_CARGOPIECES);
	}

	public String getPwb_cargopieces() {
		return this.getFieldValue(20);
	}

	public void setPwb_cargopiecesCondition(String PWB_CARGOPIECES) {
		this.setConditionValue(20, PWB_CARGOPIECES);
	}

	public void setPwb_cargoamount(String PWB_CARGOAMOUNT) {
		this.setFieldValue(21, PWB_CARGOAMOUNT);
	}

	public String getPwb_cargoamount() {
		return this.getFieldValue(21);
	}

	public void setPwb_cargoamountCondition(String PWB_CARGOAMOUNT) {
		this.setConditionValue(21, PWB_CARGOAMOUNT);
	}

	public void setPwb_transactionid(String PWB_TRANSACTIONID) {
		this.setFieldValue(22, PWB_TRANSACTIONID);
	}

	public String getPwb_transactionid() {
		return this.getFieldValue(22);
	}

	public void setPwb_transactionidCondition(String PWB_TRANSACTIONID) {
		this.setConditionValue(22, PWB_TRANSACTIONID);
	}

	public void setPwb_orderid(String PWB_ORDERID) {
		this.setFieldValue(23, PWB_ORDERID);
	}

	public String getPwb_orderid() {
		return this.getFieldValue(23);
	}

	public void setPwb_orderidCondition(String PWB_ORDERID) {
		this.setConditionValue(23, PWB_ORDERID);
	}

	public void setPwb_serverewbcode(String PWB_SERVEREWBCODE) {
		this.setFieldValue(24, PWB_SERVEREWBCODE);
	}

	public String getPwb_serverewbcode() {
		return this.getFieldValue(24);
	}

	public void setPwb_serverewbcodeCondition(String PWB_SERVEREWBCODE) {
		this.setConditionValue(24, PWB_SERVEREWBCODE);
	}

	public void setPwb_chargeweight(String PWB_CHARGEWEIGHT) {
		this.setFieldValue(25, PWB_CHARGEWEIGHT);
	}

	public String getPwb_chargeweight() {
		return this.getFieldValue(25);
	}

	public void setPwb_chargeweightCondition(String PWB_CHARGEWEIGHT) {
		this.setConditionValue(25, PWB_CHARGEWEIGHT);
	}

	public void setPwb_customremark(String PWB_CUSTOMREMARK) {
		this.setFieldValue(26, PWB_CUSTOMREMARK);
	}

	public String getPwb_customremark() {
		return this.getFieldValue(26);
	}

	public void setPwb_customremarkCondition(String PWB_CUSTOMREMARK) {
		this.setConditionValue(26, PWB_CUSTOMREMARK);
	}

	public void setPwb_consigneenameex(String PWB_CONSIGNEENAMEEX) {
		this.setFieldValue(27, PWB_CONSIGNEENAMEEX);
	}

	public String getPwb_consigneenameex() {
		return this.getFieldValue(27);
	}

	public void setPwb_consigneenameexCondition(String PWB_CONSIGNEENAMEEX) {
		this.setConditionValue(27, PWB_CONSIGNEENAMEEX);
	}

	public void setPwb_consigneeaddressex(String PWB_CONSIGNEEADDRESSEX) {
		this.setFieldValue(28, PWB_CONSIGNEEADDRESSEX);
	}

	public String getPwb_consigneeaddressex() {
		return this.getFieldValue(28);
	}

	public void setPwb_consigneeaddressexCondition(String PWB_CONSIGNEEADDRESSEX) {
		this.setConditionValue(28, PWB_CONSIGNEEADDRESSEX);
	}

	public void setPwb_consigneecityex(String PWB_CONSIGNEECITYEX) {
		this.setFieldValue(29, PWB_CONSIGNEECITYEX);
	}

	public String getPwb_consigneecityex() {
		return this.getFieldValue(29);
	}

	public void setPwb_consigneecityexCondition(String PWB_CONSIGNEECITYEX) {
		this.setConditionValue(29, PWB_CONSIGNEECITYEX);
	}

	public void setPwb_createdate(String PWB_CREATEDATE) {
		this.setFieldValue(30, PWB_CREATEDATE);
	}

	public String getPwb_createdate() {
		return this.getFieldValue(30);
	}

	public void setPwb_createdateCondition(String PWB_CREATEDATE) {
		this.setConditionValue(30, PWB_CREATEDATE);
	}

	public void setPwb_modifydate(String PWB_MODIFYDATE) {
		this.setFieldValue(31, PWB_MODIFYDATE);
	}

	public String getPwb_modifydate() {
		return this.getFieldValue(31);
	}

	public void setPwb_modifydateCondition(String PWB_MODIFYDATE) {
		this.setConditionValue(31, PWB_MODIFYDATE);
	}

	public void setPwb_declaredate(String PWB_DECLAREDATE) {
		this.setFieldValue(32, PWB_DECLAREDATE);
	}

	public String getPwb_declaredate() {
		return this.getFieldValue(32);
	}

	public void setPwb_declaredateCondition(String PWB_DECLAREDATE) {
		this.setConditionValue(32, PWB_DECLAREDATE);
	}

	public void setPwb_printdate(String PWB_PRINTDATE) {
		this.setFieldValue(33, PWB_PRINTDATE);
	}

	public String getPwb_printdate() {
		return this.getFieldValue(33);
	}

	public void setPwb_printdateCondition(String PWB_PRINTDATE) {
		this.setConditionValue(33, PWB_PRINTDATE);
	}

	public void setPwb_buyerid(String PWB_BUYERID) {
		this.setFieldValue(34, PWB_BUYERID);
	}

	public String getPwb_buyerid() {
		return this.getFieldValue(34);
	}

	public void setPwb_buyeridCondition(String PWB_BUYERID) {
		this.setConditionValue(34, PWB_BUYERID);
	}

	public void setBk_code(String BK_CODE) {
		this.setFieldValue(35, BK_CODE);
	}

	public String getBk_code() {
		return this.getFieldValue(35);
	}

	public void setBk_codeCondition(String BK_CODE) {
		this.setConditionValue(35, BK_CODE);
	}

	public void setDt_code(String DT_CODE) {
		this.setFieldValue(36, DT_CODE);
	}

	public String getDt_code() {
		return this.getFieldValue(36);
	}

	public void setDt_codeCondition(String DT_CODE) {
		this.setConditionValue(36, DT_CODE);
	}

	public void setCgk_code(String CGK_CODE) {
		this.setFieldValue(37, CGK_CODE);
	}

	public String getCgk_code() {
		return this.getFieldValue(37);
	}

	public void setCgk_codeCondition(String CGK_CODE) {
		this.setConditionValue(37, CGK_CODE);
	}

	public void setPat_code(String PAT_CODE) {
		this.setFieldValue(38, PAT_CODE);
	}

	public String getPat_code() {
		return this.getFieldValue(38);
	}

	public void setPat_codeCondition(String PAT_CODE) {
		this.setConditionValue(38, PAT_CODE);
	}

	public void setPwb_dutypaidsign(String PWB_DUTYPAIDSIGN) {
		this.setFieldValue(39, PWB_DUTYPAIDSIGN);
	}

	public String getPwb_dutypaidsign() {
		return this.getFieldValue(39);
	}

	public void setPwb_dutypaidsignCondition(String PWB_DUTYPAIDSIGN) {
		this.setConditionValue(39, PWB_DUTYPAIDSIGN);
	}

	public void setPwb_pieces(String PWB_PIECES) {
		this.setFieldValue(40, PWB_PIECES);
	}

	public String getPwb_pieces() {
		return this.getFieldValue(40);
	}

	public void setPwb_piecesCondition(String PWB_PIECES) {
		this.setConditionValue(40, PWB_PIECES);
	}


}
