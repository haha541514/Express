package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.IColumns;
import kyle.common.dbaccess.query.JGeneralQuery;

public class WaybillforpredictQuery extends JGeneralQuery {
	
	public WaybillforpredictQuery(){

	    m_strSelectClause = "SELECT cw.cw_code,cw.cw_customerewbcode,cw.cw_serverewbcode,cw.cw_ewbcode,pk.pk_code,pk.pk_sename,cw.dt_code_signin,dt.dt_hubcode,dt.dt_ename,hw.hw_shippercompany,hw.hw_shippername,hw.hw_shipperaddress1,hw.hw_shipperaddress2,hw.hw_shipperaddress3,hw.hw_shippertelephone,hw.hw_shipperpostcode,hw.hw_shipperfax,hw.hw_consigneename,hw.hw_consigneecompany,dt.dt_statecode,hw.hw_consigneecity,hw.hw_consigneeaddress1,hw.hw_consigneeaddress2,hw.hw_consigneeaddress3,hw.hw_consigneetelephone,hw.hw_consigneepostcode,hw.hw_consigneefax,hw.hw_buyerid,hw.hw_transactionid,hw.hw_remark,cw.cw_chargeweight,cw.cw_customerchargeweight,pw.pwb_createdate,cw.cw_pieces,cws.cws_code,cws.cws_name,ihs.ihs_code,ihs.ihs_name,cco.co_code,cco.co_labelcode,sco.co_code as scocode,sco.co_labelcode as scolabelcode,sco.co_sename as scosename,chn.chn_code,chn.chn_sename,chn.chn_sname,chn.chn_customerlabel,                                                                    FUN_GET_CUSTOMEREWBCODES(cw.cw_code) as originorderid,FUN_GET_SPECIALTYPESIGN(cw.cw_code,'A0101') as Alonecustomsign,hw.HW_ATTACHEINFOSIGN, chn.CHN_CustomLable,pw.pwb_PrintDate,HW_SignInDate,pw.pwb_DeclareDate,dt.dt_name,pk.pk_showserverewbcode,hw.HW_ConsigneeAddressEX,hw.HW_ConsigneeNameEX,bwb.bw_labelcode,chn.chn_masteraccount ,chn.chn_selflablecode, '' as subconame, ddt.dt_code as cdtcode,ddt.dt_hubcode as cdthubcode,hw.hw_consigneecityex,hw.pat_code,hw.dt_code,hw.cgk_code,hw.bk_code,hw.hw_dutypaidsign,hw.hw_dcustomssign,'' as itipt_code,'' as ifyib_code FROM T_OP_COREWAYBILL cw,t_OP_PREDICTWAYBILL pw,T_OP_HOUSEWAYBILL hw,T_DI_PRODUCTKIND pk,T_DI_DISTRICT dt,T_DI_COREWAYBILLSTATUS cws,t_co_corporation cco, t_co_corporation sco,t_chn_channel chn, t_di_issueholdstatus ihs,t_op_batchwaybill bwb, T_DI_DISTRICT ddt";
	    m_strWhereClause = "cw.bw_code_arrival = bwb.bw_code and cw.cw_code = hw.cw_code and cw.cw_code = pw.cw_code(+) and cw.pk_code = pk.pk_code and cw.co_code_customer = cco.co_code  and cw.cws_code = cws.cws_code and cw.co_code_supplier = sco.co_code(+) and cw.chn_code_supplier = chn.chn_code(+) and cw.dt_code_signin = dt.dt_code(+) and cw.dt_code_destination = ddt.dt_code(+) and cw.ihs_code = ihs.ihs_code(+)";
	
		
	   // m_strSelectClause = "SELECT cw.cw_code,cw.cw_customerewbcode,cw.cw_serverewbcode,cw.cw_ewbcode,pk.pk_code,pk.pk_sename,cw.dt_code_signin,dt.dt_hubcode,dt.dt_ename,hw.hw_shippercompany,hw.hw_shippername,hw.hw_shipperaddress1,hw.hw_shipperaddress2,hw.hw_shipperaddress3,hw.hw_shippertelephone,hw.hw_shipperpostcode,hw.hw_shipperfax,hw.hw_consigneename,hw.hw_consigneecompany,dt.dt_statecode,hw.hw_consigneecity,hw.hw_consigneeaddress1,hw.hw_consigneeaddress2,hw.hw_consigneeaddress3,hw.hw_consigneetelephone,hw.hw_consigneepostcode,hw.hw_consigneefax,hw.hw_buyerid,hw.hw_transactionid,hw.hw_remark,cw.cw_chargeweight,cw.cw_customerchargeweight,pw.pwb_createdate,cw.cw_pieces,cws.cws_code,cws.cws_name,ihs.ihs_code,ihs.ihs_name,cco.co_code,cco.co_labelcode,sco.co_code as scocode,sco.co_labelcode as scolabelcode,sco.co_sename as scosename,chn.chn_code,chn.chn_sename,chn.chn_sname,chn.chn_customerlabel,                                                                    FUN_GET_CUSTOMEREWBCODES(cw.cw_code) as originorderid,FUN_GET_SPECIALTYPESIGN(cw.cw_code,'A0101') as Alonecustomsign,hw.HW_ATTACHEINFOSIGN, chn.CHN_CustomLable,pw.pwb_PrintDate,HW_SignInDate,pw.pwb_DeclareDate,dt.dt_name,pk.pk_showserverewbcode,hw.HW_ConsigneeAddressEX,hw.HW_ConsigneeNameEX,bwb.bw_labelcode,chn.chn_masteraccount ,chn.chn_selflablecode, '' as subconame, ddt.dt_code as cdtcode,ddt.dt_hubcode as cdthubcode,hw.hw_consigneecityex,hw.pat_code,hw.dt_code,hw.cgk_code,hw.bk_code,hw.hw_dutypaidsign,hw.hw_dcustomssign,it.ipt_code,ify.ib_code FROM T_OP_COREWAYBILL cw,t_OP_PREDICTWAYBILL pw,T_OP_HOUSEWAYBILL hw,T_DI_PRODUCTKIND pk,T_DI_DISTRICT dt,T_DI_COREWAYBILLSTATUS cws,t_co_corporation cco, t_co_corporation sco,t_chn_channel chn, t_di_issueholdstatus ihs,t_op_batchwaybill bwb, T_DI_DISTRICT ddt,T_di_Invoiceprinttype it,T_di_Insurancebeneficiary ify";
	   // m_strWhereClause = "cw.bw_code_arrival = bwb.bw_code and cw.cw_code = hw.cw_code and cw.cw_code = pw.cw_code(+) and cw.pk_code = pk.pk_code and cw.co_code_customer = cco.co_code  and cw.cws_code = cws.cws_code and cw.co_code_supplier = sco.co_code(+) and cw.chn_code_supplier = chn.chn_code(+) and cw.dt_code_signin = dt.dt_code(+) and cw.dt_code_destination = ddt.dt_code(+) and cw.ihs_code = ihs.ihs_code(+)";
	    m_strOrderByClause = "cw.cw_customerewbcode, cw.cw_createdate desc";
	    m_strGroupByClause = "";
	    m_astrConditionWords = new String[] { "bwb.bw_labelcode ='~~'", "cw.cws_code != '~~'", "cw.cws_code in (~~)", "lower(hw.hw_consigneename) like lower('%~~%')", "lower(hw.hw_consigneecompany) like lower('%~~%')", "lower(hw.hw_consigneename) = lower('~~')", "lower(hw.hw_consigneecompany) = lower('~~')", "lower(hw.hw_buyerid) = lower('~~')", "lower(hw.hw_transactionid) = lower('~~')", "lower(hw.hw_consigneeaddress1||hw.hw_consigneeaddress2||hw.hw_consigneeaddress3) like lower('%~~%')", "pk.pk_code = '~~'", "(cw.cw_customerewbcode = '~~')", "cw.cw_ewbcode = '~~'", "cw.cw_serverewbcode = '~~'", "cw.co_code_customer = '~~'", "cw.dt_code_signin = '~~'", "cw.cws_code = '~~'", "cw.cw_code = ~~", "cw.cw_code in (~~)", "cw.cw_createdate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= cw.cw_createdate", "exists (select cwc.cwbc_id from t_op_corewaybillcode cwc where cwc.cw_code = cw.cw_code and cwc.cwbc_customerewbcode = '~~')", "hw.HW_CustomerLabelPrintDate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= hw.HW_CustomerLabelPrintDate", "HW_SignInDate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= HW_SignInDate", "hw.HW_CustomerDeclareDate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= hw.HW_CustomerDeclareDate", "exists(select cw.cw_code from t_op_hwbcargoinfo hci where hci.cw_code = cw.cw_code and lower(hci.ci_ename) like lower('~~'))", "exists(select cw.cw_code from t_op_hwbcargoinfo hci where hci.cw_code = cw.cw_code and lower(hci.ci_attacheinfo) like lower('~~'))", "nvl(hw.HW_ATTACHEINFOSIGN,'C') = '~~'", "cco.co_labelcode = '~~'", "FUN_GET_CUSTOMERSTRUCTURECODE(cw.co_code_customer) like FUN_GET_CUSTOMERSTRUCTURECODE('~~')||'%'" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	@Override
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new WaybillforpredictColumns();
	}
	
	public void setBwblabelcode(String bwblabelcode) {
		this.setField(0, bwblabelcode);
	}

	public String getBwblabelcode() {
		return this.getField(0);
	}

	public void setNotcwscode(String notcwscode) {
		this.setField(1, notcwscode);
	}

	public String getNotcwscode() {
		return this.getField(1);
	}

	public void setIncwscode(String incwscode) {
		this.setField(2, incwscode);
	}

	public String getIncwscode() {
		return this.getField(2);
	}

	public void setLikehwconsigneename(String likehwconsigneename) {
		this.setField(3, likehwconsigneename);
	}

	public String getLikehwconsigneename() {
		return this.getField(3);
	}

	public void setLikehwconsigneecompany(String likehwconsigneecompany) {
		this.setField(4, likehwconsigneecompany);
	}

	public String getLikehwconsigneecompany() {
		return this.getField(4);
	}

	public void setHwconsigneename(String hwconsigneename) {
		this.setField(5, hwconsigneename);
	}

	public String getHwconsigneename() {
		return this.getField(5);
	}

	public void setHwconsigneecompany(String hwconsigneecompany) {
		this.setField(6, hwconsigneecompany);
	}

	public String getHwconsigneecompany() {
		return this.getField(6);
	}

	public void setHwbuyerid(String hwbuyerid) {
		this.setField(7, hwbuyerid);
	}

	public String getHwbuyerid() {
		return this.getField(7);
	}

	public void setHwtransactionid(String hwtransactionid) {
		this.setField(8, hwtransactionid);
	}

	public String getHwtransactionid() {
		return this.getField(8);
	}

	public void setLikehwconsigneeaddress(String likehwconsigneeaddress) {
		this.setField(9, likehwconsigneeaddress);
	}

	public String getLikehwconsigneeaddress() {
		return this.getField(9);
	}

	public void setPkpkcode(String pkpkcode) {
		this.setField(10, pkpkcode);
	}

	public String getPkpkcode() {
		return this.getField(10);
	}

	public void setCwcustomerewbcode(String cwcustomerewbcode) {
		this.setField(11, cwcustomerewbcode);
	}

	public String getCwcustomerewbcode() {
		return this.getField(11);
	}

	public void setCwewbcode(String cwewbcode) {
		this.setField(12, cwewbcode);
	}

	public String getCwewbcode() {
		return this.getField(12);
	}

	public void setCwserverewbcode(String cwserverewbcode) {
		this.setField(13, cwserverewbcode);
	}

	public String getCwserverewbcode() {
		return this.getField(13);
	}

	public void setCocodecustomer(String cocodecustomer) {
		this.setField(14, cocodecustomer);
	}

	public String getCocodecustomer() {
		return this.getField(14);
	}

	public void setDtcodesignin(String dtcodesignin) {
		this.setField(15, dtcodesignin);
	}

	public String getDtcodesignin() {
		return this.getField(15);
	}

	public void setCwcwscode(String cwcwscode) {
		this.setField(16, cwcwscode);
	}

	public String getCwcwscode() {
		return this.getField(16);
	}

	public void setCwcode(String cwcode) {
		this.setField(17, cwcode);
	}

	public String getCwcode() {
		return this.getField(17);
	}

	public void setIncwcode(String incwcode) {
		this.setField(18, incwcode);
	}

	public String getIncwcode() {
		return this.getField(18);
	}

	public void setStartcreatedate(String startcreatedate) {
		this.setField(19, startcreatedate);
	}

	public String getStartcreatedate() {
		return this.getField(19);
	}

	public void setEndcreatedate(String endcreatedate) {
		this.setField(20, endcreatedate);
	}

	public String getEndcreatedate() {
		return this.getField(20);
	}

	public void setExistsorderid(String existsorderid) {
		this.setField(21, existsorderid);
	}

	public String getExistsorderid() {
		return this.getField(21);
	}

	public void setStartlbprintdate(String startlbprintdate) {
		this.setField(22, startlbprintdate);
	}

	public String getStartlbprintdate() {
		return this.getField(22);
	}

	public void setEndlbprintdate(String endlbprintdate) {
		this.setField(23, endlbprintdate);
	}

	public String getEndlbprintdate() {
		return this.getField(23);
	}

	public void setStartsignindate(String startsignindate) {
		this.setField(24, startsignindate);
	}

	public String getStartsignindate() {
		return this.getField(24);
	}

	public void setEndsignindate(String endsignindate) {
		this.setField(25, endsignindate);
	}

	public String getEndsignindate() {
		return this.getField(25);
	}

	public void setStartdeclaredate(String startDeclaredate) {
		this.setField(26, startDeclaredate);
	}

	public String getStartdeclaredate() {
		return this.getField(26);
	}

	public void setEnddeclaredate(String endDeclaredate) {
		this.setField(27, endDeclaredate);
	}

	public String getEnddeclaredate() {
		return this.getField(27);
	}

	public void setExistscwcode(String existscwcode) {
		this.setField(28, existscwcode);
	}

	public String getExistscwcode() {
		return this.getField(28);
	}

	public void setCiciattacheinfo(String ciciattacheinfo) {
		this.setField(29, ciciattacheinfo);
	}

	public String getCiciattacheinfo() {
		return this.getField(29);
	}

	public void setHwattacheinfosign(String hwattacheinfosign) {
		this.setField(30, hwattacheinfosign);
	}

	public String getHwattacheinfosign() {
		return this.getField(30);
	}

	public void setColabelcode(String colabelcode) {
		this.setField(31, colabelcode);
	}

	public String getColabelcode() {
		return this.getField(31);
	}

	public void setLikecocode(String likecocode) {
		this.setField(32, likecocode);
	}

	public String getLikecocode() {
		return this.getField(32);
	}

}
