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
	 * ����û���ɫ
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
	 * �����û�����Ϣϵͳ��ò˵�
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
		// ����û���ɫ
		List listUserRole = queryRoles(strUserCode, strIsk_code);
		if (CollectionUtility.isNull(listUserRole)) {
			// δ�����κν�ɫ
			objRoleMenusReturn.setExceptionSign(true);
			objRoleMenusReturn.setErrText("δ�����κν�ɫ");
		}
		boolean isAdminSign = isContainAdminRole(listUserRole);
		// ������в˵�
		if (isAdminSign && !isCarryoverSystem) {
			List listMenus = getAllMenus(strIsk_code);
			objRoleMenusReturn.setRoleMenus(listMenus);
		}
        // ��ý�ɫ�˵�
		else {
			String strRoles = getRoles(listUserRole);
			RolemenusCondition objRoleMenusC = new RolemenusCondition();
			objRoleMenusC.setRlcode(strRoles);
			// ����ǽ�ת����ϵͳ��˵��̶�
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
	 * ���ĳ����ɫ�����Ƿ�Ϊ������������Ա�Ľ�ɫ
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
