package kyle.leis.eo.operation.housewaybill.svx;

import java.io.File;

import kyle.common.explorer.HttpExplorer;
import kyle.common.util.jlang.FileUtility;

public class PredictOrderServletTest {
	
	public static void main(String[] args) {
		try {
			System.out.println(execute());
			//testCharset();
			//System.out.println(buildParam());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	public static String execute() throws Exception {
		// ����url��ַ
		
		StringBuffer sbUrl = new StringBuffer();
		// sbUrl.append("http://www.klex.cn");
		// sbUrl.append("/PredictOrderServlet.xsv");
		
		//sbUrl.append("http://127.0.0.1:9008");
		//sbUrl.append("/Express/PredictOrderServlet.xsv");
		sbUrl.append("http://www.1001000.com/predictOrder.xsv");
		
		StringBuffer sbParameter = new StringBuffer();
		sbParameter.append("username=1043122_API");
		sbParameter.append("&password=36a2yt33");
		//File objFile = new File("d:/Request.xml");
		
		File objFile = new File("E:\\Kyle\\MyESProject\\Java\\kyle-express-1.1.0-src\\src\\kyle\\leis\\eo\\operation\\housewaybill\\svx\\CharsetNote");
		String str = FileUtility.readFile(objFile, "utf-8");
		
		str = str.replace("\r\n", "");
		//sbParameter.append("&shipmentrequest=" + new String(str.getBytes("utf-8")));
		sbParameter.append("&shipmentrequest=" + str);
		
		/*
		StringBuffer sbUrl = new StringBuffer();
		sbUrl.append("http://apps.dhl.com.hk/cgi-bin/label.cgi");
		StringBuffer sbParameter = new StringBuffer();
		sbParameter.append(buildParam());
		*/
		
		HttpExplorer objHttpExplorer = new HttpExplorer();
		return objHttpExplorer.getResponseStringByPostMethod(sbUrl.toString(), 
				sbParameter.toString(), 
				"",
				"utf-8");
	}
	
	private static String buildParam() throws Exception {
		String serverewbcodes = "<span class='TrackingNumber Number'>RX614782096DE</span>";
		serverewbcodes = serverewbcodes.substring(serverewbcodes.indexOf(">") + 1);
		serverewbcodes = serverewbcodes.substring(0, serverewbcodes.indexOf("<"));
		System.out.println(serverewbcodes);
		return serverewbcodes;
	}
	
	public static void testCharset() throws Exception {
		String str = "佛山小包挂号";
		System.out.println(new String(str.getBytes("utf-8"), "gb2312"));
	}
	
}
