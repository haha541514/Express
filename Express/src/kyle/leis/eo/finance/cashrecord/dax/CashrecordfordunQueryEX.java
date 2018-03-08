package kyle.leis.eo.finance.cashrecord.dax;

import kyle.leis.eo.finance.cashrecord.da.CashrecordfordunQuery;

public class CashrecordfordunQueryEX extends CashrecordfordunQuery {
	
	public CashrecordfordunQueryEX(){
	    m_strSelectClause = "SELECT cr.cr_id,cr.co_code,cr.cr_occurdate,cr.cr_remark,decode(cr.crk_code,'RA', 1, 'PA', -1) * round(cr.cr_total, 2) as crTotal FROM t_fi_cashrecord cr, t_co_corporation co";
	}
}
