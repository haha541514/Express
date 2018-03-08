package kyle.leis.eo.operation.corewaybill.dax;

import java.io.FileInputStream;
import java.util.List;

import com.WaybillcodeParam;

import kyle.common.connectors.servlet.ActionServletConstant;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;

public abstract class ARequestXML {
	
	public abstract WaybillcodeParam buildRequest(ForinputallColumns objFIAColumns,
			List listCargo,
			List listPieces,
			PromptUtilityCollection objPUCollection) throws Exception;	
	
	
	protected String getRequestModelContent() throws Exception {
		return "";
	}
	
	public String getRequestModelContent(String strXMLFile) throws Exception {
		String strModelContent = "";
		String strModelFile = ActionServletConstant.getInstance().getRealPath();
		
		strModelFile = strModelFile + strXMLFile;
		//strModelFile = strXMLFile;
		FileInputStream fis = new FileInputStream(strModelFile);
		try {
			int fisSize = fis.available();
			byte[] buffer = new byte[fisSize];
			fis.read(buffer);
			strModelContent = new String(buffer);
		} finally {
			if (fis != null)
				fis.close();
		}
		return strModelContent;
	}	
	
	
}
