package kyle.leis.eo.operation.corewaybill.dax;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;

public class RequestUSPSEndofDayXML extends RequestXML {
	private static String m_strModelContent;
	private final static String SITE_ID = "TEST";
	private final static String PASSWORD = "TEST";
	private final static String SHIPPER = "IEEC";
	private static String USPS_WAYBILLCODE_URL = "192.168.1.9/Progistics/XML_Processor/Server/XMLProcDLL.asp";

	@Override
	public String buildRequestXML(ForinputallColumns objFIAColumns,
			List listCargo, PromptUtilityCollection objPUCollection)
			throws Exception {
		// 构造，发送EnumRequest并接收EnumResponse
		if (StringUtility.isNull(m_strModelContent))
			m_strModelContent = getRequestModelContent("EnumRequest.xml");
		String strModelContent = m_strModelContent;
		strModelContent.replace("$login$", SITE_ID);
		strModelContent.replace("$password$", PASSWORD);
		strModelContent.replace("$enumtype$", "CLOSEOUTITEMLIST");
		strModelContent.replace("$sc$", "TANDATA_USPS.USPS.PARCELPOST");
		strModelContent.replace("$shipper$", SHIPPER);
		strModelContent.replace("$groupingid$", "WORLD_EASE");
		String objEODResponse = sendXML(strModelContent, false,USPS_WAYBILLCODE_URL,"");
		
		ArrayList returnList = new ArrayList();
		int starIndex=0;
		int endIndex=0;
		int nextOne=0;
		while (true){
			starIndex = objEODResponse.indexOf("<RETURN>",nextOne);
			//截取字段完毕，跳出循环
			if(starIndex == -1){
				break;
			}
			endIndex = objEODResponse.indexOf("</RETURN>", starIndex);
			starIndex = starIndex + "<RETURN>".length();
			String colseOutItem = objEODResponse.substring(starIndex, endIndex);
			returnList.add(colseOutItem);
			
			nextOne=endIndex;
		}
		
		ArrayList closeList = new ArrayList();
		String sc = "";
		String closeoutItems="";
		for(int i = 0;i < returnList.size();i++){
			String objReturn = (String) returnList.get(0); 
			//sc
			int SCStartIndex = objReturn.indexOf("<ENUMRETURN>");
			int SCEndIndex = objReturn.indexOf("</ENUMRETURN>", SCStartIndex);
			SCStartIndex = SCStartIndex + "<ENUMRETURN>".length();
			String enumReturn = objReturn.substring(SCStartIndex, SCEndIndex);
			
			SCStartIndex = enumReturn.indexOf("(");
		    SCEndIndex = enumReturn.indexOf(")", SCStartIndex);
			SCStartIndex = SCStartIndex + "(".length();
			sc = enumReturn.substring(SCStartIndex, SCEndIndex);
			
			//closeItem
			int SBStartIndex = 0;
			int SBEndIndex = 0;
			String closeoutItem = "";
			while(true){
				SBStartIndex = objReturn.indexOf("<SYMBOL>",SBStartIndex);
				if(SBStartIndex == -1){
					break;
				}
				SBEndIndex = objReturn.indexOf("</SYMBOL>", SBStartIndex);
				SCStartIndex = SBStartIndex + "<SYMBOL>".length();
				closeoutItem = objReturn.substring(SBStartIndex, SBEndIndex);
				SBStartIndex=SBEndIndex;
				closeoutItems.concat("&"+closeoutItem);
			}
			closeList.add(sc+""+closeoutItems);
		}

		// 发送CloseOutRequest并接收CloseOutResponse
		if (StringUtility.isNull(m_strModelContent))
			m_strModelContent = getRequestModelContent("CloseOutRequest.xml");
		strModelContent = m_strModelContent;
		strModelContent.replace("$login$", SITE_ID);
		strModelContent.replace("$password$", PASSWORD);
		
		StringBuffer strTransferCloseWords = new StringBuffer();
		for(int i = 0;i < closeList.size();i++){
			String[] SCAndCloseOut = closeList.get(i).toString().split("&");
			
			strTransferCloseWords.append("\r\n");
			strTransferCloseWords.append("			<CLOSEOUT>");
			strTransferCloseWords.append("\r\n");
			strTransferCloseWords.append("				<SC>"+SCAndCloseOut[0]+"</SC>");
			strTransferCloseWords.append("\r\n");
			strTransferCloseWords.append("				<SHIPPER>"+SHIPPER+"</SHIPPER>");
			for (int j = 1;j<SCAndCloseOut.length;j++){
				strTransferCloseWords.append("\r\n");
				strTransferCloseWords.append("				<CLOSEOUTITEM>"+SCAndCloseOut[j]+"</CLOSEOUTITEM>");
			}
			strTransferCloseWords.append("\r\n");
			strTransferCloseWords.append("			</CLOSEOUT>");
			strTransferCloseWords.append("\r\n");
		}
		strModelContent.replace("<ERROR>$closeout$</ERROR>", strTransferCloseWords);
		objEODResponse = sendXML(strModelContent, false,
				USPS_WAYBILLCODE_URL,
				"");
		int iStartEODIndex = objEODResponse.indexOf("<SHIPFILEITEM>");
		int iEndEODIndex = objEODResponse.indexOf("</SHIPFILEITEM>", iStartEODIndex);
		iStartEODIndex = iStartEODIndex + "<SHIPFILEITEM>".length();
		String  shipfileitem= objEODResponse.substring(iStartEODIndex, iEndEODIndex);
		
		
		//构造 EODFileRequest.xml文件并返回
		if (StringUtility.isNull(m_strModelContent))
			m_strModelContent = getRequestModelContent("EODFileRequest.xml");
		strModelContent = m_strModelContent;
		
		strModelContent.replace("$login$", SITE_ID);
		strModelContent.replace("$password$", PASSWORD);
		strModelContent.replace("$shipper$", SHIPPER);
		strModelContent.replace("$sc$", "TANDATA_USPS.USPS.PARCELPOST");
		strModelContent.replace("$shipfileitem$", shipfileitem);
		strModelContent.replace("$docformat$", "TANDATA_UPS_PICKUP_SUMMARY_BARCODE.STANDARD");
		strModelContent.replace("$docport$", "LPT2");
		strModelContent.replace("$docprintersymbol$", "GENERIC.WINDOWSPRINTER");
		strModelContent.replace("$docstocksymbol$", "LETTER");
		
		return strModelContent;
	}

