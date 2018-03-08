package kyle.leis.es.company.shipperconsignee.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DBStringUtility;
import kyle.common.util.jlang.StringUtility;


public class DeleteShipperconsigneeTrans extends AbstractTransaction{

    private String m_strSCCode;
	
	public void setParam(String strSCCode) {
		m_strSCCode = strSCCode;
	}
	
	public void transaction(Session objSession) throws Exception {

		if(StringUtility.isNull(m_strSCCode))
			return;
		m_strSCCode = DBStringUtility.transferToInCondition(m_strSCCode);
		String strDeleteSql = "delete from t_co_shipperconsigneeinfo  where sc_code in (" + m_strSCCode + ")";
		execute(objSession, strDeleteSql);
	}

}
