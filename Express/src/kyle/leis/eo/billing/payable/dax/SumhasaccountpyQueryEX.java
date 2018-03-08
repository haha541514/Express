package kyle.leis.eo.billing.payable.dax;

import kyle.leis.eo.billing.payable.da.SumhasaccountpyQuery;

public class SumhasaccountpyQueryEX extends SumhasaccountpyQuery {
	public SumhasaccountpyQueryEX() {
		m_strSelectClause = "SELECT nvl(sum(round(py.py_actualtotal,2)),0) as sumPYTotal FROM T_BL_PAYABLE py";
	}
}
