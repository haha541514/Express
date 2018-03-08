package kyle.leis.fs.authoritys.userrole.bl;

import java.util.List;

import kyle.leis.fs.authoritys.userrole.da.UserroleCondition;
import kyle.leis.fs.authoritys.userrole.dax.UserroleDemand;
import kyle.leis.fs.authoritys.userrole.tp.DeleteUserRoleTransaction;
import kyle.leis.fs.authoritys.userrole.tp.SaveUserRoleTransaction;


public class UserRole {

	public void save(String strUser_code,String[] astrRole_code,String strIsk_code) throws Exception
	{
		SaveUserRoleTransaction objSURTrans = new SaveUserRoleTransaction();
		objSURTrans.setParam(strUser_code, astrRole_code, strIsk_code);
		objSURTrans.execute();
	}
	
	/*
	 * 删除指定平台用户下的多个角色
	 */
	public void delete(String strUser_code,String[] astrRole_code,String strIsk_code) throws Exception
	{
		DeleteUserRoleTransaction objDURTrans = new DeleteUserRoleTransaction();
		objDURTrans.setParam(strUser_code, astrRole_code, strIsk_code);
		objDURTrans.execute();
	}
	/*
	 * 删除指定平台用户下的所有角色
	 */
	public void delete(String strUser_code,String strIsk_code) throws Exception
	{
		/*DeleteUserRoleTransaction objDURTrans = new DeleteUserRoleTransaction();
		objDURTrans.setParam(strUser_code, null, strIsk_code);
		objDURTrans.execute();*/
		
		delete(strUser_code,null,strIsk_code);
	}
	
	public List queryByUrIskCode(String strUser_code,String strIsk_code) throws Exception
	{
		UserroleCondition objUserroleCondition = new UserroleCondition();
		objUserroleCondition.setUrusercode(strUser_code);
		objUserroleCondition.setIsk_code(strIsk_code);
		return UserroleDemand.query(objUserroleCondition);
	}
}
