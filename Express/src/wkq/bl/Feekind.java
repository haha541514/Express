package wkq.bl;


import wkq.da.FeekindColumns;
import wkq.dax.FeekindDemand;

import wkq.tp.SaveFeekindHibernate;


public class Feekind {
	/*
	 * 添加费用种类
	 */
	public FeekindColumns addFeekind(FeekindColumns objFeekindColumns) throws Exception
	{
		SaveFeekindHibernate objSFKTrans = new SaveFeekindHibernate();
		objSFKTrans.setParam(objFeekindColumns);//有数据
		objSFKTrans.execute();//添加
		
		//获取新的字段并返回该字段对象
		FeekindColumns objReturn = FeekindDemand.queryByFkcode(objSFKTrans.getNewFkcode());
		if(objReturn == null) return null;
		return objReturn;
	}
	
	/*
	 * 费用种类状态修改
	 */
	/*public void modifyStatus(String strFkcode,String strSscode) throws Exception
	{
		ModifyFeekindStatusTrans objModifyFeekindStatusTrans = new ModifyFeekindStatusTrans();
		objModifyFeekindStatusTrans.setParam(strFkcode, strSscode);
		objModifyFeekindStatusTrans.execute();
	}*/
}
