package kyle.leis.es.smsservice.bl;

import java.util.List;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.DateUtility;
import kyle.common.util.sms.SMSResult;
import kyle.common.util.trigger.bl.ITask;
import kyle.leis.es.smsservice.da.SmsmessageColumns;
import kyle.leis.es.smsservice.da.SmsmessageCondition;
import kyle.leis.es.smsservice.da.SmsmessageQuery;
import kyle.leis.es.smsservice.tp.BatchModifyStatusTransaction;

public class AutoSendSmsTask implements ITask{

	public void execute(String strParameter){
		String strNow = DateFormatUtility.getStandardSysdate();
		String strStartDate = DateUtility.getMoveDate(-1).substring(0,10) + " 00:00:00";
		String strEndEate = strNow.substring(0,10) + " 23:59:59";
		
		SmsmessageQuery objSmsmessageQuery = new SmsmessageQuery();
		SmsmessageCondition objSmsmessageCondition = new SmsmessageCondition();
		objSmsmessageCondition.setSmsstatus("U");
		objSmsmessageCondition.setStartsmscreatedate(strStartDate);
		objSmsmessageCondition.setEndsmscreatedate(strEndEate);
		objSmsmessageQuery.setCondition(objSmsmessageCondition);
		try {
			List<SmsmessageColumns> listMssmessageCol = objSmsmessageQuery.getResults();
			String[] astrSmsId = new String[listMssmessageCol.size()];
			for(int i = 0; i < listMssmessageCol.size(); i++)
			{
				astrSmsId[i] = (listMssmessageCol.get(i)).getSmssmsid();
			}
			//��״̬���óɷ�����
			BatchModifyStatusTransaction objBatchModifyStatusTrans = new BatchModifyStatusTransaction();
			objBatchModifyStatusTrans.setParam(astrSmsId, "I");
			objBatchModifyStatusTrans.execute();
			
			//���Ͷ���
			AutoSendSms objAutoSendSms = new /*AutoSendSms()*/ WeChatMsgSend();
			SMSResult objSmsResult = objAutoSendSms.sendSms(listMssmessageCol);

			// �ع���״̬����Ϊδ����״̬
			if (objSmsResult.isAllRollback()) {
				objBatchModifyStatusTrans = new BatchModifyStatusTransaction();
				objBatchModifyStatusTrans.setParam(astrSmsId, "U");
				objBatchModifyStatusTrans.execute();
			}
			System.out.println("����״̬��"+objSmsResult.isSuceess());
			System.out.println("���ͽ����"+objSmsResult.getResultText());			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
