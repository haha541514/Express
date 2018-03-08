package kyle.leis.eo.operation.predictwaybill.hualei;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import kyle.leis.eo.operation.predictwaybill.hualei.vo.HLResult;
import kyle.leis.eo.operation.predictwaybill.smerp.ToLowerCasePropertyNameProcessor;
import kyle.leis.eo.operation.predictwaybill.smerp.vo.PrintRequest;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;


public class HLAuthor{
	
	private static final String URL1_HL_CREATE = "http://www.sz56t.com:8082";//创建订单URL1
	private static final String URL2_HL_PRINT = "	http://www.sz56t.com:8089";//打印标签URL2
	private static final String HL_PRINTLABLE = "/order/FastRpt/PDF_NEW.aspx";//华磊打印标签的接口
	private static final String HL_ID_Authentication = "/selectAuth.htm?";//华磊身份认证接口
	
	private String username;
	private String password;
	public void setAuthenticationParam(String username,String password){
		this.username = username;
		this.password = password;
	}
	//华磊科技,身份认证
	public void createOrder() throws HttpException, IOException{
		HttpClient client = new HttpClient();
		client.setConnectionTimeout(30000);
		username = "TEST";
		password = "123456";
		
		String uri = URL1_HL_CREATE +HL_ID_Authentication+"username="+username+"&password="+password;
		PostMethod method = new PostMethod(uri);
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		int timeout = 30000;
		// 设置超时的时间
		method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, timeout);
		int status = 0;
		String response = "";
		status = client.executeMethod(method);
	
		if (status == HttpStatus.SC_OK) {
			
			InputStream inputStream = method.getResponseBodyAsStream();//获取输入流
			BufferedReader br = new BufferedReader(new InputStreamReader(
					inputStream));
			StringBuffer stringBuffer = new StringBuffer();
			String str = "";
			while ((str = br.readLine()) != null) {
				stringBuffer.append(str);
			}
			response = stringBuffer.toString();//json格式的字符串
		}
		method.releaseConnection();
		System.out.println(response);
		HLResult buildHLRequest = buildHLRequest(response);
		System.out.println("customer_id:"+buildHLRequest.customer_id+" customer_userid"+buildHLRequest.getCustomer_userid()+" ack:"+buildHLRequest.getAck());
		
	}
	
	public HLResult buildHLRequest(String request){
		JSONObject  json = JSONObject.fromObject(request);
		JsonConfig config = new JsonConfig();
		config.setRootClass(HLResult.class);
	
		ToLowerCasePropertyNameProcessor propertyNameProcessor = new ToLowerCasePropertyNameProcessor();
		config.registerJavaPropertyNameProcessor(PrintRequest.class, propertyNameProcessor);
		
		return (HLResult) JSONObject.toBean(json, config);
	}
	/*
	 * 标签打印
	 * String Format="A4_EMS_BGD.frx";
	String PrintType;
	String order_id;
	
	public void setHLPrintLableParams(String Format,String PrintType,String order_id){
		
		this.Format = Format;
		this.PrintType = PrintType;
		this.order_id = order_id;
	}*/
	
	
	

	public static void main(String[] args) {
		HLAuthor author = new HLAuthor();	
		try {
			author.createOrder();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
		
	}

	
	
	

	
	
	
}