package kyle.leis.es.bulletin.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import net.sf.hibernate.Session;

public class DelWechatItemTrans extends AbstractTransaction {
	private String cwid;
	private String blId;

	public DelWechatItemTrans(String cwid, String blId) {
		this.cwid = cwid;
		this.blId = blId;
	}
	public void transaction(Session objSession) throws Exception {
		String sql = "delete from t_es_wechatitem t where t.WCI_ID = " + cwid
				+ " and t.BL_ID = " + blId;
		execute(objSession, sql);
	}

}
