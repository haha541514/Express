package kyle.leis.es.company.customer.dax;

import java.util.List;

import kyle.leis.es.company.customer.da.CustomerapiwebtokenColumns;
import kyle.leis.es.company.customer.da.CustomerapiwebtokenCondition;
import kyle.leis.es.company.customer.da.CustomerapiwebtokenQuery;
import kyle.leis.es.company.customer.da.CustomerapiwebtypeColumns;
import kyle.leis.es.company.customer.da.CustomerapiwebtypeQuery;

public class CustomerApiWebDemand {
	public static List<CustomerapiwebtokenColumns> query(CustomerapiwebtokenCondition condition) 
			throws Exception {
		CustomerapiwebtokenQuery query = new CustomerapiwebtokenQuery();
		query.setCondition(condition);
		return query.getResults();
	}
	
	public static List<CustomerapiwebtypeColumns> query() throws Exception{
		CustomerapiwebtypeQuery query = new CustomerapiwebtypeQuery();
		return query.getResults();
	}
}
