package kyle.leis.ds.report.finance.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class CustomerselfhawbQuery extends JGeneralQuery {
	
	public CustomerselfhawbQuery(){
	    m_strSelectClause = "SELECT cw.cw_customerewbcode,cw.cw_serverewbcode,FUN_GET_CARGOINFO(cw.cw_code) as ciename,ct.ct_name,cw.cw_pieces,cw.cw_chargeweight,FUN_GET_TOTALCARGOINFO(cw.cw_code) as citotal,cdt.dt_hubcode,hw.hw_shippercompany,hw.hw_shippername,hw.hw_shipperaddress1||hw.hw_shipperaddress2||hw.hw_shipperaddress3 as hw_shipperaddress,hw.hw_consigneecompany,hw.hw_consigneename,hw.hw_consigneeaddress1||hw.hw_consigneeaddress2||hw.hw_consigneeaddress3 as hw_consigneeaddress,hw.hw_signindate,hw.hw_signoutdate,ee.ee_sname FROM t_op_corewaybill cw,t_op_housewaybill hw,t_di_cargotype ct,t_di_district dt,t_di_district cdt,T_DI_ENTERPRISEELEMENT ee";
	    m_strWhereClause = "cw.cw_code = hw.cw_code and cw.ct_code = ct.ct_code and cw.ee_code = ee.ee_code and cw.dt_code_destination = dt.dt_code and dt.dt_countcode = cdt.dt_code and cw.cws_code in ('SI', 'IP', 'SO')";
	    m_strOrderByClause = "";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "cw.cw_customerewbcode = '~~'", "cw.cw_serverewbcode = '~~'", "hw.hw_signindate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') > hw.hw_signindate", "cdt.dt_code = ~~", "cw.ct_code = '~~'", "(cw.cw_customerewbcode in (~~) OR cw.cw_serverewbcode in (~~))", "cw.co_code_customer = ~~", "hw.hw_signoutdate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') > hw.hw_signoutdate", "ee.EE_Structurecode like '~~%'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new CustomerselfhawbColumns();
	}
	
	public void setCwcustomerewbcode(String cwcustomerewbcode) {
		this.setField(0, cwcustomerewbcode);
	}

	public String getCwcustomerewbcode() {
		return this.getField(0);
	}

	public void setCwserverewbcode(String cwserverewbcode) {
		this.setField(1, cwserverewbcode);
	}

	public String getCwserverewbcode() {
		return this.getField(1);
	}

	public void setStartsignindate(String startsignindate) {
		this.setField(2, startsignindate);
	}

	public String getStartsignindate() {
		return this.getField(2);
	}

	public void setEndsignindate(String Endsignindate) {
		this.setField(3, Endsignindate);
	}

	public String getEndsignindate() {
		return this.getField(3);
	}

	public void setDtcode(String dtcode) {
		this.setField(4, dtcode);
	}

	public String getDtcode() {
		return this.getField(4);
	}

	public void setCtcode(String ctcode) {
		this.setField(5, ctcode);
	}

	public String getCtcode() {
		return this.getField(5);
	}

	public void setIncustomerewbcode(String InCustomerewbcode) {
		this.setField(6, InCustomerewbcode);
	}

	public String getIncustomerewbcode() {
		return this.getField(6);
	}
	public void setInserverewbcode(String InServerewbcode) {
		this.setField(7, InServerewbcode);
	}

	public String getInserverewbcode() {
		return this.getField(7);
	}

	public void setCocodecustomer(String cocodecustomer) {
		this.setField(8, cocodecustomer);
	}

	public String getCocodecustomer() {
		return this.getField(8);
	}

	public void setStartsignoutdate(String startsignoutdate) {
		this.setField(9, startsignoutdate);
	}

	public String getStartsignoutdate() {
		return this.getField(9);
	}

	public void setEndsignoutdate(String Endsignoutdate) {
		this.setField(10, Endsignoutdate);
	}

	public String getEndsignoutdate() {
		return this.getField(10);
	}

	public void setEestructurecode(String eeStructurecode) {
		this.setField(11, eeStructurecode);
	}

	public String getEestructurecode() {
		return this.getField(11);
	}

}
