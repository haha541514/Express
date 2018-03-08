package kyle.leis.ds.report.finance.dax;

import kyle.leis.ds.report.finance.da.FeegroupbyonylcwQuery;

public class FeegroupbyonylcwQueryEX extends FeegroupbyonylcwQuery {
	
	public FeegroupbyonylcwQueryEX(String strStartCreateDate,
			String strEndCreateDate){
	    m_strSelectClause = "SELECT cw.co_code_supplier,count(1) as billcount,nvl(sum(cw.cw_pieces),0) as sumpieces,nvl(sum(cw.cw_chargeweight),0) as sumchargeweight,nvl(sum(cw.cw_serverchargeweight),0) as sumserverchargeweight,nvl(sum((select sum(round(rv.rv_currencyrate * rv.rv_actualtotal,2)) from t_bl_receivable rv where rv.cw_code = cw.cw_code and rv.rv_createdate >= to_date('" + strStartCreateDate + "', 'yyyy-mm-dd hh24:mi:ss') and to_date('" + strEndCreateDate + "', 'yyyy-mm-dd hh24:mi:ss') >= rv.rv_createdate and rv.fs_code in ('D','C','B','W'))),0) as rvTotal, nvl(sum((select sum(round(py.py_currencyrate * py.py_actualtotal,2)) from t_bl_payable py where py.cw_code = cw.cw_code and py.py_createdate >= to_date('" + strStartCreateDate + "', 'yyyy-mm-dd hh24:mi:ss') and to_date('" + strEndCreateDate + "', 'yyyy-mm-dd hh24:mi:ss') >= py.py_createdate and py.bk_code = 'A0201' and py.fs_code in ('D','C','B','W'))),0) as pyTotal,co.co_sname,co.co_labelcode,nvl(sum((select sum(py.py_actualtotal) from t_bl_payable py where py.cw_code = cw.cw_code and py.bk_code = 'A0201' and py.ck_code = 'RMB' and py.fs_code in ('D','C','B','W'))),0) as RMBPyTotal,nvl(sum((select sum(py.py_actualtotal) from t_bl_payable py where py.cw_code = cw.cw_code and py.bk_code = 'A0201' and py.ck_code = 'HKD' and py.fs_code in ('D','C','B','W'))),0) as HKDPyTotal ,ee.ee_sname,nvl(sum((select sum(py.py_actualtotal) from t_bl_payable py where py.cw_code = cw.cw_code and py.bk_code = 'A0201' and py.ck_code = 'USD' and py.fs_code in ('D','C','B','W'))),0) as USDPyTotal,nvl(sum((select sum(py.py_actualtotal) from t_bl_payable py where py.cw_code = cw.cw_code and py.bk_code = 'A0201' and py.ck_code = 'EUR' and py.fs_code in ('D','C','B','W'))),0) as EURPyTotal FROM t_op_corewaybill cw,t_op_batchwaybill bw,t_co_corporation co,t_di_productkind pk, t_co_customer cm,t_co_supplier cs,t_chn_channel chn, T_DI_ENTERPRISEELEMENT ee ";	
	}
	
}
