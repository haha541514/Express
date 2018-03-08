package kyle.leis.eo.finance.serverbillrecord.test;

import kyle.leis.eo.finance.serverbillrecord.bl.ServerBillRecord;

public class ServerBillRecordTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// System.out.println(Boolean.parseBoolean("true"));
		
		/*
		ServerBillRecordService service = new ServerBillRecordService();
		String str = "16261~`~@~#1~`~@~#true~`~@~#";
		Decoder objPD = new Decoder(str);
		service.confirm(objPD);
		System.out.println("aaa");
		*/
		try {
			acceptSeverCharge();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	} 
	
	public static void acceptSeverCharge() throws Exception {
		ServerBillRecord objServerBillRecord = new ServerBillRecord();
		objServerBillRecord.acceptSeverCharge("371801", "0");
	}

}
