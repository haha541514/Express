package kyle.leis.es.businessrule.productrule.test;

import kyle.common.connectors.util.Decoder;
import kyle.leis.es.businessrule.productrule.sv.ProductruleService;

public class ProductruleTest {
	private static ProductruleService s_objPService = new ProductruleService();
	
	public static void main(String[] args) {
		try {
			confirm();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
	}
	
	public static String confirm() throws Exception {
		String str = "45~`~@~#1~`~@~#False~`~@~#";
		Decoder objPD = new Decoder(str);
		return s_objPService.confirm(objPD);
	}
}
