package kyle.leis.fs.dictionary.operator.sv;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.common.util.prompt.PromptUtility;
import kyle.leis.fs.dictionary.operator.bl.Operator;
import kyle.leis.fs.dictionary.operator.dax.OperatorLoginReturn;

public class OperatorServiceTest extends AService {
	
	public String login(Decoder objPD) throws Exception {
		int iParameterCount = objPD.getParameterCount();
		
		String strOperCode = (String)objPD.getParameter(0, String.class);
		String strOperPassword = (String)objPD.getParameter(1, String.class);
		String strIsk_code = (String)objPD.getParameter(2, String.class);
		String strHDSerialNumber = (String)objPD.getParameter(3, String.class);
		String strMACAddress = (String)objPD.getParameter(4, String.class);
		String strIPAddress = (String)objPD.getParameter(5, String.class);		
		boolean isCarryoverSystem = false;
		if (iParameterCount == 7)
			isCarryoverSystem = Boolean.parseBoolean((String)objPD.getParameter(6, String.class));
		
		Operator objOperator = new Operator();
		OperatorLoginReturn objOLR = objOperator.login(strOperCode, strOperPassword, strIsk_code,
				strHDSerialNumber, strMACAddress, strIPAddress,
				isCarryoverSystem);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objOLR.getLoginReturn());
		objEncode.addParameter(objOLR.getOperatorColumns());
		objEncode.addParameter(objOLR.getRoleMenus());
		return objEncode.toString();
	}
	
	public String logintest(Decoder objPD) throws Exception {
		int iParameterCount = objPD.getParameterCount();
		
		String strOperCode = (String)objPD.getParameter(0, String.class);
		String strOperPassword = (String)objPD.getParameter(1, String.class);
		String strIsk_code = (String)objPD.getParameter(2, String.class);
		String strHDSerialNumber = (String)objPD.getParameter(3, String.class);
		String strMACAddress = (String)objPD.getParameter(4, String.class);
		String strIPAddress = (String)objPD.getParameter(5, String.class);		
		boolean isCarryoverSystem = false;
		if (iParameterCount == 7)
			isCarryoverSystem = Boolean.parseBoolean((String)objPD.getParameter(6, String.class));
		
		Operator objOperator = new Operator();
		OperatorLoginReturn objOLR = objOperator.login(strOperCode, strOperPassword, strIsk_code,
				strHDSerialNumber, strMACAddress, strIPAddress,
				isCarryoverSystem);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objOLR.getLoginReturn());
		objEncode.addParameter(objOLR.getOperatorColumns());
		objEncode.addParameter(objOLR.getRoleMenus());
		
		Thread.sleep(10000);
		
		return objEncode.toString();
	}	
	
	public String modifyPassword(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 3, this);
		
		String strOperId = (String)objPD.getParameter(0, String.class);
		String strOldPassword = (String)objPD.getParameter(1, String.class);
		String strNewPassword = (String)objPD.getParameter(2, String.class);
		
		Operator objOperator = new Operator();
		PromptUtility objPUtility = objOperator.modifyPassword(strOperId, 
				strOldPassword, 
				strNewPassword);
		if (objPUtility == null)
			return "";
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objPUtility.toStringArray());
		return objEncode.toString();
	}
}
