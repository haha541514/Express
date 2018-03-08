package kyle.leis.es.smsservice.bl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import kyle.common.util.jlang.CollectionUtility;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.prompt.PromptUtility;
import kyle.common.util.sms.ISMSService;
import kyle.common.util.sms.SMSResult;
import kyle.common.util.sms.SMSServiceFactory;
import kyle.leis.eo.billing.receivable.da.ReceivableforsmsColumns;
import kyle.leis.eo.billing.receivable.da.ReceivableforsmsCondition;
import kyle.leis.eo.billing.receivable.da.ReceivableforsmsQuery;
import kyle.leis.eo.finance.dunning.da.FinancestatisticsColumns;
import kyle.leis.eo.finance.dunning.dax.DunningDemand;
import kyle.leis.eo.operation.housewaybill.da.StatisticbybwcodeColumns;
import kyle.leis.eo.operation.housewaybill.da.StatisticbybwcodeCondition;
import kyle.leis.eo.operation.housewaybill.da.StatisticbybwcodeQuery;
import kyle.leis.es.smsservice.da.SmsmessageColumns;
import kyle.leis.es.smsservice.da.SmsreceiveruleColumns;
import kyle.leis.es.smsservice.da.SmsreceiveruleCondition;
import kyle.leis.es.smsservice.da.SmsreceiveruleQuery;
import kyle.leis.es.smsservice.da.SmsserviceColumns;
import kyle.leis.es.smsservice.da.SmsserviceCondition;
import kyle.leis.es.smsservice.dax.SmsserviceDemand;
import kyle.leis.es.smsservice.tp.SaveSmsMessageTransaction;
import kyle.leis.es.smsservice.tp.SaveSmsserviceTransaction;

public class AutoSendSms {
	
	/**
	 * 保存自动发送短信记录
	 * @param strCocode
	 * @param strSnkcode
	 * @param otherParam 当到货通知时为：批次代码，当交款通知时为：交款额
	 * @throws Exception
	 */
	public void saveAutoSmsmessage(String strCocode,
			String strSnkcode,
			String otherParam) throws Exception
	{
		//件数
		String strPiece = null;
		//费用总额
		String strTotalFee = null;
		//短信内容
		String strContent = null;
		//查找短信规则
		SmsreceiveruleCondition objSMSRRCondition = new SmsreceiveruleCondition();
		objSMSRRCondition.setCocode(strCocode);
		objSMSRRCondition.setSnkcode(strSnkcode);
		List<SmsreceiveruleColumns> listSmsreceiveruleCol = (List<SmsreceiveruleColumns>)SmsserviceDemand.querySmsreceiverule(objSMSRRCondition);
		if(CollectionUtility.isNull(listSmsreceiveruleCol)) return;
		SmsreceiveruleColumns objSmsRRColumns = listSmsreceiveruleCol.get(0);
		//查询余额
		FinancestatisticsColumns objFSColumns = DunningDemand.load(strCocode);
		BigDecimal objBalanceAmount = new BigDecimal(objFSColumns.getFsfsbalanceamount());
		 
		if(strSnkcode.equals("SNK001"))// SNK001到货通知
		{
			StatisticbybwcodeQuery objSBBWCQuery = new StatisticbybwcodeQuery();
			StatisticbybwcodeCondition objSBBWCCondition = new StatisticbybwcodeCondition();
			objSBBWCCondition.setBw_code_arrival(otherParam);
			objSBBWCCondition.setCo_code_customer(strCocode);
			objSBBWCQuery.setCondition(objSBBWCCondition);
			List objList = objSBBWCQuery.getResults();
			StatisticbybwcodeColumns objSBBWC = (StatisticbybwcodeColumns)objList.get(0);
			strPiece =objSBBWC.getPieces();
			
			ReceivableforsmsQuery objReceivableforsmsQuery = new ReceivableforsmsQuery();
			ReceivableforsmsCondition objReceivableforsmsCondition = new ReceivableforsmsCondition();
			objReceivableforsmsCondition.setBw_code_arrival(otherParam);
			objReceivableforsmsQuery.setCondition(objReceivableforsmsCondition);
			List objReceivable = objReceivableforsmsQuery.getResults();
			ReceivableforsmsColumns objReceivableforsmsColumns = (ReceivableforsmsColumns)objReceivable.get(0);
			strTotalFee = objReceivableforsmsColumns.getRvrv_actualtotal();
			
			if(objBalanceAmount.compareTo(new BigDecimal("0")) >= 0)
				strContent = objSmsRRColumns.getSreeeesname() + "提醒：贵司于" + 
				DateFormatUtility.getStandardSysdate().substring(0,10) +
				"共交货" + strPiece + 
				"票总计" + strTotalFee + 
				"，帐户余额为" + objBalanceAmount + "，请保证账户余额充足";
			else
				strContent = objSmsRRColumns.getSreeeesname()+"提醒：贵司于" + 
				DateFormatUtility.getStandardSysdate().substring(0,10) +
				"共交货" + strPiece + 
				"票总计" + strTotalFee + 
				"，欠款额为" + objBalanceAmount.abs() + "，请速补足运费";
			
		}
		else if(strSnkcode.equals("SNK002"))// SNK002 交款通知
		{
			if(objBalanceAmount.compareTo(new BigDecimal("0")) >=0)
				strContent = objSmsRRColumns.getSreeeesname() + "提醒：贵司于" + 
				DateFormatUtility.getStandardSysdate().substring(0,10) + 
				"交款" + otherParam + 
				"，帐户余额为" + objBalanceAmount + "，请保证账户余额充足";
			else
				strContent = objSmsRRColumns.getSreeeesname()+"提醒：贵司于" + 
				DateFormatUtility.getStandardSysdate().substring(0,10) + 
				"交款" + otherParam + 
				"，欠款额为" + objBalanceAmount.abs() + "，请速补足运费";
		}
		
		for(SmsreceiveruleColumns objSmsreceiveruleColumns:listSmsreceiveruleCol)
		{
			SmsmessageColumns objSmsmessageColumns = new SmsmessageColumns();
			objSmsmessageColumns.setCscocode(objSmsreceiveruleColumns.getSrcococode());//////
			objSmsmessageColumns.setSmssmscontent(strContent);
			objSmsmessageColumns.setSmssmscreatedate(DateFormatUtility.getSysdate());
			//获得接收手机号
			/*OperatorQuery objOperatorQuery = new OperatorQuery();
			OperatorCondition objOperatorCon = new OperatorCondition();
			objOperatorCon.setOpid(objSmsreceiveruleColumns.getSropid());
			objOperatorQuery.setCondition(objOperatorCon);
			List listOperator = objOperatorQuery.getResults();
			OperatorColumns objOperator = (OperatorColumns)listOperator.get(0);
			objSmsmessageColumns.setSmssmsmobilenumber(objOperator.getOpmobile());*/
			objSmsmessageColumns.setSmssmsmobilenumber(objSmsreceiveruleColumns.getSropmobile());
			objSmsmessageColumns.setSmssmsreceivecocode(objSmsreceiveruleColumns.getSrcococode());//////
			objSmsmessageColumns.setSmssmsstatus("U");
			
			//保存发送短信记录
			SaveSmsMessageTransaction objSaveSmsMessageTrans = new SaveSmsMessageTransaction();
			objSaveSmsMessageTrans.setParam(objSmsmessageColumns);
			objSaveSmsMessageTrans.execute();
		}
	}
	
