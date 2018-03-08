package kyle.leis.fs.authority.sv;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.fs.authority.bl.Authority;
import kyle.leis.fs.authority.dax.RoleMenusReturn;

public class AuthorityService extends AService {
	public String queryUserMenus(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);

		String strUserCode = (String)objPD.getParameter(0, String.class);
		String strIsk_code = (String)objPD.getParameter(1, String.class);		
		
		Authority objAuthority = new Authority();
		RoleMenusReturn objRMR = objAuthority.queryGUIMenu(strUserCode, 
				strIsk_code, false);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objRMR.getReturnDescribtion());
		objEncode.addParameter(objRMR.getRoleMenus());
		return objEncode.toString();
	}
	
}
