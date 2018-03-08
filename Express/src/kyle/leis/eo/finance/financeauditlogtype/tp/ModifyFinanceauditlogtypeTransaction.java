package kyle.leis.eo.finance.financeauditlogtype.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.eo.finance.financeauditlogtype.da.TdifinanceauditlogtypeColumns;
import kyle.leis.eo.finance.financeauditlogtype.dax.financeauditlogtypeDemand;
import kyle.leis.hi.TdiFinanceauditlogtype;

public class ModifyFinanceauditlogtypeTransaction extends AbstractTransaction {
	private TdifinanceauditlogtypeColumns m_TdifinanceauditlogtypeColumns;
	public void setParam(TdifinanceauditlogtypeColumns TdifinanceauditlogtypeColumns){
		this.m_TdifinanceauditlogtypeColumns=TdifinanceauditlogtypeColumns;
	}
	public void transaction(Session objSession) throws Exception {
		TdiFinanceauditlogtype objFal=new TdiFinanceauditlogtype();
		financeauditlogtypeDemand.setfinanceauditlogtypeByColumns(objFal, m_TdifinanceauditlogtypeColumns, objSession);
		objSession.update(objFal);
	}

}
