package kyle.leis.es.company.customer.bl;

import kyle.leis.es.company.customer.da.CustomerapiwebtokenColumns;
import kyle.leis.es.company.customer.da.CustomerapiwebtokenCondition;
import kyle.leis.es.company.customer.dax.CustomerApiWebDemand;
import kyle.leis.es.company.customer.tp.SaveCustomerApiTokenTrans;
import kyle.leis.hi.TcoCustomerapiwebtoken;

public class CustomerApiWeb {
	public TcoCustomerapiwebtoken save(CustomerapiwebtokenColumns customerapiwebtokenColumns) 
			throws Exception{
		SaveCustomerApiTokenTrans tokenTrans = new SaveCustomerApiTokenTrans();
		tokenTrans.setParam(customerapiwebtokenColumns);
		tokenTrans.execute();
		return tokenTrans.getCustomerapiwebtoken();
	}
	
	public void updateToken(String cawtId, String newToken) throws Exception{
		CustomerapiwebtokenCondition condition = new CustomerapiwebtokenCondition();
		condition.setCawtcawtid(cawtId);
		CustomerapiwebtokenColumns customerapiwebtokenColumns = CustomerApiWebDemand.query(condition).get(0);
		customerapiwebtokenColumns.setCawtcawttoken(newToken);
		save(customerapiwebtokenColumns);
	}
}
