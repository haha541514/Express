package kyle.leis.es.smsservice.bl;

import java.util.List;

import kyle.common.util.jlang.CollectionUtility;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.smsservice.da.SmsmessageColumns;
import kyle.leis.es.smsservice.da.SmsreceiveruleColumns;
import kyle.leis.es.smsservice.da.SmsreceiveruleCondition;
import kyle.leis.es.smsservice.dax.SmsserviceDemand;
import kyle.leis.es.smsservice.tp.SaveSmsMessageTransaction;

/**
 * ����΢����Ϣ
 * 
 * @author Administrator
 * 
 */
public class WeChatMsgSaver implements SmsSaver {

	public void saveAutoSmsmessage(String coCode, Sms sms) throws Exception {
		if (StringUtility.isNull(coCode)
				|| StringUtility.isNull(sms.getSnkCode())) {
			return;
		}
		List<?> smsreceiverules = querySmsReveiveRules(coCode, sms.getSnkCode());
		if (CollectionUtility.isNull(smsreceiverules))
			return;
		if (isAllNull(smsreceiverules))
			return;
		saveSms(smsreceiverules, sms.getContent());
	}

	/**
	 * ���Ҷ��Ź���
	 * @param strCocode
	 * @param snkCode
	 * @return
	 * @throws Exception
	 */
	private List<?> querySmsReveiveRules(String strCocode, String snkCode)
			throws Exception {
		SmsreceiveruleCondition objSMSRRCondition = new SmsreceiveruleCondition();
		objSMSRRCondition.setCocode(strCocode);
		objSMSRRCondition.setSnkcode(snkCode);
		List<?> listSmsreceiveruleCol = SmsserviceDemand
				.querySmsreceiverule(objSMSRRCondition);
		return listSmsreceiveruleCol;
	}
	
	/**
	 * �Ƿ�������ϵ�˵���ϵ��ʽ��Ϊ��
	 * @param smsreceiverules
	 * @return
	 */
	private boolean isAllNull(List<?> smsreceiverules) {
		boolean isAllNull = true;
		for (Object object : smsreceiverules) {
			SmsreceiveruleColumns objSmsreceiveruleColumns = (SmsreceiveruleColumns) object;
			if (!StringUtility.isNull(objSmsreceiveruleColumns.getSropmobile())) {
				isAllNull = false;
				break;
			}
		}
		return isAllNull;
	}
	
	/**
	 * ������Ϣ
	 * @param smsreceiverules
	 * @param smsContent
	 * @throws Exception
	 */
	private void saveSms(List<?> smsreceiverules, String smsContent)
			throws Exception {
		for (Object object : smsreceiverules) {
			SmsreceiveruleColumns objSmsreceiveruleColumns = (SmsreceiveruleColumns) object;
			if (StringUtility.isNull(objSmsreceiveruleColumns.getSropmobile()))
				continue;
			SmsmessageColumns objSmsmessageColumns = assembleSmsMessage(smsContent, objSmsreceiveruleColumns);
			// ���淢�Ͷ��ż�¼
			SaveSmsMessageTransaction objSaveSmsMessageTrans = new SaveSmsMessageTransaction();
			objSaveSmsMessageTrans.setParam(objSmsmessageColumns);
			objSaveSmsMessageTrans.execute();
		}
	}

	/**
	 * assemble smsMessage
	 * @param smsContent
	 * @param objSmsreceiveruleColumns
	 * @return
	 */
	private SmsmessageColumns assembleSmsMessage(String smsContent,
			SmsreceiveruleColumns objSmsreceiveruleColumns) {
		SmsmessageColumns objSmsmessageColumns = new SmsmessageColumns();
		objSmsmessageColumns.setCscocode(objSmsreceiveruleColumns
				.getSrcococode());// ////
		objSmsmessageColumns.setSmssmscontent(smsContent);
		objSmsmessageColumns.setSmssmscreatedate(DateFormatUtility
				.getSysdate());
		objSmsmessageColumns.setSmssmsmobilenumber(objSmsreceiveruleColumns
				.getSropmobile());
		objSmsmessageColumns
				.setSmssmsreceivecocode(objSmsreceiveruleColumns
						.getSrcococode());// ////
		objSmsmessageColumns.setSmssmsstatus("U");
		return objSmsmessageColumns;
	}
}
