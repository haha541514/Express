package kyle.leis.eo.billing.calculate.chargeweight.test;

import kyle.common.connectors.util.Decoder;
import kyle.leis.eo.billing.calculate.chargeweight.bl.Chargeweight;
import kyle.leis.eo.billing.calculate.chargeweight.dax.ChargeweightResult;
import kyle.leis.eo.billing.calculate.chargeweight.sv.CalculateCWService;

public class ChargeweightTest {
	private static CalculateCWService s_objCalculateCWService = new CalculateCWService();
	public static void main(String[] args) {
		try {
			System.out.println(getChargeWeight());
			// System.out.println(getServiceChargeweight());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static String getChargeWeight() throws Exception {
		Chargeweight objChargeweight = new Chargeweight();
		String strCwcode = "918735";
		ChargeweightResult objChargeweightResult = objChargeweight.calculate(strCwcode);
		
		return objChargeweightResult.getChargeweight();
	}
	
	public static String getServiceChargeweight() throws Exception {
		String str = "202~`~`24~`D0101~`5~`A01~`2010-01-16 18:28:11~`~@~#~`~`12~`23~`12~`32~`~`~`~@~`~`12~`23~`32~`12~`~`~`~@~#";
		Decoder objPD = new Decoder(str);
		return s_objCalculateCWService.calculate(objPD);
	}
}
