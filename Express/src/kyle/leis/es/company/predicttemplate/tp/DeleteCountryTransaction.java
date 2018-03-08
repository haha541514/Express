package kyle.leis.es.company.predicttemplate.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;

/**
 * @author Synchrn
 * @date:2012-5-30 
 * @version :1.0
 * 
 */
public class DeleteCountryTransaction extends AbstractTransaction{
	private String podmId;

	public void setParam(String podmId) {
		this.podmId = podmId;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (StringUtility.isNull(podmId))
			return;
		//objSession.delete("FROM TcoPreictorderdicmapping t where t.podmId = '"+podmId+"'");
		execute(objSession, "delete from  xsdbuser.t_co_preictorderdicmapping t where t.podm_id = '"+podmId+"'");
	}
}
