package kyle.leis.fs.corewaybillauditlog.bl;

import kyle.leis.fs.corewaybillauditlog.tp.AddAuditlogTransaction;
import kyle.leis.fs.corewaybillauditlog.tp.DeleteAuditlogTransaction;

public class Auditlog {
	public void add(String strCwcode, 
			String strFaltcode, 
			String strOperId) throws Exception {
		AddAuditlogTransaction objAALTrans = new AddAuditlogTransaction();
		objAALTrans.setParam(strCwcode, strFaltcode, strOperId);
		objAALTrans.execute();
	}
	
	public void delete(String strCwcode, 
			String strFaltcode) throws Exception {
		DeleteAuditlogTransaction objDALTrans = new DeleteAuditlogTransaction();
		objDALTrans.setParam(strCwcode, strFaltcode);
		objDALTrans.execute();
	}	
	
}
