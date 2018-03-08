package kyle.leis.eo.customerservice.track.bl;

import kyle.leis.eo.customerservice.track.tp.AddSingleTrackTrans;

public class TrackThread extends Thread {
	
	private String m_strCwcode; 
	private String m_strDtcode;
	private String m_strWbtscode;
	private String m_strOperId;
	private String m_strOccurDate;	
	
	public TrackThread(String strCwcode, 
			String strDtcode, 
			String strWbtscode,
			String strOperId,
			String strOccurDate) {
		m_strCwcode = strCwcode;
		m_strDtcode = strDtcode;
		m_strWbtscode = strWbtscode;
		m_strOperId = strOperId;
		m_strOccurDate = strOccurDate;
	}
	
	public void addSingleTrack() throws Exception {

	}	
	
	public void run() {
		try {
			AddSingleTrackTrans objASTTrans = new AddSingleTrackTrans();
			objASTTrans.setParam(m_strCwcode, 
					m_strDtcode, 
					m_strWbtscode, 
					m_strOperId, 
					m_strOccurDate);
			objASTTrans.execute();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
