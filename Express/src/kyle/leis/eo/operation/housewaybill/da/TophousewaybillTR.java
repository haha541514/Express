package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.table.ITableRecord;
import kyle.common.dbaccess.table.TableRecord;

/**
 * ±í¼ÇÂ¼Àà
 */
public class TophousewaybillTR extends TableRecord {

	public TophousewaybillTR() {
		super(TophousewaybillTD.getDatabaseInstance());
	}

	public ITableRecord createInstance() {
		return new TophousewaybillTR();
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

	public void setDt_code_shipper(String DT_CODE_SHIPPER) {
		this.setFieldValue(1, DT_CODE_SHIPPER);
	}

	public String getDt_code_shipper() {
		return this.getFieldValue(1);
	}

	public void setDt_code_shipperCondition(String DT_CODE_SHIPPER) {
		this.setConditionValue(1, DT_CODE_SHIPPER);
	}

	public void setHw_shipperaccount(String HW_SHIPPERACCOUNT) {
		this.setFieldValue(2, HW_SHIPPERACCOUNT);
	}

	public String getHw_shipperaccount() {
		return this.getFieldValue(2);
	}

	public void setHw_shipperaccountCondition(String HW_SHIPPERACCOUNT) {
		this.setConditionValue(2, HW_SHIPPERACCOUNT);
	}

	public void setHw_shippername(String HW_SHIPPERNAME) {
		this.setFieldValue(3, HW_SHIPPERNAME);
	}

	public String getHw_shippername() {
		return this.getFieldValue(3);
	}

	public void setHw_shippernameCondition(String HW_SHIPPERNAME) {
		this.setConditionValue(3, HW_SHIPPERNAME);
	}

	public void setHw_shippercompany(String HW_SHIPPERCOMPANY) {
		this.setFieldValue(4, HW_SHIPPERCOMPANY);
	}

	public String getHw_shippercompany() {
		return this.getFieldValue(4);
	}

	public void setHw_shippercompanyCondition(String HW_SHIPPERCOMPANY) {
		this.setConditionValue(4, HW_SHIPPERCOMPANY);
	}

	public void setHw_shipperaddress1(String HW_SHIPPERADDRESS1) {
		this.setFieldValue(5, HW_SHIPPERADDRESS1);
	}

	public String getHw_shipperaddress1() {
		return this.getFieldValue(5);
	}

	public void setHw_shipperaddress1Condition(String HW_SHIPPERADDRESS1) {
		this.setConditionValue(5, HW_SHIPPERADDRESS1);
	}

	public void setHw_shipperaddress2(String HW_SHIPPERADDRESS2) {
		this.setFieldValue(6, HW_SHIPPERADDRESS2);
	}

	public String getHw_shipperaddress2() {
		return this.getFieldValue(6);
	}

	public void setHw_shipperaddress2Condition(String HW_SHIPPERADDRESS2) {
		this.setConditionValue(6, HW_SHIPPERADDRESS2);
	}

	public void setHw_shipperaddress3(String HW_SHIPPERADDRESS3) {
		this.setFieldValue(7, HW_SHIPPERADDRESS3);
	}

	public String getHw_shipperaddress3() {
		return this.getFieldValue(7);
	}

	public void setHw_shipperaddress3Condition(String HW_SHIPPERADDRESS3) {
		this.setConditionValue(7, HW_SHIPPERADDRESS3);
	}

	public void setHw_shipperpostcode(String HW_SHIPPERPOSTCODE) {
		this.setFieldValue(8, HW_SHIPPERPOSTCODE);
	}

	public String getHw_shipperpostcode() {
		return this.getFieldValue(8);
	}

	public void setHw_shipperpostcodeCondition(String HW_SHIPPERPOSTCODE) {
		this.setConditionValue(8, HW_SHIPPERPOSTCODE);
	}

	public void setHw_shippertelephone(String HW_SHIPPERTELEPHONE) {
		this.setFieldValue(9, HW_SHIPPERTELEPHONE);
	}

	public String getHw_shippertelephone() {
		return this.getFieldValue(9);
	}

	public void setHw_shippertelephoneCondition(String HW_SHIPPERTELEPHONE) {
		this.setConditionValue(9, HW_SHIPPERTELEPHONE);
	}

	public void setHw_shipperfax(String HW_SHIPPERFAX) {
		this.setFieldValue(10, HW_SHIPPERFAX);
	}

	public String getHw_shipperfax() {
		return this.getFieldValue(10);
	}

	public void setHw_shipperfaxCondition(String HW_SHIPPERFAX) {
		this.setConditionValue(10, HW_SHIPPERFAX);
	}

	public void setHw_consigneename(String HW_CONSIGNEENAME) {
		this.setFieldValue(11, HW_CONSIGNEENAME);
	}

	public String getHw_consigneename() {
		return this.getFieldValue(11);
	}

	public void setHw_consigneenameCondition(String HW_CONSIGNEENAME) {
		this.setConditionValue(11, HW_CONSIGNEENAME);
	}

	public void setHw_consigneecompany(String HW_CONSIGNEECOMPANY) {
		this.setFieldValue(12, HW_CONSIGNEECOMPANY);
	}

	public String getHw_consigneecompany() {
		return this.getFieldValue(12);
	}

	public void setHw_consigneecompanyCondition(String HW_CONSIGNEECOMPANY) {
		this.setConditionValue(12, HW_CONSIGNEECOMPANY);
	}

	public void setHw_consigneepostcode(String HW_CONSIGNEEPOSTCODE) {
		this.setFieldValue(13, HW_CONSIGNEEPOSTCODE);
	}

	public String getHw_consigneepostcode() {
		return this.getFieldValue(13);
	}

	public void setHw_consigneepostcodeCondition(String HW_CONSIGNEEPOSTCODE) {
		this.setConditionValue(13, HW_CONSIGNEEPOSTCODE);
	}

	public void setHw_consigneeaddress1(String HW_CONSIGNEEADDRESS1) {
		this.setFieldValue(14, HW_CONSIGNEEADDRESS1);
	}

	public String getHw_consigneeaddress1() {
		return this.getFieldValue(14);
	}

	public void setHw_consigneeaddress1Condition(String HW_CONSIGNEEADDRESS1) {
		this.setConditionValue(14, HW_CONSIGNEEADDRESS1);
	}

	public void setHw_consigneeaddress2(String HW_CONSIGNEEADDRESS2) {
		this.setFieldValue(15, HW_CONSIGNEEADDRESS2);
	}

	public String getHw_consigneeaddress2() {
		return this.getFieldValue(15);
	}

	public void setHw_consigneeaddress2Condition(String HW_CONSIGNEEADDRESS2) {
		this.setConditionValue(15, HW_CONSIGNEEADDRESS2);
	}

	public void setHw_consigneeaddress3(String HW_CONSIGNEEADDRESS3) {
		this.setFieldValue(16, HW_CONSIGNEEADDRESS3);
	}

	public String getHw_consigneeaddress3() {
		return this.getFieldValue(16);
	}

	public void setHw_consigneeaddress3Condition(String HW_CONSIGNEEADDRESS3) {
		this.setConditionValue(16, HW_CONSIGNEEADDRESS3);
	}

	public void setHw_consigneetelephone(String HW_CONSIGNEETELEPHONE) {
		this.setFieldValue(17, HW_CONSIGNEETELEPHONE);
	}

	public String getHw_consigneetelephone() {
		return this.getFieldValue(17);
	}

	public void setHw_consigneetelephoneCondition(String HW_CONSIGNEETELEPHONE) {
		this.setConditionValue(17, HW_CONSIGNEETELEPHONE);
	}

	public void setHw_consigneeaccount(String HW_CONSIGNEEACCOUNT) {
		this.setFieldValue(18, HW_CONSIGNEEACCOUNT);
	}

	public String getHw_consigneeaccount() {
		return this.getFieldValue(18);
	}

	public void setHw_consigneeaccountCondition(String HW_CONSIGNEEACCOUNT) {
		this.setConditionValue(18, HW_CONSIGNEEACCOUNT);
	}

	public void setHw_consigneefax(String HW_CONSIGNEEFAX) {
		this.setFieldValue(19, HW_CONSIGNEEFAX);
	}

	public String getHw_consigneefax() {
		return this.getFieldValue(19);
	}

	public void setHw_consigneefaxCondition(String HW_CONSIGNEEFAX) {
		this.setConditionValue(19, HW_CONSIGNEEFAX);
	}

	public void setHw_signindate(String HW_SIGNINDATE) {
		this.setFieldValue(20, HW_SIGNINDATE);
	}

	public String getHw_signindate() {
		return this.getFieldValue(20);
	}

	public void setHw_signindateCondition(String HW_SIGNINDATE) {
		this.setConditionValue(20, HW_SIGNINDATE);
	}

	public void setHw_op_id_signin(String HW_OP_ID_SIGNIN) {
		this.setFieldValue(21, HW_OP_ID_SIGNIN);
	}

	public String getHw_op_id_signin() {
		return this.getFieldValue(21);
	}

	public void setHw_op_id_signinCondition(String HW_OP_ID_SIGNIN) {
		this.setConditionValue(21, HW_OP_ID_SIGNIN);
	}

	public void setHw_op_id_weighting(String HW_OP_ID_WEIGHTING) {
		this.setFieldValue(22, HW_OP_ID_WEIGHTING);
	}

	public String getHw_op_id_weighting() {
		return this.getFieldValue(22);
	}

	public void setHw_op_id_weightingCondition(String HW_OP_ID_WEIGHTING) {
		this.setConditionValue(22, HW_OP_ID_WEIGHTING);
	}

	public void setHw_signoutdate(String HW_SIGNOUTDATE) {
		this.setFieldValue(23, HW_SIGNOUTDATE);
	}

	public String getHw_signoutdate() {
		return this.getFieldValue(23);
	}

	public void setHw_signoutdateCondition(String HW_SIGNOUTDATE) {
		this.setConditionValue(23, HW_SIGNOUTDATE);
	}

	public void setHw_op_id_signout(String HW_OP_ID_SIGNOUT) {
		this.setFieldValue(24, HW_OP_ID_SIGNOUT);
	}

	public String getHw_op_id_signout() {
		return this.getFieldValue(24);
	}

	public void setHw_op_id_signoutCondition(String HW_OP_ID_SIGNOUT) {
		this.setConditionValue(24, HW_OP_ID_SIGNOUT);
	}

	public void setHw_recorddate(String HW_RECORDDATE) {
		this.setFieldValue(25, HW_RECORDDATE);
	}

	public String getHw_recorddate() {
		return this.getFieldValue(25);
	}

	public void setHw_recorddateCondition(String HW_RECORDDATE) {
		this.setConditionValue(25, HW_RECORDDATE);
	}

	public void setHw_op_id_record(String HW_OP_ID_RECORD) {
		this.setFieldValue(26, HW_OP_ID_RECORD);
	}

	public String getHw_op_id_record() {
		return this.getFieldValue(26);
	}

	public void setHw_op_id_recordCondition(String HW_OP_ID_RECORD) {
		this.setConditionValue(26, HW_OP_ID_RECORD);
	}

	public void setHw_op_id_packing(String HW_OP_ID_PACKING) {
		this.setFieldValue(27, HW_OP_ID_PACKING);
	}

	public String getHw_op_id_packing() {
		return this.getFieldValue(27);
	}

	public void setHw_op_id_packingCondition(String HW_OP_ID_PACKING) {
		this.setConditionValue(27, HW_OP_ID_PACKING);
	}

	public void setHw_insurancevalue(String HW_INSURANCEVALUE) {
		this.setFieldValue(28, HW_INSURANCEVALUE);
	}

	public String getHw_insurancevalue() {
		return this.getFieldValue(28);
	}

	public void setHw_insurancevalueCondition(String HW_INSURANCEVALUE) {
		this.setConditionValue(28, HW_INSURANCEVALUE);
	}

	public void setHw_insurancesign(String HW_INSURANCESIGN) {
		this.setFieldValue(29, HW_INSURANCESIGN);
	}

	public String getHw_insurancesign() {
		return this.getFieldValue(29);
	}

	public void setHw_insurancesignCondition(String HW_INSURANCESIGN) {
		this.setConditionValue(29, HW_INSURANCESIGN);
	}

	public void setHw_consigneecity(String HW_CONSIGNEECITY) {
		this.setFieldValue(30, HW_CONSIGNEECITY);
	}

	public String getHw_consigneecity() {
		return this.getFieldValue(30);
	}

	public void setHw_consigneecityCondition(String HW_CONSIGNEECITY) {
		this.setConditionValue(30, HW_CONSIGNEECITY);
	}

	public void setHw_insurancecurrency(String HW_INSURANCECURRENCY) {
		this.setFieldValue(31, HW_INSURANCECURRENCY);
	}

	public String getHw_insurancecurrency() {
		return this.getFieldValue(31);
	}

	public void setHw_insurancecurrencyCondition(String HW_INSURANCECURRENCY) {
		this.setConditionValue(31, HW_INSURANCECURRENCY);
	}

	public void setHw_labelprinttimes(String HW_LABELPRINTTIMES) {
		this.setFieldValue(32, HW_LABELPRINTTIMES);
	}

	public String getHw_labelprinttimes() {
		return this.getFieldValue(32);
	}

	public void setHw_labelprinttimesCondition(String HW_LABELPRINTTIMES) {
		this.setConditionValue(32, HW_LABELPRINTTIMES);
	}

	public void setHw_remark(String HW_REMARK) {
		this.setFieldValue(33, HW_REMARK);
	}

	public String getHw_remark() {
		return this.getFieldValue(33);
	}

	public void setHw_remarkCondition(String HW_REMARK) {
		this.setConditionValue(33, HW_REMARK);
	}

	public void setHw_customslabelprintsign(String HW_CUSTOMSLABELPRINTSIGN) {
		this.setFieldValue(34, HW_CUSTOMSLABELPRINTSIGN);
	}

	public String getHw_customslabelprintsign() {
		return this.getFieldValue(34);
	}

	public void setHw_customslabelprintsignCondition(String HW_CUSTOMSLABELPRINTSIGN) {
		this.setConditionValue(34, HW_CUSTOMSLABELPRINTSIGN);
	}

	public void setHw_serverewbkind(String HW_SERVEREWBKIND) {
		this.setFieldValue(35, HW_SERVEREWBKIND);
	}

	public String getHw_serverewbkind() {
		return this.getFieldValue(35);
	}

	public void setHw_serverewbkindCondition(String HW_SERVEREWBKIND) {
		this.setConditionValue(35, HW_SERVEREWBKIND);
	}

	public void setHw_serverewbchangedsign(String HW_SERVEREWBCHANGEDSIGN) {
		this.setFieldValue(36, HW_SERVEREWBCHANGEDSIGN);
	}

	public String getHw_serverewbchangedsign() {
		return this.getFieldValue(36);
	}

	public void setHw_serverewbchangedsignCondition(String HW_SERVEREWBCHANGEDSIGN) {
		this.setConditionValue(36, HW_SERVEREWBCHANGEDSIGN);
	}

	public void setHw_subchildewbkind(String HW_SUBCHILDEWBKIND) {
		this.setFieldValue(37, HW_SUBCHILDEWBKIND);
	}

	public String getHw_subchildewbkind() {
		return this.getFieldValue(37);
	}

	public void setHw_subchildewbkindCondition(String HW_SUBCHILDEWBKIND) {
		this.setConditionValue(37, HW_SUBCHILDEWBKIND);
	}

	public void setHw_channelmasteraccount(String HW_CHANNELMASTERACCOUNT) {
		this.setFieldValue(38, HW_CHANNELMASTERACCOUNT);
	}

	public String getHw_channelmasteraccount() {
		return this.getFieldValue(38);
	}

	public void setHw_channelmasteraccountCondition(String HW_CHANNELMASTERACCOUNT) {
		this.setConditionValue(38, HW_CHANNELMASTERACCOUNT);
	}

	public void setHw_labelprintremark(String HW_LABELPRINTREMARK) {
		this.setFieldValue(39, HW_LABELPRINTREMARK);
	}

	public String getHw_labelprintremark() {
		return this.getFieldValue(39);
	}

	public void setHw_labelprintremarkCondition(String HW_LABELPRINTREMARK) {
		this.setConditionValue(39, HW_LABELPRINTREMARK);
	}

	public void setHw_cargoprinttimes(String HW_CARGOPRINTTIMES) {
		this.setFieldValue(40, HW_CARGOPRINTTIMES);
	}

	public String getHw_cargoprinttimes() {
		return this.getFieldValue(40);
	}

	public void setHw_cargoprinttimesCondition(String HW_CARGOPRINTTIMES) {
		this.setConditionValue(40, HW_CARGOPRINTTIMES);
	}

	public void setHw_op_id_weightcheck(String HW_OP_ID_WEIGHTCHECK) {
		this.setFieldValue(41, HW_OP_ID_WEIGHTCHECK);
	}

	public String getHw_op_id_weightcheck() {
		return this.getFieldValue(41);
	}

	public void setHw_op_id_weightcheckCondition(String HW_OP_ID_WEIGHTCHECK) {
		this.setConditionValue(41, HW_OP_ID_WEIGHTCHECK);
	}

	public void setHw_weightcheckkind(String HW_WEIGHTCHECKKIND) {
		this.setFieldValue(42, HW_WEIGHTCHECKKIND);
	}

	public String getHw_weightcheckkind() {
		return this.getFieldValue(42);
	}

	public void setHw_weightcheckkindCondition(String HW_WEIGHTCHECKKIND) {
		this.setConditionValue(42, HW_WEIGHTCHECKKIND);
	}

	public void setHw_weightcheckdate(String HW_WEIGHTCHECKDATE) {
		this.setFieldValue(43, HW_WEIGHTCHECKDATE);
	}

	public String getHw_weightcheckdate() {
		return this.getFieldValue(43);
	}

	public void setHw_weightcheckdateCondition(String HW_WEIGHTCHECKDATE) {
		this.setConditionValue(43, HW_WEIGHTCHECKDATE);
	}

	public void setHw_buyerid(String HW_BUYERID) {
		this.setFieldValue(44, HW_BUYERID);
	}

	public String getHw_buyerid() {
		return this.getFieldValue(44);
	}

	public void setHw_buyeridCondition(String HW_BUYERID) {
		this.setConditionValue(44, HW_BUYERID);
	}

	public void setHw_transactionid(String HW_TRANSACTIONID) {
		this.setFieldValue(45, HW_TRANSACTIONID);
	}

	public String getHw_transactionid() {
		return this.getFieldValue(45);
	}

	public void setHw_transactionidCondition(String HW_TRANSACTIONID) {
		this.setConditionValue(45, HW_TRANSACTIONID);
	}

	public void setHw_customerlabelprintdate(String HW_CUSTOMERLABELPRINTDATE) {
		this.setFieldValue(46, HW_CUSTOMERLABELPRINTDATE);
	}

	public String getHw_customerlabelprintdate() {
		return this.getFieldValue(46);
	}

	public void setHw_customerlabelprintdateCondition(String HW_CUSTOMERLABELPRINTDATE) {
		this.setConditionValue(46, HW_CUSTOMERLABELPRINTDATE);
	}

	public void setHw_attacheinfosign(String HW_ATTACHEINFOSIGN) {
		this.setFieldValue(47, HW_ATTACHEINFOSIGN);
	}

	public String getHw_attacheinfosign() {
		return this.getFieldValue(47);
	}

	public void setHw_attacheinfosignCondition(String HW_ATTACHEINFOSIGN) {
		this.setConditionValue(47, HW_ATTACHEINFOSIGN);
	}

	public void setHw_customerdeclaredate(String HW_CUSTOMERDECLAREDATE) {
		this.setFieldValue(48, HW_CUSTOMERDECLAREDATE);
	}

	public String getHw_customerdeclaredate() {
		return this.getFieldValue(48);
	}

	public void setHw_customerdeclaredateCondition(String HW_CUSTOMERDECLAREDATE) {
		this.setConditionValue(48, HW_CUSTOMERDECLAREDATE);
	}

	public void setHw_consigneeaddressex(String HW_CONSIGNEEADDRESSEX) {
		this.setFieldValue(49, HW_CONSIGNEEADDRESSEX);
	}

	public String getHw_consigneeaddressex() {
		return this.getFieldValue(49);
	}

	public void setHw_consigneeaddressexCondition(String HW_CONSIGNEEADDRESSEX) {
		this.setConditionValue(49, HW_CONSIGNEEADDRESSEX);
	}

	public void setHw_consigneenameex(String HW_CONSIGNEENAMEEX) {
		this.setFieldValue(50, HW_CONSIGNEENAMEEX);
	}

	public String getHw_consigneenameex() {
		return this.getFieldValue(50);
	}

	public void setHw_consigneenameexCondition(String HW_CONSIGNEENAMEEX) {
		this.setConditionValue(50, HW_CONSIGNEENAMEEX);
	}

	public void setHw_owninputcwauditsign(String HW_OWNINPUTCWAUDITSIGN) {
		this.setFieldValue(51, HW_OWNINPUTCWAUDITSIGN);
	}

	public String getHw_owninputcwauditsign() {
		return this.getFieldValue(51);
	}

	public void setHw_owninputcwauditsignCondition(String HW_OWNINPUTCWAUDITSIGN) {
		this.setConditionValue(51, HW_OWNINPUTCWAUDITSIGN);
	}

	public void setHw_arrearsignout(String HW_ARREARSIGNOUT) {
		this.setFieldValue(52, HW_ARREARSIGNOUT);
	}

	public String getHw_arrearsignout() {
		return this.getFieldValue(52);
	}

	public void setHw_arrearsignoutCondition(String HW_ARREARSIGNOUT) {
		this.setConditionValue(52, HW_ARREARSIGNOUT);
	}

	public void setHw_electricmark(String HW_ELECTRICMARK) {
		this.setFieldValue(53, HW_ELECTRICMARK);
	}

	public String getHw_electricmark() {
		return this.getFieldValue(53);
	}

	public void setHw_electricmarkCondition(String HW_ELECTRICMARK) {
		this.setConditionValue(53, HW_ELECTRICMARK);
	}

	public void setHw_sewbchangedbywebsign(String HW_SEWBCHANGEDBYWEBSIGN) {
		this.setFieldValue(54, HW_SEWBCHANGEDBYWEBSIGN);
	}

	public String getHw_sewbchangedbywebsign() {
		return this.getFieldValue(54);
	}

	public void setHw_sewbchangedbywebsignCondition(String HW_SEWBCHANGEDBYWEBSIGN) {
		this.setConditionValue(54, HW_SEWBCHANGEDBYWEBSIGN);
	}

	public void setHw_bookingid(String HW_BOOKINGID) {
		this.setFieldValue(55, HW_BOOKINGID);
	}

	public String getHw_bookingid() {
		return this.getFieldValue(55);
	}

	public void setHw_bookingidCondition(String HW_BOOKINGID) {
		this.setConditionValue(55, HW_BOOKINGID);
	}

	public void setHw_paymentaccount(String HW_PAYMENTACCOUNT) {
		this.setFieldValue(56, HW_PAYMENTACCOUNT);
	}

	public String getHw_paymentaccount() {
		return this.getFieldValue(56);
	}

	public void setHw_paymentaccountCondition(String HW_PAYMENTACCOUNT) {
		this.setConditionValue(56, HW_PAYMENTACCOUNT);
	}

	public void setHw_consigneecityex(String HW_CONSIGNEECITYEX) {
		this.setFieldValue(57, HW_CONSIGNEECITYEX);
	}

	public String getHw_consigneecityex() {
		return this.getFieldValue(57);
	}

	public void setHw_consigneecityexCondition(String HW_CONSIGNEECITYEX) {
		this.setConditionValue(57, HW_CONSIGNEECITYEX);
	}


}
