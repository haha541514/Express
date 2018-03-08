package kyle.leis.eo.operation.corewaybill.sv;

import java.util.Arrays;
import java.util.List;

import kyle.common.connectors.servlet.RemoteServlet;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;

public class CorewaybillDelegate extends RemoteServlet {
	
	public CorewaybillDelegate() {
		super();
		setServiceName("CorewaybillService");
		setServerName("Waybillcode");
	}
	
	public List<String> getDHLChildLabelcode(String strBCK_Code,
			String strOperId, 
			int iPieces) throws Exception {
		Encoder objEncode = new Encoder();
		objEncode.addParameter(strBCK_Code);
		objEncode.addParameter(strOperId);
		objEncode.addParameter(String.valueOf(iPieces));
		// 
		String strResults = execute("getDHLChildLabelcode", objEncode.toString());
		Decoder objPD = new Decoder(strResults);
		
		String[] astr = objPD.getParameterArray(0, String.class);
		if (astr == null || astr.length < 1)
			return null;
		return Arrays.asList(astr);
	}
	
	public String getDHLMainLabelcode(String strBCK_Code,
			String strOperId, 
			String strComplexPrefixType,
			ForinputallColumns objFIAColumns) throws Exception {
		Encoder objEncode = new Encoder();
		objEncode.addParameter(strBCK_Code);
		objEncode.addParameter(strOperId);
		objEncode.addParameter(strComplexPrefixType);
		objEncode.addParameter(objFIAColumns);
		// 
		String strResults = execute("getDHLMainLabelcode", objEncode.toString());
		Decoder objPD = new Decoder(strResults);
		
		String strLabelcode = (String)objPD.getParameter(0, String.class);
		return strLabelcode;
	}	
	
	public String getCustomsLabelcode(String strOperId) throws Exception {
		Encoder objEncode = new Encoder();
		objEncode.addParameter(strOperId);
		// 
		String strResults = execute("getCustomsLabelcode", objEncode.toString());
		Decoder objPD = new Decoder(strResults);
		
		String strLabelcode = (String)objPD.getParameter(0, String.class);
		return strLabelcode;
	}		
	
}
