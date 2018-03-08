package kyle.leis.eo.operation.predictwaybill.tongtool;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class ApiTongHttpResult {
	public  static String getHttpResult(String urlget,String json){
		String result="";
		try {
			URL url = new URL(urlget);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");// 提交模式
	        conn.setDoOutput(true);// 是否输入参数
	        conn.setRequestProperty("contentType", "utf-8");
	        StringBuffer params = new StringBuffer();
	        String utf8 = UTF2GBK.GBK2Unicode(json);
//	        String utf8= new String(json.getBytes("GBK"),"UTF-8");
//	        String utf8= new String(json.getBytes("UTF-8"),"GBK");  
	        System.out.println(utf8);
	        params.append("q").append("=").append(utf8);
	        byte[] bypes = params.toString().getBytes();
	        conn.getOutputStream().write(bypes);// 输入参数
	        InputStream inStream=conn.getInputStream();
	        result = new String(StreamTool.readInputStream(inStream), "utf-8");
//			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//			httpURLConnection.setDoOutput(true);
//			httpURLConnection.setDoInput(true);
//			httpURLConnection.setConnectTimeout(10000);
//			httpURLConnection.setRequestMethod("POST");
//			httpURLConnection.setRequestProperty("Content-type", "text/html");
//			httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
//			httpURLConnection.setRequestProperty("contentType", "utf-8");
//			httpURLConnection.connect();  
//			InputStreamReader  bis = new InputStreamReader(httpURLConnection.getInputStream(),"utf-8");
//			int c = 0;
//			while((c = bis.read()) != -1){        
//				result=result+(char)c;   
//			}
		}catch (Exception e) {
			e.printStackTrace(); 
			System.out.println(urlget + "  HTTP通信失败！");
		}
		return result;
	}
}
