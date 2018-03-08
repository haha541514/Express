package kyle.leis.fs.authoritys.userrole.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.fs.authority.bl.Authority;
import kyle.leis.fs.authority.dax.RoleMenusReturn;
import kyle.leis.fs.authoritys.userrole.bl.UserRole;

public class UserRoleService extends AService {

	public String queryUserMenus(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);

		String strUserCode = (String)objPD.getParameter(0, String.class);
		String strIsk_code = (String)objPD.getParameter(1, String.class);		
		
		Authority objAuthority = new Authority();
		RoleMenusReturn objRMR = objAuthority.queryGUIMenu(strUserCode, 
				strIsk_code, 
				false);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objRMR.getReturnDescribtion());
		objEncode.addParameter(objRMR.getRoleMenus());
		return objEncode.toString();
	}
	
	public String saveUserRole(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,3,this);
		
		String strUser_code = (String)objPD.getParameter(0,String.class);
		String[] astrRole_code = (String[])objPD.getParameterArray(1, String.class);
		String strIsk_code = (String) objPD.getParameter(2, String.class);
		
		UserRole objUserRole = new UserRole();
		objUserRole.save(strUser_code, astrRole_code, strIsk_code);
		return "";///////
	}
	
	public String deleteUserRole(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,3,this);
		
		String strUser_code  = (String)objPD.getParameter(0, String.class);
		String[] astrRole_code = (String[])objPD.getParameterArray(1, String.class);
		String strIsk_code = (String)objPD.getParameter(2, String.class);
		
		UserRole objUserRole = new UserRole();
		objUserRole.delete(strUser_code,astrRole_code, strIsk_code);
		return "";
	}
	
	public String deleteAllUserRole(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,2,this);
		
		String strUser_code = (String)objPD.getParameter(0, String.class);
		String strIsk_code = (String)objPD.getParameter(1, String.class);
		
		UserRole objUserRole = new UserRole();
		objUserRole.delete(strUser_code, strIsk_code);
		return "";
	}
	
	/*public String queryUserRoles(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,2,this);
		
		String strUser_code = (String)objPD.getParameter(0, String.class);
		String strIsk_code = (String)objPD.getParameter(1, String.class);
		
		Authority objAuthority = new Authority();
		List objList = objAuthority.queryRoles(strUser_code, strIsk_code);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}*/
	
	public String queryUserRoles(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,2,this);
		
		String strUser_code = (String)objPD.getParameter(0, String.class);
		String strIsk_code = (String)objPD.getParameter(1, String.class);
		
		UserRole objUserrole = new UserRole();
		List objList = objUserrole.queryByUrIskCode(strUser_code, strIsk_code);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
}
