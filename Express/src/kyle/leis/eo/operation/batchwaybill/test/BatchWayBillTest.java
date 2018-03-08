package kyle.leis.eo.operation.batchwaybill.test;

import kyle.common.connectors.util.Decoder;
import kyle.common.util.jlang.DateUtility;
import kyle.leis.eo.operation.batchwaybill.sv.ArrivalBWService;
import kyle.leis.eo.operation.batchwaybill.sv.DepartureBWService;

public class BatchWayBillTest {
	private static ArrivalBWService s_objArrivalBWService = new ArrivalBWService();
	private static DepartureBWService s_objDepartureBWService = new DepartureBWService();
	
	public static void main(String[] args) {
		try {
			// System.out.println(querySimpleBatchwaybill());
			// System.out.println(getString());
			//System.out.println(save());
			//System.out.println(parseDate());
			querySimpleBatchwaybill();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public static String parseDate() throws Exception {
		String str = "2010-01-11 16:01:36";
		return DateUtility.getMoveDate(str, -3);
	}
	
	public static String queryBatchwaybill() throws Exception {
		String str = "";
		Decoder objPD = new Decoder(str);
		return s_objArrivalBWService.query(objPD);
	}
	
	public static String querySimpleBatchwaybill() throws Exception {
		String str = "~`2011/12/14 00:00:00~`2011/12/17 23:59:59~`~`NW~`~`1~`A~`~`~`~@~#";
		Decoder objPD = new Decoder(str);
		return s_objArrivalBWService.querySimpleBatchwaybill(objPD);
	}
	
	public static String getString() {
		String str = "A";
		char[] aobj = str.toCharArray();
		int i = aobj[0];
		System.out.println((char)(i + 1));
		return "0";
	}
	
	public static String save() throws Exception {
		String str = "~`~`~`~`~`~`2010-01-30 14:02:39~`签入时保存到货总单~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`1~`~`~`A~`~`156~`~`~`~`~@~#1~`~@~#False~`~@~#";
		Decoder objPD = new Decoder(str);
		s_objDepartureBWService.complete(objPD);
		return s_objArrivalBWService.save(objPD);
	}
}
