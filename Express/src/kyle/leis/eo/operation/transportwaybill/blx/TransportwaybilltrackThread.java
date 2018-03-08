package kyle.leis.eo.operation.transportwaybill.blx;

import java.util.List;

import kyle.leis.eo.operation.transportwaybill.da.TransportwaybilltraceColumns;
import kyle.leis.eo.operation.transportwaybill.tp.AddWaybillTrackTrans;

public class TransportwaybilltrackThread extends Thread {
	private String m_strTwbid;
	private List<TransportwaybilltraceColumns> m_listTWBTColumns;
	private String m_strOperId;
	
	public TransportwaybilltrackThread(String strTwbid, 
			List<TransportwaybilltraceColumns> listTWBTColumns,
			String strOperId) {
		m_strTwbid = strTwbid;
		m_listTWBTColumns = listTWBTColumns;
		m_strOperId = strOperId;
	}
	
	public void run() {
		try {
			AddWaybillTrackTrans objAddWTTrans = new AddWaybillTrackTrans();
			objAddWTTrans.setParam(m_strTwbid, 
					m_listTWBTColumns, 
					m_strOperId,
					true);
			objAddWTTrans.execute();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
