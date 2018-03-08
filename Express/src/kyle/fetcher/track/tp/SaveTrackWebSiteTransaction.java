package kyle.fetcher.track.tp;

import net.sf.hibernate.Session;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.eo.customerservice.track.tp.SaveBatchTrackTransaction;

/**
 * User: Kyle 
 * Date: 2009-2-9 
 * Time: 16:07:52 
 */
public class SaveTrackWebSiteTransaction extends AbstractTransaction {
	private String m_strCwCode;
	private String m_strWpacode;

	public void setTrackWebSite(String strCwCode, 
			String strWpacode) {
		m_strCwCode = strCwCode;
		m_strWpacode = strWpacode;
	}

	public void transaction(Session objSession) throws Exception {
		SaveBatchTrackTransaction objSBTTrans = new SaveBatchTrackTransaction();
		objSBTTrans.setParam(m_strCwCode, m_strWpacode);
		objSBTTrans.transaction(objSession);
	}
}
