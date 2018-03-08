package kyle.leis.eo.finance.financeauditlogtype.dax;

import kyle.leis.eo.finance.financeauditlogtype.da.TdifinanceauditlogtypeColumns;
import kyle.leis.hi.TdiFinanceauditlogtype;
import net.sf.hibernate.Session;

public class financeauditlogtypeDemand {
	public static void setfinanceauditlogtypeByColumns(TdiFinanceauditlogtype objTdiFinanceauditlogtype,
			TdifinanceauditlogtypeColumns objTdifinanceauditlogtypeColumns,
			Session objSession) throws Exception{
		objTdiFinanceauditlogtype.setFaltCode(objTdifinanceauditlogtypeColumns.getFalfaltcode());
		objTdiFinanceauditlogtype.setFaltContent(objTdifinanceauditlogtypeColumns.getFalfaltcontent());
	}
}
