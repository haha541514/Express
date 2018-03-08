package kyle.leis.fs.authoritys.role.bl;

import java.util.List;

import kyle.leis.fs.authoritys.role.da.RoleColumns;
import kyle.leis.fs.authoritys.role.da.RoleCondition;
import kyle.leis.fs.authoritys.role.da.RoleQuery;
import kyle.leis.fs.authoritys.role.tp.DeleteRoleTransaction;
import kyle.leis.fs.authoritys.role.tp.SaveRoleTransaction;

public class Role {
	/*
	 * 新增角色
	 */
	public RoleColumns save(String strOperId, RoleColumns objRoleColumns)
			throws Exception {
		SaveRoleTransaction objSRTrans = new SaveRoleTransaction();
		objSRTrans.setParam(strOperId, objRoleColumns);
		objSRTrans.execute();
		
		RoleCondition objRoleCondition = new RoleCondition();
		objRoleCondition.setRlcode(objSRTrans.getM_strNewRlcode());
		return (RoleColumns)query(objRoleCondition).get(0);
	}

	/*
	 * 查询角色
	 */
	public List query(RoleCondition objRoleCondition) throws Exception {
		RoleQuery objQuery = new RoleQuery();
		objQuery.setCondition(objRoleCondition);	
		return objQuery.getResults();
	}

	/*
	 * 根据平台查询角色
	 */
	public List queryRoleByIskCode(String strIsk_code) throws Exception {
		RoleCondition objRoleConditon = new RoleCondition();
		objRoleConditon.setIsk_code(strIsk_code);
		return query(objRoleConditon);
	}

	/*
	 * 根据角色代码查角色
	 */
	public List queryRoleByRoleCode(String strRole_code) throws Exception {
		RoleCondition objRoleCondition = new RoleCondition();
		objRoleCondition.setRlcode(strRole_code);
		return query(objRoleCondition);
	}

	public void delete(String strRole_code) throws Exception {
		DeleteRoleTransaction objDRTrans = new DeleteRoleTransaction();
		objDRTrans.setParam(strRole_code);
		objDRTrans.execute();
	}
}
