package kyle.leis.es.company.customer.bl;

import kyle.leis.es.company.customer.da.CustomerColumns;
import kyle.leis.es.company.customer.tp.SaveCustomerUserTransaction;
import kyle.leis.fs.authoritys.user.da.UserColumns;

public class CustomerRegsiter {
	public void save(CustomerColumns objCustomerColumns,
			UserColumns objUserColumns,String sign) throws Exception {
		SaveCustomerUserTransaction objRTran=new SaveCustomerUserTransaction();
		objRTran.setParam(objCustomerColumns, objUserColumns,sign);
		objRTran.execute();
	}
}
