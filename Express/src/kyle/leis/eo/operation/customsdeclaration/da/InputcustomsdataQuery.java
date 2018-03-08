package kyle.leis.eo.operation.customsdeclaration.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class InputcustomsdataQuery extends JGeneralQuery {
	
	public InputcustomsdataQuery(){
	    m_strSelectClause = "SELECT cw.cw_serverewbcode,(select cp.cp_Baglabelcode from t_op_corewaybillpieces cp where cp.cw_code = cw.cw_code and rownum < 2) as baglabelcode,hw.hw_consigneename,hw.hw_consigneeaddress1||hw.hw_consigneeaddress2||hw.hw_consigneeaddress3 as hw_consigneeaddress,hw.hw_shipperaddress1||hw.hw_shipperaddress2||hw.hw_shipperaddress3 as hw_shipperaddress,'142' as shippercountry,shdt.dt_ename,cddt.dt_name, FUN_GET_CARGOINFOALLNAME(cw.cw_code) as CargoInfo, '' as cargotype,FUN_GET_CARGOHSCODE(cw.cw_code) as cargono,'142' as origincountry,cw.cw_pieces,cw.cw_chargeweight,FUN_GET_TOTALCARGOPCS(cw.cw_code) as TotalCargoPCS,'035' as Cargounit,'' as unitcargoinfo, FUN_GET_TOTALCARGOINFO(cw.cw_code) as TotalCargoInfo,'142' as currency,'2' as customstype,'3010' as commercetype,'1' as levytype,'' as company, '' as companytype, '' as companyname FROM t_op_corewaybill cw,t_op_housewaybill hw,t_di_district shdt,t_di_district ddt,t_di_district cddt,t_op_batchwaybill bw";
	    m_strWhereClause = "cw.cw_code = hw.cw_code and cw.bw_code_departure = bw.bw_code(+) and hw.dt_code_shipper = shdt.dt_code and cw.dt_code_destination = ddt.dt_code and ddt.dt_countcode = cddt.dt_code and cw.cws_code NOT IN ('EL', 'CEL', 'CTS', 'CHP')";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "hw.hw_signoutdate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= hw.hw_signoutdate", "cw.cw_code = ~~", "cw.cw_ewbcode = '~~'", "cw.cw_serverewbcode = '~~'", "cw.chn_code_supplier = '~~'", "bw.bw_labelcode = '~~'", "cw.ct_code = '~~'", "cd.cd_labelcode = '~~'", "exists (select cp.cp_id from t_op_corewaybillpieces cp where cp.cw_code = hw.cw_code and cp.cp_baglabelcode = '~~')", "exists (select * from t_op_transportwaybill tw,t_op_transportwaybillvalue twv,t_op_batchwaybill bw where tw.twb_id = twv.twb_id and twv.bw_code = bw.bw_code and bw.bw_code = cw.bw_code_departure and tw.twbs_code != 'E' and tw.twb_labelcode = '~~')", "cw.co_code_customer = '~~'", "FUN_GET_ESSTRUCTURECODE(cw.ee_code) like '~~%'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new InputcustomsdataColumns();
	}
	
	public void setStartdate(String StartDate) {
		this.setField(0, StartDate);
	}

	public String getStartdate() {
		return this.getField(0);
	}

	public void setEnddate(String EndDate) {
		this.setField(1, EndDate);
	}

	public String getEnddate() {
		return this.getField(1);
	}

	public void setCwcode(String cwcode) {
		this.setField(2, cwcode);
	}

	public String getCwcode() {
		return this.getField(2);
	}

	public void setCwewbcode(String cwEwbcode) {
		this.setField(3, cwEwbcode);
	}

	public String getCwewbcode() {
		return this.getField(3);
	}

	public void setCwserverewbcode(String cwServerewbcode) {
		this.setField(4, cwServerewbcode);
	}

	public String getCwserverewbcode() {
		return this.getField(4);
	}

	public void setChncodesupplier(String chncodeSupplier) {
		this.setField(5, chncodeSupplier);
	}

	public String getChncodesupplier() {
		return this.getField(5);
	}

	public void setBwcodedeparture(String bwcodeDeparture) {
		this.setField(6, bwcodeDeparture);
	}

	public String getBwcodedeparture() {
		return this.getField(6);
	}

	public void setCtcode(String ctCode) {
		this.setField(7, ctCode);
	}

	public String getCtcode() {
		return this.getField(7);
	}

	public void setCdlabelcode(String cdLabelcode) {
		this.setField(8, cdLabelcode);
	}

	public String getCdlabelcode() {
		return this.getField(8);
	}

	public void setCpbaglabelcode(String cpBaglabelcode) {
		this.setField(9, cpBaglabelcode);
	}

	public String getCpbaglabelcode() {
		return this.getField(9);
	}

	public void setTwblabelcode(String twbLabelcode) {
		this.setField(10, twbLabelcode);
	}

	public String getTwblabelcode() {
		return this.getField(10);
	}

	public void setCocodecustomer(String coCodecustomer) {
		this.setField(11, coCodecustomer);
	}

	public String getCocodecustomer() {
		return this.getField(11);
	}

	public void setEestructurecode(String eestructurecode) {
		this.setField(12, eestructurecode);
	}

	public String getEestructurecode() {
		return this.getField(12);
	}

}
