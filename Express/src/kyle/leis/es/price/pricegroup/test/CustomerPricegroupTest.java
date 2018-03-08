package kyle.leis.es.price.pricegroup.test;

import kyle.common.connectors.util.Decoder;
import kyle.leis.es.price.pricegroup.sv.CustomerPricegroupService;

public class CustomerPricegroupTest {
	private static CustomerPricegroupService s_objCPGService = new CustomerPricegroupService();
	public static void main(String[] args) {
		try {
			System.out.println(save());
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
	}
	
	public static String save() throws Exception {
		String str = "108~`~`2009-12-16 13:46:03~`2010-01-01~`2010-01-10~`asdf~`~`C~`~`~`~`~`~`1~`~`1~`深联运~`~`1~`~`~`~`~`~`~`~`D0102~`~`~`~@~#1~`108~`A01~`1~`价格组A~`A01~`运费~`~@2~`108~`A10~`2~`价格组B~`A10~`杂费~`~@3~`108~`A20~`3~`价格组C~`A20~`折扣~`~@~#1~`~@~#True~`~@~#";
		Decoder objPD = new Decoder(str);
		return s_objCPGService.save(objPD);
	}
}
