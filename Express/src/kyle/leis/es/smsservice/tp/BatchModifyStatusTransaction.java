package kyle.leis.es.smsservice.tp;


import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import net.sf.hibernate.Session;

public class BatchModifyStatusTransaction extends AbstractTransaction {
	private String[] m_astrSmsId;
	private String m_strStatus;
	
	public void setParam(String[] astrSmsId,String strStatus)
	{
		this.m_astrSmsId = astrSmsId;
		this.m_strStatus = strStatus;
	}
	
	public void transaction(Session objSession) throws Exception {
		if(m_astrSmsId.length ==0 || m_astrSmsId == null || StringUtility.isNull(m_strStatus)) return ;
		
		for(String strSmsId:m_astrSmsId)
		{
			String strSql = "UPDATE T_ES_SMSMESSAGE SET SMS_STATUS = '"+ m_strStatus +"' WHERE SMS_ID = "+ strSmsId;
			execute(objSession,strSql);
		}
	}

}
