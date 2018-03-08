package kyle.leis.eo.operation.housewaybill.svx.parsexml;

import org.jdom.Element;

import kyle.common.conf.elementconfig.AElementConfig;

public class ServiceHeader extends AElementConfig {
	
	private String m_strUsercode;
	private String m_strPassword;
	
	public ServiceHeader(Element objElement) {
		parseFather(objElement, "ServiceHeader");
	}
	
	@Override
	protected void check(StringBuffer sbCheck) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void parse(Element objElement) {
		m_strUsercode = objElement.getChildText("Usercode");
		m_strPassword = objElement.getChildText("Password");
	}

	public String getPassword() {
		return m_strPassword;
	}

	public String getUsercode() {
		return m_strUsercode;
	}

}
