package wkq.bl;


import wkq.da.FeekindColumns;
import wkq.dax.FeekindDemand;

import wkq.tp.SaveFeekindHibernate;


public class Feekind {
	/*
	 * ��ӷ�������
	 */
	public FeekindColumns addFeekind(FeekindColumns objFeekindColumns) throws Exception
	{
		SaveFeekindHibernate objSFKTrans = new SaveFeekindHibernate();
		objSFKTrans.setParam(objFeekindColumns);//������
		objSFKTrans.execute();//���
		
		//��ȡ�µ��ֶβ����ظ��ֶζ���
		FeekindColumns objReturn = FeekindDemand.queryByFkcode(objSFKTrans.getNewFkcode());
		if(objReturn == null) return null;
		return objReturn;
	}
	
	/*
	 * ��������״̬�޸�
	 */
	/*public void modifyStatus(String strFkcode,String strSscode) throws Exception
	{
		ModifyFeekindStatusTrans objModifyFeekindStatusTrans = new ModifyFeekindStatusTrans();
		objModifyFeekindStatusTrans.setParam(strFkcode, strSscode);
		objModifyFeekindStatusTrans.execute();
	}*/
}
