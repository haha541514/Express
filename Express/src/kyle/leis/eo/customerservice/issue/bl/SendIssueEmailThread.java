package kyle.leis.eo.customerservice.issue.bl;

import java.util.logging.Logger;

import kyle.leis.eo.billing.calculate.feecalculate.blx.AutoFeeCalculateThread;

public class SendIssueEmailThread extends Thread {

	private String m_strIssueId;
	private String m_strCwcode;
	private String m_strIsuCountent;
	private String m_strOperId;
	private static Logger s_objLogger = Logger
			.getLogger(AutoFeeCalculateThread.class.getName());

	public SendIssueEmailThread(String strCwcode, String strIssueId,
			String strIsuCountent, String strOperId) {
		this.m_strIssueId = strIssueId;
		this.m_strCwcode = strCwcode;
		this.m_strIsuCountent = strIsuCountent;
		this.m_strOperId = strOperId;
	}

	public void run() {
		try {
			// ·¢ËÍÍ¨Öª
			IssueForEmail objIssueForEmail = new IssueForEmail();
			objIssueForEmail.sendIssueToEmail(m_strCwcode, m_strIssueId,
					m_strIsuCountent, m_strOperId);
		} catch (Exception e) {
			s_objLogger.warning(e.getMessage());
			e.printStackTrace();
		}

	}
}
