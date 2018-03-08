package kyle.leis.es.smsservice.test;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import kyle.common.connectors.util.Decoder;
import kyle.leis.eo.billing.receivable.da.ReceivableforsmsCondition;
import kyle.leis.eo.billing.receivable.da.ReceivableforsmsQuery;
import kyle.leis.es.smsservice.bl.AutoSendSms;
import kyle.leis.es.smsservice.da.SmsmessageCondition;
import kyle.leis.es.smsservice.da.SmsmessageQuery;
import kyle.leis.es.smsservice.da.SmsreceiveruleCondition;
import kyle.leis.es.smsservice.da.SmsserviceCondition;
import kyle.leis.es.smsservice.da.SmsserviceQuery;
import kyle.leis.es.smsservice.dax.SmsserviceDemand;
import kyle.leis.es.smsservice.sv.SMSService;
import kyle.leis.fs.dictionary.operator.da.OperatorColumns;
import kyle.leis.fs.dictionary.operator.da.OperatorQuery;

public class SMSServiceTest {
	private static SMSService objSMSService = new SMSService();
	public static void main(String [] args) throws Exception
	{
		try
		{
//			System.out.println(addTest());
//			queryBySsIdTest();
			
//			querySmsservice();
			
			querySmsMessage();
			
//			querySmsmessage();
			
//			querySmsReceiverule();
//			System.out.println(isInvalidDate("20:20:20","21:21:21"));
//			receivableSms();
//			getOperator();
//			complete();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static String addTest() throws Exception
	{
		String str = "";
		Decoder objPD = new Decoder(str);
		return objSMSService.addSmsservice(objPD);
	}
	
	
	public static String queryBySsIdTest() throws Exception
	{
		String str = "~`1~`~`2010-11-01 00:00:00~`2010-11-04 23:59:59~`~`~`~`~`~`~`~`~`~@~#";
		Decoder objPD = new Decoder(str);
		return objSMSService.querySmsrechargerecord(objPD);
	}
	
	
	public static String querySmsMessage() throws Exception
	{
//		String str = "~`~`1~`~`2010-01-01 00:00:00~`2010-12-28 23:59:59~`~@~#";
		String str = "~`~@~#";
		Decoder objPD = new Decoder(str);
		return objSMSService.querySmsmessage(objPD);
	}
	
	public static void querySmsservice() throws Exception
	{
		SmsserviceQuery query = new SmsserviceQuery();
		SmsserviceCondition objCondition = new SmsserviceCondition();
//		objCondition.setCocode("172");
		objCondition.setSsid("22");
		query.setCondition(objCondition);
		List objList = query.getResults();
		System.out.println(objList.size());
	}
	
	public static void querySmsmessage() throws Exception
	{
		SmsmessageCondition objSmsmessageCondition = new SmsmessageCondition();
		SmsmessageQuery objSmsmessageQuery = new SmsmessageQuery();
		
		objSmsmessageCondition.setSmsreceivecocode("1");
		objSmsmessageCondition.setStartsmscreatedate("2010-01-01 00:00:00");
		objSmsmessageCondition.setEndsmscreatedate("2010-12-28 23:59:59");
		
		objSmsmessageQuery.setCondition(objSmsmessageCondition);
		List objList = objSmsmessageQuery.getResults();
		System.out.println(objList.size());
	}
	
	
	public static void querySmsReceiverule() throws Exception
	{
		SmsreceiveruleCondition objSMSRRCondition = new SmsreceiveruleCondition();
		objSMSRRCondition.setOpid("1259");
//		objSMSRRCondition.setCocode("338");
		List objList = SmsserviceDemand.querySmsreceiverule(objSMSRRCondition);
		System.out.println(objList.size());
	}
	
	
	/**
	 * 
	 * 时间判断（判断是否终止使用时间）
	 * false没有终止使用时间
	 * true已终止使用时间
	 */
	public static boolean isInvalidDate(String strStartDate,String strEndDate)
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
	
	public static void receivableSms() throws Exception
	{
		ReceivableforsmsQuery objQuery = new ReceivableforsmsQuery();
		ReceivableforsmsCondition objCondition = new ReceivableforsmsCondition();
		objCondition.setBw_code_arrival("1781");
		objQuery.setCondition(objCondition);
		List objList= objQuery.getResults();
		
		System.out.println(objList.size());
	}
	
	public static void getOperator() throws Exception
	{
		OperatorQuery objOperatorQuery = new OperatorQuery();
		/*OperatorCondition objOperatorCon = new OperatorCondition();
		objOperatorCon.setOpid("1064");
		
		objOperatorQuery.setCondition(objOperatorCon);*/
		List listOperator = objOperatorQuery.getResults();
		System.out.println(listOperator.size());
		OperatorColumns objOperator = (OperatorColumns)listOperator.get(0);
	}
	
	public static void complete() throws Exception
	{
		//保存待发送的短信记录
		AutoSendSms objAutoSendSms = new AutoSendSms();
		objAutoSendSms.saveAutoSmsmessage("1", "SNK001", "4422");
	}
	
}
