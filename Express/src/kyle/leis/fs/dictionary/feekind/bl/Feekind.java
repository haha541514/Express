package kyle.leis.fs.dictionary.feekind.bl;


import kyle.leis.fs.dictionary.feekind.da.FeekindColumns;
import kyle.leis.fs.dictionary.feekind.dax.FeekindDemand;
import kyle.leis.fs.dictionary.feekind.tp.ModifyFeekindStatusTrans;
import kyle.leis.fs.dictionary.feekind.tp.SaveFeekindTransaction;

public class Feekind {
	/*
	 * 添加费用种类
	 */
	public FeekindColumns addFeekind(FeekindColumns objFeekindColumns) throws Exception
	{
		SaveFeekindTransaction objSFKTrans = new SaveFeekindTransaction();
		objSFKTrans.setParam(objFeekindColumns);
		objSFKTrans.execute();
		
		FeekindColumns objReturn = FeekindDemand.queryByFkcode(objSFKTrans.getNewFkcode());
		if(objReturn == null) return null;
		return objReturn;
	}
	
	/*
	 * 费用种类状态修改
	 */
	public void modifyStatus(String strFkcode,String strSscode) throws Exception
	{
		ModifyFeekindStatusTrans objModifyFeekindStatusTrans = new ModifyFeekindStatusTrans();
		objModifyFeekindStatusTrans.setParam(strFkcode, strSscode);
		objModifyFeekindStatusTrans.execute();
	}
}
