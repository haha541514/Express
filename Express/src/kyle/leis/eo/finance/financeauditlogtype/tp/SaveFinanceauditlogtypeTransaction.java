package kyle.leis.eo.finance.financeauditlogtype.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.eo.finance.financeauditlogtype.da.TdifinanceauditlogtypeColumns;
import kyle.leis.eo.finance.financeauditlogtype.dax.financeauditlogtypeDemand;
import kyle.leis.hi.TdiFinanceauditlogtype;

public class SaveFinanceauditlogtypeTransaction extends AbstractTransaction {
    private TdifinanceauditlogtypeColumns m_TdifinanceauditlogtypeColumns;
	public void setParam(TdifinanceauditlogtypeColumns TdifinanceauditlogtypeColumns){
		this.m_TdifinanceauditlogtypeColumns=TdifinanceauditlogtypeColumns;
	}
	public void transaction(Session objSession) throws Exception {
		if(m_TdifinanceauditlogtypeColumns == null)
			return;
		TdiFinanceauditlogtype objFal=new TdiFinanceauditlogtype();
		financeauditlogtypeDemand.setfinanceauditlogtypeByColumns(objFal, m_TdifinanceauditlogtypeColumns, objSession);
		objSession.save(objFal);
	}
}
