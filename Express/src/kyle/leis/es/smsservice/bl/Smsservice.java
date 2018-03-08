package kyle.leis.es.smsservice.bl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import kyle.common.util.jlang.CollectionUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtility;
import kyle.common.util.sms.ISMSService;
import kyle.common.util.sms.SMSResult;
import kyle.common.util.sms.SMSServiceFactory;
import kyle.leis.es.smsservice.da.SmsmessageColumns;
import kyle.leis.es.smsservice.da.SmsreceiveruleColumns;
import kyle.leis.es.smsservice.da.SmsreceiveruleCondition;
import kyle.leis.es.smsservice.da.SmsrechargerecordColumns;
import kyle.leis.es.smsservice.da.SmsserviceColumns;
import kyle.leis.es.smsservice.da.SmsserviceCondition;
import kyle.leis.es.smsservice.dax.SMSReturn;
import kyle.leis.es.smsservice.dax.SmsserviceDemand;
import kyle.leis.es.smsservice.tp.DeleteSmsReceiveruleTrans;
import kyle.leis.es.smsservice.tp.SaveRechargeRecordTransaction;
import kyle.leis.es.smsservice.tp.SaveSmsMessageTransaction;
import kyle.leis.es.smsservice.tp.SaveSmsReceiveruleTrans;
import kyle.leis.es.smsservice.tp.SaveSmsserviceTransaction;

public class Smsservice {
	/**
	 * 短信服务设置
	 * @param objSmsserviceColumns
	 * @return
	 * @throws Exception
	 */
	public SmsserviceColumns add(SmsserviceColumns objSmsserviceColumns) throws Exception
	{
		SaveSmsserviceTransaction objSaveSmsserviceTrans = new SaveSmsserviceTransaction();
		objSaveSmsserviceTrans.setParam(objSmsserviceColumns);
		objSaveSmsserviceTrans.execute();
		
		return SmsserviceDemand.querySmsserviceBySsId(objSaveSmsserviceTrans.getNewssId());
	}
	
	/**
	 * 冲值
	 * @param strSsId
	 * @param strAmount
	 * @param strRemark
	 * @param strOpCreateName
	 * @return
	 * @throws Exception
	 */
	public SmsrechargerecordColumns recharge(String strSsId,String strAmount,String strRemark,String strOpCreateName) throws Exception
	{
		SaveRechargeRecordTransaction objSaveRechargeRecordTrans = new SaveRechargeRecordTransaction();
		objSaveRechargeRecordTrans.setPrame(strSsId, strAmount, strRemark, strOpCreateName);
		objSaveRechargeRecordTrans.execute();
		
		return SmsserviceDemand.querySrrByPk(objSaveRechargeRecordTrans.getNewSrrId(),strSsId);
	}
	
	/**
	 * 添加短信发送规则
	 * @param objSmsrColumns
	 * @return
	 * @throws Exception
	 */
	public List addSmsRule(List<SmsreceiveruleColumns> listSmsrColumns, String strCocode) throws Exception
	{
		SaveSmsReceiveruleTrans objSaveSmsReceiveruleTrans = new SaveSmsReceiveruleTrans();
		objSaveSmsReceiveruleTrans.setParam(listSmsrColumns,strCocode);
		objSaveSmsReceiveruleTrans.execute();
		if(CollectionUtility.isNull(listSmsrColumns)) return null;
		SmsreceiveruleCondition objSmsRuleCondition = new SmsreceiveruleCondition();
		objSmsRuleCondition.setCocode(strCocode);
		return SmsserviceDemand.querySmsreceiverule(objSmsRuleCondition);
	}

	/**
	 * 删除短信规则
	 * @param strSnkCode
	 * @param strRecipientOpId
	 * @throws Exception
	 */
	public void deleteSmsRule(String strRecipientOpId ,String strSnkCode) throws Exception
	{
		DeleteSmsReceiveruleTrans objDeleteSmsrrTrans = new DeleteSmsReceiveruleTrans();
		objDeleteSmsrrTrans.setParam(strRecipientOpId, strSnkCode);
		objDeleteSmsrrTrans.execute();
	}
	