	/**
	 * 发送短信（用于自动发送）
	 * @param listMssmessageCol
	 * @return
	 * @throws Exception
	 */
	public SMSResult sendSms(List<SmsmessageColumns> listMssmessageCol) throws Exception
	{
		//实际发送条数
		int iRealCount = 0;
		SMSResult objSmsResult = new SMSResult();
		ISMSService objSmsService = SMSServiceFactory.getSMSService();
		SmsserviceCondition objSmsserviceCondition = new SmsserviceCondition();
		SmsserviceColumns objSmsserviceColumns = (SmsserviceColumns)SmsserviceDemand.querySmsservice(objSmsserviceCondition).get(0);
		BigDecimal objUnitprice = new BigDecimal(objSmsserviceColumns.getSsssunitprice());
		//预计费用
		BigDecimal objNewBalanceAmount = objUnitprice.multiply(new BigDecimal(listMssmessageCol.size()));
		//校验费用和是否终止使用
		PromptUtility objPU = checkAutoSendSMS(objSmsserviceColumns,objNewBalanceAmount);
		if(objPU != null && !objPU.canGo(false))
		{
			objSmsResult.setSuccessSign(false);
			objSmsResult.setResultText(objPU.getDescribtion());
			objSmsResult.setAllRollbackSign(true);
			return objSmsResult;
		}
		//短信发送
		for(SmsmessageColumns objSmsmessageCol:listMssmessageCol)
		{
			
			SmsreceiveruleCondition objSMSRRCondition = new SmsreceiveruleCondition();
			objSMSRRCondition.setCocode(objSmsmessageCol.getCscocode());
			SmsreceiveruleQuery objSmsrecieveruleQuery = new SmsreceiveruleQuery();
			objSmsrecieveruleQuery.setCondition(objSMSRRCondition);
			List<SmsreceiveruleColumns> listSmsreceiverule = objSmsrecieveruleQuery.getResults();
			
						
			for(SmsreceiveruleColumns objSmsreceiverule:listSmsreceiverule)
			{
				if(objSmsmessageCol.getSmssmsmobilenumber().equals(objSmsreceiverule.getSropmobile()))
				{
					//判断接收日期限制
					if(objSmsreceiverule.getSnttsnttcode().equals("SNTT2"))
						objPU = checkReceiveType(objSmsreceiverule);
					if(objPU != null && !objPU.canGo(false))
					{
						objSmsResult.setSuccessSign(false);
						objSmsResult.setResultText(objPU.getDescribtion());
						
						objSmsmessageCol.setSmssmsstatus("U");
						SaveSmsMessageTransaction objSaveSmsMessageTrans = new SaveSmsMessageTransaction();
						objSaveSmsMessageTrans.setParam(objSmsmessageCol);
						objSaveSmsMessageTrans.execute();						
						
						continue;
					}
					//发送
					objSmsResult = objSmsService.sendOneSms(objSmsmessageCol.getSmssmsmobilenumber(), objSmsmessageCol.getSmssmscontent());
					if(objSmsResult.isSuceess())
					{
						//发送成功，则修改短信发送状态
						objSmsmessageCol.setSmssmsstatus("S");
						SaveSmsMessageTransaction objSaveSmsMessageTrans = new SaveSmsMessageTransaction();
						objSaveSmsMessageTrans.setParam(objSmsmessageCol);
						objSaveSmsMessageTrans.execute();
						iRealCount++;
					}
					else
					{
						System.out.println("手机号码为："+objSmsmessageCol.getSmssmsmobilenumber()+" 短信内容为："+objSmsmessageCol.getSmssmscontent()+" 的短信未发送成功!");
						System.out.println("短信发送失败："+objSmsResult.getResultText());
						
						objSmsmessageCol.setSmssmsstatus("U");
						SaveSmsMessageTransaction objSaveSmsMessageTrans = new SaveSmsMessageTransaction();
						objSaveSmsMessageTrans.setParam(objSmsmessageCol);
						objSaveSmsMessageTrans.execute();							
						
						continue;
					}
					
				}
			}
		}
		//实际费用
		objNewBalanceAmount = objUnitprice.multiply(new BigDecimal(iRealCount));
		objNewBalanceAmount = objNewBalanceAmount.multiply(new BigDecimal("-1"));
		objNewBalanceAmount = objNewBalanceAmount.add(new BigDecimal(objSmsserviceColumns.getSsssbalanceamount()));
		objSmsserviceColumns.setSsssbalanceamount(objNewBalanceAmount);
		objSmsserviceColumns.setSssssendtotal(iRealCount+Integer.parseInt(objSmsserviceColumns.getSssssendtotal()));
		
		//更新短信服务表
		SaveSmsserviceTransaction objSaveSmsserviceTrans = new SaveSmsserviceTransaction();
		objSaveSmsserviceTrans.setParam(objSmsserviceColumns);
		objSaveSmsserviceTrans.execute();
		
		return objSmsResult;
	}
	