	public static String sendXML(String request, boolean httpsFlag,
			String hName, String port) {
		URL aspPage;
		InputStream isXMLResponse;
		String response = new String();
		String httpOrHttps = "http";

		System.out.println("XML Request: ");
		System.out.println(request);

		try {
			System.out.println("In sendXML method...");
			// define all the pages
			if (httpsFlag) {
				httpOrHttps = "https";
			} else {
				httpOrHttps = "http";
			}

			System.out
					.println("Posting to URL: " + httpOrHttps + "://" + hName);
			aspPage = new URL(httpOrHttps + "://" + hName);
			HttpURLConnection connection2 = (HttpURLConnection) aspPage
					.openConnection();

			// setup connection
			connection2.setDoInput(true);
			connection2.setDoOutput(true);
			connection2.setRequestMethod("POST");
			connection2.setUseCaches(false);
			connection2.setRequestProperty("Content-type",
					"text/xml; charset=utf-8");

			String xml_request = new String(request);
			byte[] buffer = xml_request.getBytes("UTF-8");

			connection2.setRequestProperty("Content-Length", String
					.valueOf(buffer.length));

			// POST the request to the connections OutputStream
			DataOutputStream toServer = new DataOutputStream(connection2
					.getOutputStream());
			try {
				int bytesRead = 0;
				toServer.write(buffer, 0, buffer.length);
			} finally {
				toServer.close();
				toServer.flush();
			}

			// read back the repsonse to check if the post request was
			// successful
			isXMLResponse = connection2.getInputStream();

			int charRead;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while (true) {
				charRead = isXMLResponse.read();
				if (charRead == -1)
					break;

				baos.write(charRead);
			}

			response = baos.toString();
			System.out.println("XML Response: ");
			// response=new String(response.getBytes("ISO-8859-1"),"utf-8") ;
			System.out.println(response);

			isXMLResponse.close();
		} catch (MalformedURLException mue) {
			System.out.println("MalformedURLException: " + mue);
		} catch (IOException ioe) {
			System.out.println("IOException: " + ioe);
		} catch (Exception exe) {
			System.out.println("Exeception: " + exe);
		}
		return response;
	}

}
