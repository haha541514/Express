package kyle.leis.eo.finance.cashrecord.dax;

import kyle.leis.eo.finance.cashrecord.da.CashrecordfordunQuery;

public class ServerCashrecordfordunQueryEX extends CashrecordfordunQuery {
	
	public ServerCashrecordfordunQueryEX(boolean isNeedRelease){
		m_strSelectClause = "SELECT cr.cr_id,cr.co_code,cr.cr_occurdate,cr.cr_remark,decode(cr.crk_code,'RA', 1, 'PA', -1) * round(cr.cr_total, 2) as crTotal FROM t_fi_cashrecord cr, t_co_corporation co";
		if (isNeedRelease) {
			m_strWhereClause = "cr.co_code = co.co_code and cr.crs_code in ('R')";
		}
	}

}
