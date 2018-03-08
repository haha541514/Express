package kyle.leis.fs.dictionary.operator.sv;

import kyle.common.connectors.servlet.RemoteServlet;
import kyle.common.connectors.util.Encoder;

public class OperatorDelegate extends RemoteServlet {
	public OperatorDelegate() {
		super();
		setServiceName("OperatorServiceTest");
		// setServiceName("TrackfortestService");
	}
	
	public String login(String strOperCode, 
			String strOperPassword, 
			String strIsk_code,
			String strHDSerialNumber,
			String strMACAddress,
			String strIPAddress) throws Exception {
		Encoder objEncode = new Encoder();
		objEncode.addParameter(strOperCode);
		objEncode.addParameter(strOperPassword);
		objEncode.addParameter(strIsk_code);
		objEncode.addParameter(strHDSerialNumber);
		objEncode.addParameter(strMACAddress);
		objEncode.addParameter(strIPAddress);
		// ����ͬ���˵��ķ���
		return execute("logintest", objEncode.toString());
	}	
	
	public String insertHawb(String strHawbAndCountryAndPostcode) throws Exception {
		// ����ͬ���˵��ķ���
		return execute("insertHawb", strHawbAndCountryAndPostcode);
	}		
}
