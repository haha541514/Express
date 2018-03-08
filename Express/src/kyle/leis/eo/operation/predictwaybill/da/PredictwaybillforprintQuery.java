package kyle.leis.eo.operation.predictwaybill.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class PredictwaybillforprintQuery extends JGeneralQuery {
	
	public PredictwaybillforprintQuery(){
	    m_strSelectClause = "SELECT pwb.PWB_Code,pwb.PWB_ConsigneeTel,pwb.PWB_ConsigneeCity,pwb.PWB_ConsigneeState,pwb.PWB_ConsigneePostcode,pwb.PWB_CargoEname,pwb.PWB_CargoPieces,pwb.PWB_CargoAmount,pwb.PWB_TransactionID,pwb.PWB_OrderID,pwb.PWB_Chargeweight,pwb.PWB_CustomRemark,pwb.PWB_ConsigneeNameEX,pwb.PWB_ConsigneeAddressEX,pwb.PWB_ConsigneeCityEX,pwb.PWB_ServerEWBCode FROM T_OP_PREDICTWAYBILL pwb";
	    m_strWhereClause = "";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "pwb.PWB_CreateDate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= pwb.PWB_CreateDate", "co.CO_Code = '~~'", "cop.OP_ID = ~~", "pwb.PWB_Code = ~~" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new PredictwaybillforprintColumns();
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

	public void setCo_code_customer(String co_code_customer) {
		this.setField(2, co_code_customer);
	}

	public String getCo_code_customer() {
		return this.getField(2);
	}

	public void setOp_id_creator(String op_id_creator) {
		this.setField(3, op_id_creator);
	}

	public String getOp_id_creator() {
		return this.getField(3);
	}

	public void setPwb_code(String pwb_code) {
		this.setField(4, pwb_code);
	}

	public String getPwb_code() {
		return this.getField(4);
	}

}
