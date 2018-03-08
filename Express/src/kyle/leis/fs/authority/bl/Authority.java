package kyle.leis.fs.authority.bl;

import java.util.List;

import kyle.common.util.jlang.CollectionUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.authority.da.MenusQuery;
import kyle.leis.fs.authority.da.RolemenusCondition;
import kyle.leis.fs.authority.da.UserroleColumns;
import kyle.leis.fs.authority.da.UserroleCondition;
import kyle.leis.fs.authority.da.UserroleQuery;
import kyle.leis.fs.authority.dax.RoleMenusReturn;
import kyle.leis.fs.authority.dax.RolemenusQueryEX;

public class Authority {
	/**
	 * 获得用户角色
	 * @param strUserCode
	 * @param strIsk_code
	 * @return
	 * @throws Exception
	 */
	public List queryRoles(String strUserCode, String strIsk_code) 
	throws Exception {
		UserroleQuery objUserroleQ = new UserroleQuery();
		UserroleCondition objUserroleC = new UserroleCondition();
		objUserroleC.setUr_usercode(strUserCode);
		objUserroleC.setIsk_code(strIsk_code);
		objUserroleQ.setCondition(objUserroleC);
		
		List listUserroles = objUserroleQ.getResults();
		return listUserroles;
	}
	
	private List getAllMenus(String strIsk_code) throws Exception {
		MenusQuery objMenusQuery = new MenusQuery();
		if (StringUtility.isNull(strIsk_code))
			strIsk_code = "LEDIS";
		objMenusQuery.setIskcode(strIsk_code);
		return objMenusQuery.getResults();		
	}
	
	
	/**
	 * 根据用户和信息系统获得菜单
	 * @param strUserCode
	 * @param strIsk_code
	 * @throws Exception
	 */
	public RoleMenusReturn queryGUIMenu(String strUserCode, 
			String strIsk_code,
			boolean isCarryoverSystem) 
	throws Exception {
		RoleMenusReturn objRoleMenusReturn = new RoleMenusReturn();
		if (!StringUtility.isNull(strIsk_code) && strIsk_code.equals("LEDPIS")) {
			List listMenus = getAllMenus(strIsk_code);
			objRoleMenusReturn.setRoleMenus(listMenus);
			return objRoleMenusReturn;
		}
		// 获得用户角色
		List listUserRole = queryRoles(strUserCode, strIsk_code);
		if (CollectionUtility.isNull(listUserRole)) {
			// 未分配任何角色
			objRoleMenusReturn.setExceptionSign(true);
			objRoleMenusReturn.setErrText("未分配任何角色");
		}
		boolean isAdminSign = isContainAdminRole(listUserRole);
		// 获得所有菜单
		if (isAdminSign && !isCarryoverSystem) {
			List listMenus = getAllMenus(strIsk_code);
			objRoleMenusReturn.setRoleMenus(listMenus);
		}
        // 获得角色菜单
		else {
			String strRoles = getRoles(listUserRole);
			RolemenusCondition objRoleMenusC = new RolemenusCondition();
			objRoleMenusC.setRlcode(strRoles);
			// 如果是结转财务系统则菜单固定
			if (isCarryoverSystem) {
				objRoleMenusC.setEqualrlcode("2520");
				objRoleMenusC.setRlcode("");
			}
			RolemenusQueryEX objRoleMenusQ = new RolemenusQueryEX(strIsk_code);
			objRoleMenusQ.setCondition(objRoleMenusC);
			List listRoleMenus = objRoleMenusQ.getResults();
			
			objRoleMenusReturn.setRoleMenus(listRoleMenus);
		}
		return objRoleMenusReturn;
	}
	
	/**
	 * 检查某个角色组中是否为包含超级管理员的角色
	 * @param listUserRole
	 * @return
	 */
	private boolean isContainAdminRole(List listUserRole) {
		if (!CollectionUtility.isNull(listUserRole))
			for (int i = 0; i < listUserRole.size(); i++) {
				UserroleColumns objURColumns = (UserroleColumns)listUserRole.get(i);
				String strAdminSign = objURColumns.getRladministratorsign();
				if (!StringUtility.isNull(strAdminSign) && strAdminSign.equals("Y"))
					return true;
			}
		return false;
	}
	
	private String getRoles(List listUserRole) {
		String strRoles = "";
		if (!CollectionUtility.isNull(listUserRole)) {
			for (int i = 0; i < listUserRole.size(); i++) {
				UserroleColumns objURColumns = (UserroleColumns)listUserRole.get(i);
				String strRl_code = objURColumns.getRlcode();
				if (i < listUserRole.size() - 1)
					strRl_code = strRl_code + ",";
				strRoles = strRoles + strRl_code;
			}
		}
		return strRoles;
	}
	
}
