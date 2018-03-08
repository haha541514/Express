package kyle.leis.es.smsservice.tp;

import java.util.List;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.smsservice.da.SmsidseqColumns;
import kyle.leis.es.smsservice.da.SmsidseqQuery;
import kyle.leis.es.smsservice.da.SmsmessageColumns;
import kyle.leis.es.smsservice.dax.SmsserviceDemand;
import kyle.leis.hi.TesSmsmessage;
import net.sf.hibernate.Session;

public class SaveSmsMessageTransaction extends AbstractTransaction {

	private SmsmessageColumns m_objSmsmessageColumns;
	public void setParam(SmsmessageColumns objSmsmessageColumns)
	{
		this.m_objSmsmessageColumns = objSmsmessageColumns;
	}
	
	public void transaction(Session objSession) throws Exception {
		if(m_objSmsmessageColumns == null) return;
		TesSmsmessage objTesSmsmessage;
		if(!StringUtility.isNull(m_objSmsmessageColumns.getSmssmsid()))
			objTesSmsmessage = (TesSmsmessage)objSession.load(TesSmsmessage.class, Long.parseLong(m_objSmsmessageColumns.getSmssmsid()));
		else
		{
			objTesSmsmessage = new TesSmsmessage();
			SmsidseqQuery objSmsidseqQuery = new SmsidseqQuery();
			List list = objSmsidseqQuery.getResults();
			objTesSmsmessage.setSmsId(Long.valueOf(((SmsidseqColumns)list.get(0)).getSmsidseq()));
		}
		SmsserviceDemand.setSmsMessageByColumns(objTesSmsmessage, m_objSmsmessageColumns,objSession);
		objSession.save(objTesSmsmessage); 
	}
}
