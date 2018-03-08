package kyle.leis.fs.authoritys.role.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.fs.authoritys.role.bl.Role;
import kyle.leis.fs.authoritys.role.da.RoleColumns;
import kyle.leis.fs.authoritys.role.da.RoleCondition;

public class RoleService extends AService {
	
	public String save(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,2,this);
		
		String strOperId = (String)objPD.getParameter(0, String.class);
		RoleColumns objRoleColumns = (RoleColumns)objPD.getParameter(1, RoleColumns.class);
		
		Role objRole = new Role();
		RoleColumns objRoleReturn = objRole.save(strOperId, objRoleColumns);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objRoleReturn);
		return objEncode.toString();
	}
	
	public String query(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		
		RoleCondition objRoleCondition = (RoleCondition)objPD.getParameter(0, RoleCondition.class);
		
		Role objRole = new Role();
		List objList = objRole.query(objRoleCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	public String delete(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		
		String strRole_code = (String)objPD.getParameter(0, String.class);
		
		Role objRole = new Role();
		objRole.delete(strRole_code);
		
		return "";
	}
}
