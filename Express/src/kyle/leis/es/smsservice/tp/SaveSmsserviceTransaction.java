package kyle.leis.es.smsservice.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.smsservice.da.SmsserviceColumns;
import kyle.leis.es.smsservice.dax.SmsserviceDemand;
import kyle.leis.hi.TesSmsservice;
import net.sf.hibernate.Session;

public class SaveSmsserviceTransaction extends AbstractTransaction {
	private SmsserviceColumns m_objSmsserviceColumns;
	private String m_strNewssId;
	public String getNewssId()
	{
		return this.m_strNewssId;
	}
	
	public void setParam(SmsserviceColumns objSmsserviceColumns)
	{
		this.m_objSmsserviceColumns = objSmsserviceColumns;
	}
	
	public void transaction(Session objSession) throws Exception {
		if(m_objSmsserviceColumns == null) return;
		TesSmsservice objTesSmsservice;
		if(StringUtility.isNull(m_objSmsserviceColumns.getSsssid()))
			objTesSmsservice = new TesSmsservice();
		else
			objTesSmsservice = (TesSmsservice)objSession.load(TesSmsservice.class, Long.valueOf(m_objSmsserviceColumns.getSsssid()));
		
		SmsserviceDemand.setSmsserviceByColumns(objTesSmsservice, m_objSmsserviceColumns, objSession);
		objSession.save(objTesSmsservice);
		this.m_strNewssId = String.valueOf(objTesSmsservice.getSsId());
	}

}
