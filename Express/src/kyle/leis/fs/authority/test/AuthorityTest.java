package kyle.leis.fs.authority.test;

import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.fs.authority.sv.AuthorityService;

public class AuthorityTest {
	private static AuthorityService s_objAuthority = new AuthorityService();
	
	public static void main(String[] args) {
		try {
			System.out.println(testEqual());
			queryUserMenus();
			
		} catch(Exception objEx) {
			objEx.printStackTrace();
			System.out.println(objEx.getMessage());
		}
	}
	
	public static String testEqual() throws Exception {
		String str = null;
		/*
		if (str.equals("Y")) {
			return "true";
		} else {
			return "false";
		}		
		*/
		
		if ("Y".equals(str)) {
			return "true";
		} else {
			return "false";
		}
	}
	
	public static String queryUserMenus() throws Exception {
		String strOperCode = "XS001";
		String strIsk_code = "LEDIS";
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(strOperCode);
		objEncode.addParameter(strIsk_code);
		String str = objEncode.toString();
		
		Decoder objPD = new Decoder(str);
		return s_objAuthority.queryUserMenus(objPD);
	}
}
