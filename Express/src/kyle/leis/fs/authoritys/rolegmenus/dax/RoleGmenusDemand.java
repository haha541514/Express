package kyle.leis.fs.authoritys.rolegmenus.dax;

import java.util.List;

import kyle.leis.fs.authoritys.role.da.RoleQuery;
import kyle.leis.fs.authoritys.rolegmenus.da.RolegmenusColumns;
import kyle.leis.fs.authoritys.rolegmenus.da.RolegmenusCondition;
import kyle.leis.fs.authoritys.rolegmenus.da.RolegmenusQuery;

public class RoleGmenusDemand {

	public static List query(RolegmenusCondition objRoglgmenusCondition) throws Exception
	{
		RolegmenusQuery objRolegmenusQuery = new RolegmenusQuery();
		objRolegmenusQuery.setCondition(objRoglgmenusCondition);
		return objRolegmenusQuery.getResults();
	}
	
	public static RolegmenusColumns queryByPK(String strRole_code,String strGmenu_code) throws Exception
	{
		RolegmenusCondition objRolegmenusCondition = new RolegmenusCondition();
		objRolegmenusCondition.setRlcode(strRole_code);
		objRolegmenusCondition.setGmcode(strGmenu_code);
		return (RolegmenusColumns)query(objRolegmenusCondition).get(0);
	}
	
	public static List queryByRlcode(String strRlcode) throws Exception
	{
		RolegmenusCondition objRolegmenusCondition = new RolegmenusCondition();
		objRolegmenusCondition.setRlcode(strRlcode);
		return query(objRolegmenusCondition);
	}
	
	public static List queryWithFather(String strRlcode) throws Exception
	{
		//查询出结构代码
		RoleQuery objRoleQuery = new RoleQuery();
		
		
		//查询菜单
		
		return null;
	}
	
	/*public static RolegmenusColumns queryByStructurecode(String strStructurecode,String strIsk_code) throws Exception
	{
		RolegmenusCondition objRolegmenusCondition = new RolegmenusCondition();
		objRolegmenusCondition.setRlstructurecode(strStructurecode);
		objRolegmenusCondition.setIskcode(strIsk_code);
		return (RolegmenusColumns)query(objRolegmenusCondition).get(0);
	}*/
	
	
	public static List queryByStructurecode(String strStructurecode,String strIsk_code) throws Exception
	{
		RolegmenusCondition objRolegmenusCondition = new RolegmenusCondition();
		objRolegmenusCondition.setRlstructurecode(strStructurecode);
		objRolegmenusCondition.setIskcode(strIsk_code);
		return query(objRolegmenusCondition);
	}
	
}
