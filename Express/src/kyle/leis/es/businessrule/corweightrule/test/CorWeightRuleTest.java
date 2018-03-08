package kyle.leis.es.businessrule.corweightrule.test;

import kyle.common.connectors.util.Decoder;
import kyle.leis.es.businessrule.corweightrule.sv.CorWeightRuleService;

public class CorWeightRuleTest {
	private static CorWeightRuleService s_objCorWeightRuleService = new CorWeightRuleService();
	
	public static void main(String[] args) {
		try {
			System.out.println(save());
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
	}
	
	public static String save() throws Exception {
		String str = "~`qew~`~`2010-01-12 00:00:00~`2100-01-01 00:00:00~`1~`~`2010-01-11 14:45:22~`1~`~`2010-01-11 14:45:22~`qwrwq~`A02~`~`NW~`~`7~`~`A01~`~`~`~`~`~`1~`~`~`~`~`~@~#1~`~@~#False~`~@~#";
		Decoder objPD = new Decoder(str);
		return s_objCorWeightRuleService.save(objPD);
	}
}
