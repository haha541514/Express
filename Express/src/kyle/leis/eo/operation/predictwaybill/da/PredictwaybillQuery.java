package kyle.leis.eo.operation.predictwaybill.da;


import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class PredictwaybillQuery extends JGeneralQuery {
	
	public PredictwaybillQuery(){
	    m_strSelectClause = "SELECT pwb.PWB_DUTYPAIDSIGN,pwb.PWB_Code,pwb.PWB_CreateDate,pwb.PWB_ModifyDate,pwb.PWB_ConsigneeName,pwb.PWB_ConsigneeTel,pwb.PWB_ConsigneeAddress1,pwb.PWB_ConsigneeAddress2,pwb.PWB_ConsigneeCity,pwb.PWB_ConsigneeState,pwb.PWB_ConsigneePostcode,pwb.PWB_CargoEname,pwb.PWB_CargoPieces,pwb.PWB_CargoAmount,pwb.PWB_TransactionID,pwb.PWB_OrderID,pwb.PWB_Chargeweight,pwb.PWB_CustomRemark,pwbs.PWBS_Code,pwbs.PWBS_Name,pk.PK_Code,pk.PK_SName,co.CO_Code,co.CO_LabelCode,COP.OP_ID COPID,COP.OP_NAME COPNAME,MOP.OP_ID MOPID,MOP.OP_NAME MOPNAME,chn.chn_code,chn.CHN_SName,pwb.PWB_ServerEWBCode,dt.DT_Code,dt.DT_Name,pwb.cw_code,pwb.PWB_DeclareDate,pwb.PWB_PrintDate,POP.OP_ID POPID,POP.OP_NAME POPNAME,DOP.OP_ID DOPID,DOP.OP_NAME DOPNAME,pwb.PWB_ConsigneeNameEX,pwb.PWB_ConsigneeAddressEX,pwb.PWB_ConsigneeCityEX,pwb.PWB_Buyerid,pwb.CK_Code, '' as subconame, pwb.PWB_PIECES FROM T_OP_PREDICTWAYBILL pwb,T_DI_PREDICTWAYBILLSTATUS pwbs,T_DI_PRODUCTKIND pk,T_CO_CORPORATION co,T_DI_OPERATOR cop,T_DI_OPERATOR mop,T_DI_OPERATOR pop,T_DI_OPERATOR dop,T_CHN_CHANNEL chn,T_DI_DISTRICT dt";
	    m_strWhereClause = "pwb.PWBS_Code = pwbs.PWBS_Code and pwb.pk_code = pk.pk_code and pwb.CO_Code_Customer = co.co_code and pwb.pwb_destination = dt.dt_code and pwb.chn_code = chn.chn_code(+) and pwb.OP_ID_Creator = cop.op_id and pwb.OP_ID_Modifier = mop.op_id and pwb.OP_ID_Printer = pop.op_id(+) and pwb.OP_ID_Declarer = dop.op_id(+)";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "pwb.PWB_CreateDate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= pwb.PWB_CreateDate", "pwb.PWB_DeclareDate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= pwb.PWB_DeclareDate", "pwb.PWB_PrintDate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= pwb.PWB_PrintDate", "co.CO_Code = '~~'", "cop.OP_ID = ~~", "pwb.PWB_Code = ~~", "pwb.PWB_OrderID = '~~'", "pwbs.PWBS_Code in (~~)", "pk.PK_Code = '~~'", "dt.DT_Code = ~~", "pwb.PWB_CustomRemark = '~~'", "pwb.PWB_ConsigneeName = '~~'", "lower(pwb.PWB_consigneeaddress1||pwb.PWB_consigneeaddress2) like lower('%~~%')", "lower(pwb.PWB_cargoename) like lower('%~~%')", "pwb.CW_Code = '~~'", "pwb.PWB_DUTYPAIDSIGN='~~'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new PredictwaybillColumns();
	}
	
	public void setStartcreatedate(String StartCreateDate) {
		this.setField(0, StartCreateDate);
	}

	public String getStartcreatedate() {
		return this.getField(0);
	}

	public void setEndcreatedate(String EndCreateDate) {
		this.setField(1, EndCreateDate);
	}

	public String getEndcreatedate() {
		return this.getField(1);
	}

	public void setStartdeclaredate(String StartDeclareDate) {
		this.setField(2, StartDeclareDate);
	}

	public String getStartdeclaredate() {
		return this.getField(2);
	}

	public void setEnddeclaredate(String EndDeclareDate) {
		this.setField(3, EndDeclareDate);
	}

	public String getEnddeclaredate() {
		return this.getField(3);
	}

	public void setStartprintdate(String StartPrintDate) {
		this.setField(4, StartPrintDate);
	}

	public String getStartprintdate() {
		return this.getField(4);
	}

	public void setEndprintdate(String EndPrintDate) {
		this.setField(5, EndPrintDate);
	}

	public String getEndprintdate() {
		return this.getField(5);
	}

	public void setCo_code_customer(String co_code_customer) {
		this.setField(6, co_code_customer);
	}

	public String getCo_code_customer() {
		return this.getField(6);
	}

	public void setOp_id_creator(String op_id_creator) {
		this.setField(7, op_id_creator);
	}

	public String getOp_id_creator() {
		return this.getField(7);
	}

	public void setPwb_code(String pwb_code) {
		this.setField(8, pwb_code);
	}

	public String getPwb_code() {
		return this.getField(8);
	}

	public void setPwb_orderid(String pwb_orderid) {
		this.setField(9, pwb_orderid);
	}

	public String getPwb_orderid() {
		return this.getField(9);
	}

	public void setPwbs_code(String pwbs_code) {
		this.setField(10, pwbs_code);
	}

	public String getPwbs_code() {
		return this.getField(10);
	}

	public void setPk_code(String pk_code) {
		this.setField(11, pk_code);
	}

	public String getPk_code() {
		return this.getField(11);
	}

	public void setDt_code(String dt_code) {
		this.setField(12, dt_code);
	}

	public String getDt_code() {
		return this.getField(12);
	}

	public void setPwb_customerremark(String pwb_customerremark) {
		this.setField(13, pwb_customerremark);
	}

	public String getPwb_customerremark() {
		return this.getField(13);
	}

	public void setPwb_consigneename(String pwb_consigneename) {
		this.setField(14, pwb_consigneename);
	}

	public String getPwb_consigneename() {
		return this.getField(14);
	}

	public void setLikepwbconsigneeaddress(String likepwbconsigneeaddress) {
		this.setField(15, likepwbconsigneeaddress);
	}

	public String getLikepwbconsigneeaddress() {
		return this.getField(15);
	}

	public void setLikepwbcargoename(String likepwbcargoename) {
		this.setField(16, likepwbcargoename);
	}

	public String getLikepwbcargoename() {
		return this.getField(16);
	}

	public void setCwcode(String cwcode) {
		this.setField(17, cwcode);
	}

	public String getCwcode() {
		return this.getField(17);
	}

	public void setPwb_dutypaidsign(String pwb_dutypaidsign) {
		this.setField(18, pwb_dutypaidsign);
	}

	public String getPwb_dutypaidsign() {
		return this.getField(18);
	}

}
