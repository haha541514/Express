package kyle.leis.es.company.customer.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.company.customer.da.CustomerColumns;
import kyle.leis.fs.authoritys.user.da.UserColumns;
import kyle.leis.fs.authoritys.user.tp.SaveUserTransaction;

public class SaveCustomerUserTransaction extends AbstractTransaction {
	private CustomerColumns m_objCustomerColumns;
	private UserColumns m_objUColumns;
	private String m_sign;
	
	public void setParam(CustomerColumns cust,UserColumns user,String sign){
		m_objCustomerColumns=cust;
		m_objUColumns=user;
		m_sign=sign;
	}

	public void transaction(Session objSession) throws Exception {
		SaveCustomerTransaction objSCTrans = new SaveCustomerTransaction();
		if(StringUtility.isNull(m_sign)){
			objSCTrans.setCustomerParam(m_objCustomerColumns, null, "0");
			objSCTrans.transaction(objSession);
		}
		SaveUserTransaction objSUTrans=new SaveUserTransaction();
		if(StringUtility.isNull(m_sign)){
			m_objUColumns.setCococode(objSCTrans.getSavedCocode());
		}else{
			m_objUColumns.setCococode(m_sign);
		}
		objSUTrans.setParam("0", m_objUColumns);
		objSUTrans.transaction(objSession);
	}

}