	/**
	 * 批量发送短信
	 * @param strCocode
	 * @param strReceiveCocode
	 * @param astrMobile
	 * @param strSMSText
	 * @param strTaskID
	 * @return
	 * @throws Exception
	 */
	public SMSReturn sendBatchSm(String strCocode,String strReceiveCocode,String[] astrMobile,String strSMSText,String strTaskID) throws Exception
	{
		SMSReturn objSMSReturn = new SMSReturn();
		SMSResult objSmsResult = new SMSResult();
		SmsmessageColumns objSmsmessageColumns = null;
		SmsserviceColumns objSmsserviceColumns = null;
		ISMSService objSmsService= SMSServiceFactory.getSMSService();
		
		//短信发送
		SmsserviceCondition objSmsserviceCondition = new SmsserviceCondition();
		objSmsserviceColumns = (SmsserviceColumns)SmsserviceDemand.querySmsservice(objSmsserviceCondition).get(0);
		BigDecimal objUnitprice = new BigDecimal(objSmsserviceColumns.getSsssunitprice());
		//预计费
		BigDecimal objNewBalanceAmount = objUnitprice.multiply(new BigDecimal(astrMobile.length));
		//校验
		PromptUtility objPU = checkSendSMS(objSmsserviceColumns,objNewBalanceAmount);
		if(objPU != null && !objPU.canGo(false))
		{
			objSMSReturn.setSuccessSign(false);
			objSMSReturn.setPromptText(objPU.getDescribtion());
			System.out.println("系统提示："+objSmsResult.getResultText());
			return objSMSReturn;
		}
		//发送
		if(StringUtility.isNull(strTaskID) && astrMobile.length ==1)
			objSmsResult = objSmsService.sendOneSms(astrMobile[0], strSMSText);
		else
			objSmsResult = objSmsService.sendBatchSms(astrMobile, strSMSText, strTaskID);
		if(objSmsResult.isSuceess())
		{
			objNewBalanceAmount = objNewBalanceAmount.multiply(new BigDecimal("-1"));
			objNewBalanceAmount = objNewBalanceAmount.add(new BigDecimal(objSmsserviceColumns.getSsssbalanceamount()));
			objSmsserviceColumns.setSsssbalanceamount(objNewBalanceAmount);
			objSmsserviceColumns.setSssssendtotal(astrMobile.length+Integer.parseInt(objSmsserviceColumns.getSssssendtotal()));
			SaveSmsserviceTransaction objSaveSmsserviceTrans = new SaveSmsserviceTransaction();
			objSaveSmsserviceTrans.setParam(objSmsserviceColumns);
			objSaveSmsserviceTrans.execute();
			
			//保存短信记录
			if(astrMobile !=null && astrMobile.length>0)
			{
				for(int i=0;i<astrMobile.length;i++)
				{
					objSmsmessageColumns = new SmsmessageColumns();
					SmsserviceDemand.setSmsMessageForSendSm(strCocode,strReceiveCocode, astrMobile[i], strSMSText, objSmsmessageColumns);
					SaveSmsMessageTransaction objSaveSmsMessageTrans = new SaveSmsMessageTransaction();
					objSaveSmsMessageTrans.setParam(objSmsmessageColumns);
					objSaveSmsMessageTrans.execute();
				}
			}
			objSMSReturn.setPromptText("短信发送成功！");
			objSMSReturn.setSuccessSign(true);
		}
		else
		{
			objSMSReturn.setPromptText("发送失败，系统或网络出错！");
			System.out.println("系统提示："+objSmsResult.getResultText());
		}
		return objSMSReturn;
	}
	
	/**
	 * 发送单用户短信
	 * @param strCocode
	 * @param strReceiveCocode
	 * @param strMobile
	 * @param strSMSText
	 * @return
	 * @throws Exception
	 */
	public SMSReturn sendOneSm(String strCocode,String strReceiveCocode,String strMobile,String strSMSText)throws Exception
	{
		String[] astrMobile = new String[1];
		astrMobile[0] = strMobile;
		return sendBatchSm(strCocode,strReceiveCocode,astrMobile,strSMSText,null);
	}
	
	/**
	 * 短信发送检查	
	 * @param strCocode
	 * @param strNewBalance
	 * @return
	 * @throws Exception
	 */
	public PromptUtility checkSendSMS(SmsserviceColumns objSmsserviceColumns,BigDecimal strNewBalance) throws Exception
	{
		BigDecimal objOldBalance = new BigDecimal(objSmsserviceColumns.getSsssbalanceamount());
		BigDecimal objNewBalance = strNewBalance;
		if(objOldBalance.compareTo(objNewBalance) <0)
		{
			PromptUtility objPromptUtility = new PromptUtility("E_SMS_001","余额不足。","Smsservice.checkSendSMS");
			return objPromptUtility;
		}
		if(isInvalidDate(objSmsserviceColumns.getSsssstartdate(),objSmsserviceColumns.getSsssenddate()))
		{
			PromptUtility objPromptUtility = new PromptUtility("E_SMS_002","已终止使用时间。","Smsservice.checkSendSMS");
			return objPromptUtility;
		}
		return null;
	}
	
	/**
	 * 检查是否限制接收信息
	 * @param strOperId
	 * @return
	 */
	public boolean checkReceiveType(String strOperId)
	{
		return false;
	}
	
	
	/**
	 * 
	 * 时间判断（判断是否终止使用时间）
	 * false没有终止使用时间
	 * true已终止使用时间
	 */
	public boolean isInvalidDate(String strStartDate,String strEndDate)
	{
		try
		{
			Date now = new Date();
			DateFormat objDF = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			if(now.before(objDF.parse(strEndDate)) && now.after(objDF.parse(strStartDate)))
				return false;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}
}
