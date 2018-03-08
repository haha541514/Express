package kyle.leis.eo.operation.corewaybill.dax;

import kyle.common.util.jlang.StringUtility;

public class RequestDHLSoapXML extends RequestDHLXML {
	private static String m_strModelContent;
	
	public RequestDHLSoapXML(){
		DHL_SITE_ID = "B-06";
		DHL_PASSWORD = "641497";
	}
	
	protected String getRequestModelContent() throws Exception {
		if (StringUtility.isNull(m_strModelContent))
			m_strModelContent = getRequestModelContent("ShipmentValidateRequest_ZJ.xml");
		return m_strModelContent;
	}	
}
