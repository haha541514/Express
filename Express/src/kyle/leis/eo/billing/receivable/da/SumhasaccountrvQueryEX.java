package kyle.leis.eo.billing.receivable.da;

public class SumhasaccountrvQueryEX extends SumhasaccountrvQuery {
	public SumhasaccountrvQueryEX() {
		m_strSelectClause = "SELECT nvl(sum(round(rv.rv_actualtotal,2)),0) as sumRvTotal FROM t_bl_receivable rv";
	}

}
