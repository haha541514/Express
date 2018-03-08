package kyle.leis.es.smsservice.tp;

import java.util.List;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.CollectionUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.smsservice.da.SmsreceiveruleColumns;
import kyle.leis.es.smsservice.dax.SmsserviceDemand;
import kyle.leis.hi.TcoSmsreceiverule;
import kyle.leis.hi.TcoSmsreceiverulePK;
import kyle.leis.hi.TdiMessagenoticekind;
import kyle.leis.hi.TdiSmsnoticetimetype;
import net.sf.hibernate.Session;

public class SaveSmsReceiveruleTrans extends AbstractTransaction {

	private String m_strCocode;
	private List<SmsreceiveruleColumns> m_listSmsrRuleColumns;
	
	public void setParam(List<SmsreceiveruleColumns> listSmsrRuleColumns,String strCocode)
	{
		this.m_listSmsrRuleColumns = listSmsrRuleColumns;
		this.m_strCocode = strCocode;
	}
	
	public void transaction(Session objSession) throws Exception {
		if(StringUtility.isNull(m_strCocode)) return;
		
		//������ڣ���ɾ��������(ɾ����Ӧ�����˵����й���)
		/*if(SmsserviceDemand.isExist(m_strSrOperId))
		{
			objSession
			.delete("from TcoSmsreceiverule smsrr where smsrr.comp_id.srrRecipient ='"
					+ m_strSrOperId
					+ "'");
		}*/
		
		if(SmsserviceDemand.isExist(m_strCocode))
		{
			objSession
			.delete("from TcoSmsreceiverule smsrr where smsrr.tdiOperator.tcoCorporation.coCode='"
					+ m_strCocode
					+ "'");
		}
		
		//�������޸Ķ��Ź���
		if(!CollectionUtility.isNull(m_listSmsrRuleColumns))
		for(SmsreceiveruleColumns objSmsrColumns:m_listSmsrRuleColumns)
		{
			TcoSmsreceiverule objTcoSmsreceiverule = new TcoSmsreceiverule();
			TdiSmsnoticetimetype objTdiSmsnoticetimetype = (TdiSmsnoticetimetype)objSession.load(TdiSmsnoticetimetype.class, 
					objSmsrColumns.getSnttsnttcode());
			TdiMessagenoticekind mnk = (TdiMessagenoticekind)objSession.load(TdiMessagenoticekind.class, 
					objSmsrColumns.getMnkmnkcode());
			//���ö��Ž��չ���
			TcoSmsreceiverulePK comp_id = new TcoSmsreceiverulePK();
			comp_id.setSnkCode(objSmsrColumns.getSmsrcomp_idsnkcode());
			comp_id.setSrrRecipient(Long.valueOf(objSmsrColumns.getSropid()));
			comp_id.setMnkCode(objSmsrColumns.getMnkmnkcode());
			
			objTcoSmsreceiverule.setComp_id(comp_id);
			objTcoSmsreceiverule.setTdiSmsnoticetimetype(objTdiSmsnoticetimetype);
			objTcoSmsreceiverule.setTdiMessagenoticekind(mnk);
			objSession.save(objTcoSmsreceiverule);
		}
	}

}
