package kyle.leis.fs.dictionary.operator.test;

import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.common.util.jlang.IntervalTime;
import kyle.leis.fs.dictionary.operator.sv.OperatorDelegate;
import kyle.leis.fs.dictionary.operator.sv.OperatorService;
import kyle.leis.fs.dictionary.operator.tp.ModifyPasswordTrans;

public class OperatorTest {
	private static OperatorService s_objOS = new OperatorService();
	
	public static void main(String[] args) {
		try {
			//System.out.println(login());
			//modifyPassword();
			//System.out.println(test());
			//remoteLogin();
			insertHawb();
		} catch(Exception objEx) {
			System.out.println(objEx.getMessage());
		}
	}
	
	public static void insertHawb() throws Exception {
		String strHawbAndCountryAndPostcode = "RS000647072CN,RS,0002300,测试";
		OperatorDelegate delegate = new OperatorDelegate();
		delegate.insertHawb(strHawbAndCountryAndPostcode);
	}	
	
	public static String test() throws Exception {
		String str = "DN46AG";
		return str.substring(1, 1);
	}
	
	public static void remoteLogin() throws Exception {
		
		for (int i = 0; i < 10; i++) {
		new Thread(new Runnable() {
			public void run() {
				try {
					IntervalTime iv = new IntervalTime("----��ʼ---");
					OperatorDelegate delegate = new OperatorDelegate();
					String strResult = delegate.login("XS001", 
							"BQCAAqaz001", 
							"LEDIS", 
							"48A6367D", 
							"00:26:B9:09:01:BA", 
							"192.168.1.132");
					System.out.println(iv.toString());
					//System.out.println(strResult);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		).start();
		}
		
	}
	
	
	public static String login() throws Exception {
		/*
		String strOperCode = "XS001";
		String strOperPassword = "kyle_bing";
		String strIsk_code = "LEDIS";
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(strOperCode);
		objEncode.addParameter(strOperPassword);
		objEncode.addParameter(strIsk_code);
		String str = objEncode.toString();
		*/
		String str = "XS001~`~@~#QAZ098BQaa~`~@~#LEDIS~`~@~#48A6367D~`~@~#0C:EE:E6:8E:A2:C7~`~@~#192.168.1.233~`~@~#";
		Decoder objPD = new Decoder(str);
		return s_objOS.login(objPD);
	}
	
	public static String modifyPassword() throws Exception {
		ModifyPasswordTrans objMPTrans = new ModifyPasswordTrans();
		objMPTrans.setParam("1", "qazwsx");
		objMPTrans.execute();
		return "";
	}
}
