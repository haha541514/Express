package kyle.leis.eo.operation.predictwaybill.mabang;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiHttpResult {
	public  static String getHttpResult(String urlget){
		String result="";
		try {
			URL url = new URL(urlget);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setConnectTimeout(10000);
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("contentType", "utf-8");
			httpURLConnection.connect();  
			InputStreamReader  bis = new InputStreamReader(httpURLConnection.getInputStream(),"utf-8");
			int c = 0;
			while((c = bis.read()) != -1){        
				result=result+(char)c;   
			}
		}catch (Exception e) {
			e.printStackTrace(); 
			System.out.println(urlget + "  HTTPÕ®–≈ ß∞‹£°");
			//		   result = "http error";
		}
		return result;
	}
}
