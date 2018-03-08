package kyle.leis.eo.finance.serverbillrecord.dax;

import kyle.leis.eo.finance.serverbillrecord.da.ChargeweightdifferenceQuery;

public class ChargeweightdifferenceQueryEX extends ChargeweightdifferenceQuery {

	public ChargeweightdifferenceQueryEX(){
	    m_strSelectClause = "SELECT hwb.HW_OWNINPUTCWAUDITSIGN,(select count(1) from t_bl_receivable rv where rv.cw_code = cw.cw_code and fk_code = 'A0101' and rv.fs_code = 'B') as status,(select nvl(sum(round(rv.rv_actualtotal * rv.RV_CURRENCYRATE,2)),0) from t_bl_receivable rv where rv.cw_code = cw.cw_code and rv.fs_code != 'E') as rvtotal,(select nvl(sum(round(py.py_actualtotal * py.PY_CURRENCYRATE,2)),0) from t_bl_payable py where py.cw_code = cw.cw_code and py.fs_code != 'E' and py.bk_code = 'A0201') as pytotal,'' as SWB_CODE, cw.chn_code_supplier,chn.chn_sname, cw.cw_serverewbcode,cw.cw_customerewbcode, cw.cw_pieces,cw.cw_chargeweight,0 as SWB_TotalFreightCharge,0 as SWB_TotalSurcharge, 0 as SWB_TotalIncidentalCharge,cw.cw_transferpieces,cw.cw_serverchargeweight,cw.cw_chargeweight, cw.cw_code,co.co_code,co.co_sname,co.co_labelcode,bw.add_date,pk.pk_code,pk.pk_name FROM T_OP_COREWAYBILL cw,T_OP_BATCHWAYBILL bw,T_CHN_CHANNEL chn,T_CO_CORPORATION co,T_DI_PRODUCTKIND pk,t_co_customer cus,t_op_housewaybill  hwb";
	    m_strWhereClause = "hwb.cw_code = cw.cw_code and cw.CO_CODE_CUSTOMER = cus.co_code and cw.bw_code_arrival = bw.bw_code and cw.chn_code_supplier = chn.chn_code and cw.co_code_customer = co.co_code and cw.pk_code = pk.pk_code and not exists (select swb.swb_code from t_fi_serverwaybill swb where swb.swb_referencecode = cw.cw_code)";
	    m_astrConditionWords = new String[] { "swb.SWB_CODE = ~~", "cw.cw_serverewbcode in (~~)", "bw.add_date >= to_date('~~','yyyy-mm-dd hh24:mi:ss')", "to_date('~~','yyyy-mm-dd hh24:mi:ss') >= bw.add_date", "co.co_code = '~~'", "pk.pk_code = '~~'", "cus.ct_code = '~~'", "nvl(hwb.HW_OWNINPUTCWAUDITSIGN,'N')='~~'", "cw.cw_customerewbcode in (~~)" };
	    m_aiConditionVariableCount = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1 };		
	}	
	
}
