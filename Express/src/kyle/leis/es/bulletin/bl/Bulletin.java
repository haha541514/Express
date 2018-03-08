package kyle.leis.es.bulletin.bl;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.bulletin.da.BulletinColumns;
import kyle.leis.es.bulletin.dax.BulletinDemand;
import kyle.leis.es.bulletin.tp.DeleteBulletinTrans;
import kyle.leis.es.bulletin.tp.SaveBulletinTrans;

public class Bulletin {
	public String save(BulletinColumns objBulletinColumns, 
			String strOperId) throws Exception {
		SaveBulletinTrans objSBTrans = new SaveBulletinTrans();
		objSBTrans.setParam(objBulletinColumns, strOperId);
		objSBTrans.execute();
		if (StringUtility.isNull(objBulletinColumns.getBlblid())) {
			BulletinDemand.sendMessage(objBulletinColumns);
		}
		return objSBTrans.getSavedBlID();
	}
	
	public void delete(String strBlid) throws Exception {
		DeleteBulletinTrans objDBTrans = new DeleteBulletinTrans();
		objDBTrans.setParam(strBlid);
		objDBTrans.execute();
	}
}
