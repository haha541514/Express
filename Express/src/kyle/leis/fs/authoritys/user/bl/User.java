package kyle.leis.fs.authoritys.user.bl;

import java.util.List;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.leis.fs.authoritys.user.da.UserColumns;
import kyle.leis.fs.authoritys.user.da.UserCondition;
import kyle.leis.fs.authoritys.user.da.UserQuery;
import kyle.leis.fs.authoritys.user.dax.UserDemand;
import kyle.leis.fs.authoritys.user.tp.DeleteUserTransaction;
import kyle.leis.fs.authoritys.user.tp.ModifyUserStatusTransaction;
import kyle.leis.fs.authoritys.user.tp.SaveUserTransaction;

public class User {
	
	/*
	 * ÍøÕ¾ÓÃ»§µÇÂ¼
	 */
	public UserColumns login(String strOpcode,String strPassowrd)
	{
		List objList = null;
		try
		{
			UserCondition objUserCondition = new UserCondition();
			objUserCondition.setOpcode(strOpcode);
			objUserCondition.setOppassword(strPassowrd);
			objList = UserDemand.query(objUserCondition);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		if(objList == null ||objList.size() != 1)
			return null;
		return (UserColumns)objList.get(0);
	}
	
	
	public UserColumns save(String strOperId, UserColumns objUserColumns){
		List listUser = null;
		try{
			SaveUserTransaction objSaveUserTrans = new SaveUserTransaction();
			objSaveUserTrans.setParam(strOperId, objUserColumns);
			objSaveUserTrans.execute();			
			// Ë¢ÐÂ»º³å
			QueryCache objQueryCache = new QueryCache();
			objQueryCache.reset();
			
			UserQuery objUserQuery = new UserQuery();
			UserCondition objUserCondition = new UserCondition();
			objUserCondition.setOpid(objSaveUserTrans.getM_newOperId());
			objUserQuery.setCondition(objUserCondition);
			listUser = objUserQuery.getResults();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		if(listUser == null || listUser.size()!=1)
			return null;
		return (UserColumns)listUser.get(0);
	}
	
	public void modifyStatus(String strOperId,String strOsCode)
	{
		try
		{
			ModifyUserStatusTransaction objModifyUserStatusTrans = new ModifyUserStatusTransaction();
			objModifyUserStatusTrans.setParam(strOperId, strOsCode);
			objModifyUserStatusTrans.execute();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void delete(String strOpId)
	{
		try
		{
			DeleteUserTransaction objDeleteUserTrans = new DeleteUserTransaction();
			objDeleteUserTrans.setParams(strOpId);
			objDeleteUserTrans.execute();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
