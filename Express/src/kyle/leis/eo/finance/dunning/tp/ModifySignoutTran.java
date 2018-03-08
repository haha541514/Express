package kyle.leis.eo.finance.dunning.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.es.company.customer.dax.CustomerDemand;

public class ModifySignoutTran extends AbstractTransaction{
	private String m_cocode;
	private String m_isAllow;
	public void setParam(String cocode,String isAllow){
		this.m_cocode=cocode;
		this.m_isAllow=isAllow;
	}

	public void transaction(Session objSession) throws Exception {
		// TODO Auto-generated method stub
		if(CustomerDemand.load(m_cocode)!=null){
			String sql="update t_co_customer co set cm_arrearallowsignout='" + m_isAllow + 
			"' where co.co_code = '" + m_cocode + "'";
			execute(objSession, sql);
		}
	}

}
