package kyle.leis.fs.authoritys.rolegmenus.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.fs.authoritys.rolegmenus.bl.RoleGmenus;
import kyle.leis.fs.authoritys.rolegmenus.da.RolegmenusColumns;
import kyle.leis.fs.authoritys.rolegmenus.da.RolegmenusCondition;
import kyle.leis.fs.authoritys.rolegmenus.dax.QueryByStructurecodeReturn;
import kyle.leis.fs.authoritys.rolegmenus.dax.RoleGmenusDemand;

public class RoleGmenusService extends AService {

	public String saveRolgGmenus(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,3,this);
		
		String[] astrRole_code = (String[])objPD.getParameterArray(0, String.class);
		String[] astrGmenus_code = (String[])objPD.getParameterArray(1, String.class);
		String strOperId = (String)objPD.getParameter(2, String.class);
		
		RoleGmenus objRoleGmenus = new RoleGmenus();
		objRoleGmenus.save(astrRole_code, astrGmenus_code, strOperId);
		return "";////
	}
	
	public String queryRoleGmenus(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		
		RolegmenusCondition objRGMCondition = (RolegmenusCondition)objPD.getParameter(0, RolegmenusCondition.class);
		List objList = RoleGmenusDemand.query(objRGMCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	public String queryByPk(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,2,this);
		
		String strRole_code = (String)objPD.getParameter(0, String.class);
		String strGmenus_code = (String)objPD.getParameter(1, String.class);
		
		RolegmenusColumns objRGMColumns = RoleGmenusDemand.queryByPK(strRole_code, strGmenus_code);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objRGMColumns);
		return objEncode.toString();
	}
	
	public String queryByStructurecode(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,2,this);
		
		String strStructurecode = (String)objPD.getParameter(0, String.class);
		String strIsk_code = (String)objPD.getParameter(1, String.class);
		
		RoleGmenus objRoleGmenus = new RoleGmenus();
		QueryByStructurecodeReturn objQBSReturn = objRoleGmenus.queryByStructurecode(strStructurecode, strIsk_code);
		
		return objQBSReturn.toString();
	}
}
