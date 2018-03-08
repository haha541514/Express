package kyle.leis.es.businessrule.weightrule.test;

import kyle.common.connectors.util.Decoder;
import kyle.leis.es.businessrule.weightrule.sv.WeightRuleService;

public class WeightRuleTest {
	private static WeightRuleService s_objWeightRuleService = new WeightRuleService();
	
	public static void main(String[] args) {
		try {
			System.out.println(save());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	public static String save() throws Exception {
		String str = "~`122332~`1223~`2007-01-11 00:00:00~`2100-01-01 00:00:00~`~`~`~`1~`~`2010-01-11 11:57:52~`qwe~`~`~`NW~`~`~`~`KG~`~`~@~#~`~`47~`1~`GW >= VW~`GW~`~@~#~`~`47~`1~`5000~`~@~#~`~`999999~`0.5~`~@~#1~`~@~#False~`~@~#";
		Decoder objPD = new Decoder(str);
		return s_objWeightRuleService.save(objPD);
	}
	
}
