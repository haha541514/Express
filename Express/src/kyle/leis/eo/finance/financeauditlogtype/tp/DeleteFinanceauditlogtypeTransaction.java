package kyle.leis.eo.finance.financeauditlogtype.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.eo.finance.financeauditlogtype.da.TdifinanceauditlogtypeColumns;
import kyle.leis.eo.finance.financeauditlogtype.dax.financeauditlogtypeDemand;
import kyle.leis.hi.TdiFinanceauditlogtype;

public class DeleteFinanceauditlogtypeTransaction extends AbstractTransaction  {
	private TdifinanceauditlogtypeColumns m_TdifinanceauditlogtypeColumns;
	public void setParam(TdifinanceauditlogtypeColumns TdifinanceauditlogtypeColumns){
	  this.m_TdifinanceauditlogtypeColumns=TdifinanceauditlogtypeColumns;
	}
	private String m_code;
	
	public String getM_code() {
		return m_code;
	}

	public void setM_code(String mCode) {
		m_code = mCode;
	}

	public void transaction(Session objSession) throws Exception {
		TdiFinanceauditlogtype objFal=new TdiFinanceauditlogtype();
		String deletesql1="delete from T_FS_CoreWayBillAuditLog cwbal where cwbal.falt_code='"+m_code+"'";
		execute(objSession, deletesql1);
		String deletesql="delete from T_DI_FINANCEAUDITLOGTYPE fal where fal.falt_code='"+m_code+"'";
		execute(objSession, deletesql);
	}
}
