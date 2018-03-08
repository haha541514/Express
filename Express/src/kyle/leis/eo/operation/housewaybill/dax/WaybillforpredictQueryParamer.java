package kyle.leis.eo.operation.housewaybill.dax;

import kyle.leis.eo.operation.housewaybill.da.WaybillforpredictQuery;

public class WaybillforpredictQueryParamer extends WaybillforpredictQuery {

	public WaybillforpredictQueryParamer(String field,String sort){
	    // m_strSelectClause = "SELECT cw.cw_code,cw.cw_customerewbcode,cw.cw_serverewbcode,cw.cw_ewbcode,pk.pk_code,pk.pk_sename,cw.dt_code_signin,dt.dt_hubcode,dt.dt_ename,hw.hw_shippercompany,hw.hw_shippername,hw.hw_shipperaddress1,hw.hw_shipperaddress2,hw.hw_shipperaddress3,hw.hw_shippertelephone,hw.hw_shipperpostcode,hw.hw_shipperfax,hw.hw_consigneename,hw.hw_consigneecompany,dt.dt_statecode,hw.hw_consigneecity,hw.hw_consigneeaddress1,hw.hw_consigneeaddress2,hw.hw_consigneeaddress3,hw.hw_consigneetelephone,hw.hw_consigneepostcode,hw.hw_consigneefax,hw.hw_buyerid,hw.hw_transactionid,hw.hw_remark,cw.cw_chargeweight,cw.cw_customerchargeweight,cw.cw_createdate,cw.cw_pieces,cws.cws_code,cws.cws_name,ihs.ihs_code,ihs.ihs_name,cco.co_code,cco.co_labelcode,sco.co_code as scocode,sco.co_labelcode as scolabelcode,sco.co_sename as scosename,chn.chn_code,chn.chn_sename,chn.chn_sname,chn.chn_customerlabel, FUN_GET_CUSTOMEREWBCODES(cw.cw_code) as originorderid, FUN_GET_SPECIALTYPESIGN(cw.cw_code, 'A0101') as AlonecustomSign,hw.HW_ATTACHEINFOSIGN, chn.CHN_CustomLable,hw.HW_CustomerLabelPrintDate,HW_SignInDate,hw.HW_CustomerDeclareDate,dt.dt_name,pk.pk_showserverewbcode FROM T_OP_COREWAYBILL cw,T_OP_HOUSEWAYBILL hw,T_DI_PRODUCTKIND pk,T_DI_DISTRICT dt,T_DI_COREWAYBILLSTATUS cws,t_co_corporation cco, t_co_corporation sco,t_chn_channel chn, t_di_issueholdstatus ihs";
	    // m_strWhereClause = "cw.cw_op_id_creator = "+opid ;
	    // m_strOrderByClause = ""+field +"  "+ sort;
		m_strOrderByClause = "" + field;
	    // m_strGroupByClause = "";
	    // m_astrConditionWords = new String[] { "cw.cws_code != '~~'", "cw.cws_code in (~~)", "lower(hw.hw_consigneename) like lower('%~~%')", "lower(hw.hw_consigneecompany) like lower('%~~%')", "lower(hw.hw_consigneename) = lower('~~')", "lower(hw.hw_consigneecompany) = lower('~~')", "lower(hw.hw_buyerid) = lower('~~')", "lower(hw.hw_transactionid) = lower('~~')", "pk.pk_code = '~~'", "(cw.cw_customerewbcode = '~~')", "cw.cw_ewbcode = '~~'", "cw.cw_serverewbcode = '~~'", "cw.co_code_customer = '~~'", "cw.dt_code_signin = '~~'", "cw.cws_code = '~~'", "cw.cw_code = ~~", "cw.cw_code in (~~)", "cw.cw_createdate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= cw.cw_createdate", "exists (select cwc.cwbc_id from t_op_corewaybillcode cwc where cwc.cw_code = cw.cw_code and cwc.cwbc_customerewbcode = '~~')", "hw.HW_CustomerLabelPrintDate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= hw.HW_CustomerLabelPrintDate", "HW_SignInDate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= HW_SignInDate", "hw.HW_CustomerDeclareDate >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= hw.HW_CustomerDeclareDate", "nvl(hw.HW_ATTACHEINFOSIGN,'C') = '~~'" };
	    // m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}
	
	public WaybillforpredictQueryParamer(String field,String sort,long opid){
	     m_strWhereClause += " and cw.cw_op_id_creator = " + opid;
	     m_strOrderByClause = ""+field +"  "+ sort;
	}
	
	
}
