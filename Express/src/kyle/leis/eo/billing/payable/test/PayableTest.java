package kyle.leis.eo.billing.payable.test;

import kyle.leis.eo.billing.payable.bl.Payable;

public class PayableTest {
	public static void main(String[] args) {
		try {
			delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void delete() throws Exception {
		// É¾³ýÓ¦¸¶·ÑÓÃ
		Payable objPayable = new Payable();
		objPayable.deleteAll("411073", "0");
		
	}
	
}
