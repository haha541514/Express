package kyle.leis.es.smsservice.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;

public class DeleteSmsReceiveruleTrans extends AbstractTransaction {

	private String m_strRecipientOpId;
	private String m_strSnkCode;
	
	public void setParam(String strRecipientOpId, String strSnkCode)
	{
		this.m_strRecipientOpId = strRecipientOpId;
		this.m_strSnkCode = strSnkCode;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (StringUtility.isNull(m_strRecipientOpId) || StringUtility.isNull(m_strSnkCode))return;

		/*if(StringUtility.isNull(m_strSnkCode))
		objSession
				.delete("from TcoSmsreceiverule smsrr where smsrr.comp_id.srrRecipient ='"
						+ m_strRecipientOpId
						+ "'");
		else*/
			objSession
			.delete("from TcoSmsreceiverule smsrr where smsrr.comp_id.srrRecipient ='"
					+ m_strRecipientOpId
					+ "' and smsrr.comp_id.snkCode ='"
					+ m_strSnkCode + "'");
	}

}