	/**
	 * 检查金额不足和终止使用时间问题
	 * @param objSmsserviceColumns
	 * @param objNewBalanceAmount
	 * @return
	 */
	public PromptUtility checkAutoSendSMS(SmsserviceColumns objSmsserviceColumns,BigDecimal objNewBalanceAmount)
	{
		BigDecimal objOldBalance = new BigDecimal(objSmsserviceColumns.getSsssbalanceamount());
		BigDecimal objNewBalance = objNewBalanceAmount;
		if(objOldBalance.compareTo(objNewBalance) <0)
		{
			PromptUtility objPromptUtility = new PromptUtility("E_SMS_001","余额不足。","AutoSendSms.checkSendSMS");
			return objPromptUtility;
		}
		if(isInvalidDate(objSmsserviceColumns.getSsssstartdate(),objSmsserviceColumns.getSsssenddate()))
		{
			PromptUtility objPromptUtility = new PromptUtility("E_SMS_002","已终止使用时间。","AutoSendSms.checkSendSMS");
			return objPromptUtility;
		}
		return null;
	}
	
	/**
	 * 检查是否限制接收信息
	 * @param strOperId
	 * @return
	 */
	public PromptUtility checkReceiveType(SmsreceiveruleColumns objSmsreceiveruleColumns) throws Exception
	{
		String strNow = DateFormatUtility.getStandardSysdate();
		String strStartTime = strNow.substring(0,10)+" "+objSmsreceiveruleColumns.getSnttsnttstarttime();
		String strEndTime = strNow.substring(0,10)+" "+objSmsreceiveruleColumns.getSnttsnttendtime();
		if(isInvalidDate(strStartTime,strEndTime))
		{
			PromptUtility objPU = new PromptUtility("E_SMS_003","时间受限，不在发送时间内!","AutoSendSms.checkReceiveType");
			return objPU;
		}
		return null;
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
