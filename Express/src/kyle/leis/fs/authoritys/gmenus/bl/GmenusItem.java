package kyle.leis.fs.authoritys.gmenus.bl;
import kyle.leis.fs.authoritys.gmenus.da.GmenusColumns;
import kyle.leis.fs.authoritys.gmenus.da.GmenusitemColumns;
import kyle.leis.fs.authoritys.gmenus.tp.DeleteGmenusTransaction;
import kyle.leis.fs.authoritys.gmenus.tp.DeleteGmenusitemTransaction;
import kyle.leis.fs.authoritys.gmenus.tp.SaveGmenusitemTransaction;

public class GmenusItem {
	public GmenusitemColumns save(GmenusColumns objGmenusColumns,String contend)
	{
		try
		{
			SaveGmenusitemTransaction objSGMITrans = new SaveGmenusitemTransaction();
			objSGMITrans.setParam(objGmenusColumns,contend);
			objSGMITrans.execute();
		
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
			DeleteGmenusitemTransaction objDGMITrans = new DeleteGmenusitemTransaction();
			objDGMITrans.setParam(strGmcode);
			objDGMITrans.execute();	
			
			DeleteGmenusTransaction objDGMTrans = new DeleteGmenusTransaction();
			objDGMTrans.setParam(strGmcode);
			objDGMTrans.execute();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
