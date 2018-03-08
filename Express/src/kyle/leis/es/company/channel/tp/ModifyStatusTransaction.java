package kyle.leis.es.company.channel.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.leis.fs.dictionary.dictionarys.da.TdiChannelstatusDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.hi.TchnChannel;
import kyle.leis.hi.TdiChannelstatus;
import kyle.leis.hi.TdiOperator;

public class ModifyStatusTransaction extends AbstractTransaction {
	private String m_strChncode;
	private String m_strCscode;
	private String m_strOperId;
	
	public void setParam(String strChncode, 
			String strCscode, 
			String strOperId) throws Exception {
		m_strChncode = strChncode;
		m_strCscode = strCscode;
		m_strOperId = strOperId;
	}
	
	public void transaction(Session objSession) throws Exception {
		TchnChannel objTchnChannel = (TchnChannel)objSession.load(TchnChannel.class, 
				m_strChncode);
		TdiChannelstatus objTCS = TdiChannelstatusDC.loadByKey(m_strCscode);
		TdiOperator objTdiOperator = TdiOperatorDC.loadByKey(m_strOperId);
		
		objTchnChannel.setTdiOperatorByChnOpIdModify(objTdiOperator);
		objTchnChannel.setTdiChannelstatus(objTCS);
		objTchnChannel.setChnModifydate(DateFormatUtility.getSysdate());
		
		objSession.save(objTchnChannel);
	}
	
}
