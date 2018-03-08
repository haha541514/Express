package kyle.leis.es.company.customer.test;

import java.util.List;

import kyle.leis.es.company.channel.da.ChannelCondition;
import kyle.leis.es.company.channel.dax.ChannelDemand;
import kyle.leis.es.company.customer.da.CustomerforbillCondition;
import kyle.leis.es.company.customer.dax.CustomerDemand;

public class CustomerTest {
	public static void main(String[] args) {
		try {
			// queryForBill();
			// testReplace();
			
			//System.out.println(CustomerDemand.buildCustomerStructure("2940"));
			
			//System.out.println(CustomerDemand.getTopParentCustomer("1"));
			
			ChannelCondition c=new ChannelCondition();
			c.setCstcode("S");
			c.setCscode("AP");
			List list= ChannelDemand.query(c);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
	}
	
	public static void queryForBill() throws Exception {
		CustomerforbillCondition objCFBCondition = new CustomerforbillCondition();
		objCFBCondition.setStartdate("2010-01-01");
		objCFBCondition.setEnddate("2010-05-01");
		CustomerDemand.queryForBill(objCFBCondition);
	}
	
	public static void testReplace() throws Exception {
		String str = "123~~456~~";
		str = str.replaceFirst("~~", "@");
		System.out.println(str);
	}
}
