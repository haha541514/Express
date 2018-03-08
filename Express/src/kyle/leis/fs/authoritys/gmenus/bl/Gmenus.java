package kyle.leis.fs.authoritys.gmenus.bl;

import kyle.leis.fs.authoritys.gmenus.da.GmenusColumns;
import kyle.leis.fs.authoritys.gmenus.da.GmenusCondition;
import kyle.leis.fs.authoritys.gmenus.da.GmenusQuery;
import kyle.leis.fs.authoritys.gmenus.tp.DeleteGmenusTransaction;
import kyle.leis.fs.authoritys.gmenus.tp.SaveGmenusTransaction;

public class Gmenus {
	
	public GmenusColumns save(GmenusColumns objGmenusColumns)
	{
		try
		{
			SaveGmenusTransaction objSGMTrans = new SaveGmenusTransaction();
			objSGMTrans.setParam(objGmenusColumns);
			objSGMTrans.execute();
			
			GmenusQuery objGmenusQuery = new GmenusQuery();
			GmenusCondition objGmenusCondition = new GmenusCondition();
			objGmenusCondition.setGmcode(objSGMTrans.getM_strNewgmcode());
			objGmenusQuery.setCondition(objGmenusCondition);
			return (GmenusColumns)objGmenusQuery.getResults().get(0);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public void delete(String strGmcode)
	{
		try
		{
			DeleteGmenusTransaction objDGMTrans = new DeleteGmenusTransaction();
			objDGMTrans.setParam(strGmcode);
			objDGMTrans.execute();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
