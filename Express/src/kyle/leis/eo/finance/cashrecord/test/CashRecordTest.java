package kyle.leis.eo.finance.cashrecord.test;

import kyle.common.connectors.util.Decoder;
import kyle.leis.eo.finance.cashrecord.sv.CashRecordService;

public class CashRecordTest {
	private static CashRecordService s_objCRService = new CashRecordService();
	
	public static void main(String[] args) {
		try {
			// System.out.println(save());
			// System.out.println(confirm());
			//System.out.println(saveDirectCustomerCash());
			query();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
	}
	
	public static String confirm() throws Exception {
		String str = "5~`~@~#1~`~@~#";
		Decoder objPD = new Decoder(str);
		return s_objCRService.confirm(objPD);		
	}
	
	public static String save() throws Exception {
		String str = "~`1~`2~`~`2009-12-26 17:15:38~`~`~`~`~`~`12~`CS~`~`N~`~`RMB~`~`~`~`~`~`~`~`1~`~`~`1~`1~`~`~`~`RA~`~`~@~#1~`~@~#";
		Decoder objPD = new Decoder(str);
		return s_objCRService.save(objPD);
	}
	
	public static String query() throws Exception {
		String str = "~`~`2009-10-19 00:00:00~`2011-10-22 23:59:59~`~`E~`~`~`~`~`1~`~`RA~`~`~`~`~`~@~#";
		Decoder objPD = new Decoder(str);
		return s_objCRService.query(objPD);
	}
	
	public static String saveDirectCustomerCash() throws Exception {
		String str = "45~`1~`1212~`1111~`2010-01-16 19:46:17~`~`~`~`~`0~`1212~`CS~`~`N~`~`RMB~`~`~`≤‚ ‘’À∫≈~`~`~`~`≤‚ ‘’À∫≈~`1~`~`~`SLYSZX~`1~`~`~`~`RA~`~`~@~#1~`~@~#225~`~@~#";
		Decoder objPD = new Decoder(str);
		return s_objCRService.saveDirectCustomerCash(objPD);		
	}
}
