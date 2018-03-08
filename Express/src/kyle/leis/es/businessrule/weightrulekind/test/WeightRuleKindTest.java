package kyle.leis.es.businessrule.weightrulekind.test;

import kyle.common.connectors.util.Decoder;
import kyle.leis.es.businessrule.weightrulekind.sv.WeightRuleKindService;

public class WeightRuleKindTest {
	private static WeightRuleKindService s_objWRKS = new WeightRuleKindService();
	public static void main(String[] args) {
		try {
			System.out.println(save());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static String save() throws Exception {
		String str = "~`ตุ~`S~`A01~`~`ON~`~`~@~#1~`~@~#";
		Decoder objPD = new Decoder(str);
		return s_objWRKS.save(objPD);
	}
}
