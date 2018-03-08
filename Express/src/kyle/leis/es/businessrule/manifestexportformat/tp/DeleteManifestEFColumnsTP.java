package kyle.leis.es.businessrule.manifestexportformat.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.hi.TbrManifestefcolumnPK;

public class DeleteManifestEFColumnsTP extends AbstractTransaction {
      private  TbrManifestefcolumnPK pk;
      private String result;
	public String getResult() {
		return result;
	}

	public void setPk(TbrManifestefcolumnPK pk) {
		this.pk = pk;
	}
	public void transaction(Session objSession) throws Exception {
		if(pk.getMefcId()==null&&pk.getMefCode()==null){
			result = null;
			return;
		}
		else if(pk.getMefCode()!=null&&pk.getMefcId()==null){
			String delSql = "delete from t_br_manifestefcolumns where mef_code="+pk.getMefCode();
			execute(objSession,delSql);
			result = "删除成功";
			return;
		}
		String delSql = "delete from t_br_manifestefcolumns where mef_code="+pk.getMefCode()+" and mefc_id="+pk.getMefcId();
		execute(objSession,delSql);
		result = "删除成功";
	}
}
