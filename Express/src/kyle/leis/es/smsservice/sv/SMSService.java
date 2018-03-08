package kyle.leis.es.smsservice.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.es.smsservice.bl.Smsservice;
import kyle.leis.es.smsservice.da.SmsmessageCondition;
import kyle.leis.es.smsservice.da.SmsreceiveruleColumns;
import kyle.leis.es.smsservice.da.SmsreceiveruleCondition;
import kyle.leis.es.smsservice.da.SmsrechargerecordColumns;
import kyle.leis.es.smsservice.da.SmsrechargerecordCondition;
import kyle.leis.es.smsservice.da.SmsserviceColumns;
import kyle.leis.es.smsservice.da.SmsserviceCondition;
import kyle.leis.es.smsservice.dax.SMSReturn;
import kyle.leis.es.smsservice.dax.SmsserviceDemand;

public class SMSService extends AService {
	
	/**
	 * 短信服务设置
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String addSmsservice(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		
		SmsserviceColumns objSmsserviceColumns = (SmsserviceColumns) objPD.getParameter(0, SmsserviceColumns.class);
		Smsservice objSmsservice = new Smsservice();
		SmsserviceColumns objReturn = objSmsservice.add(objSmsserviceColumns);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objReturn);
		return objEncode.toString();
	}
	
	/**
	 * 查询短信设置
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String querySmsservice(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		
		SmsserviceCondition objSmsserviceCondition = (SmsserviceCondition) objPD.getParameter(0, SmsserviceCondition.class);
		List objList = SmsserviceDemand.querySmsservice(objSmsserviceCondition);
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	/**
	 * 冲值
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String recharge(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,4,this);
		
		String strSsId = (String) objPD.getParameter(0, String.class);
		String strAmount = (String) objPD.getParameter(1, String.class);
		String strRemark = (String) objPD.getParameter(2, String.class);
		String strOpCreateName = (String) objPD.getParameter(3, String.class);
		
		Smsservice objSmsservice = new Smsservice();
		SmsrechargerecordColumns objReturn = objSmsservice.recharge(strSsId, strAmount, strRemark, strOpCreateName);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objReturn);
		return objEncode.toString();
	}
	
	/**
	 * 查询短信冲值记录
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String querySmsrechargerecord(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		
		SmsrechargerecordCondition objSRRCondition = (SmsrechargerecordCondition) objPD.getParameter(0, SmsrechargerecordCondition.class);
		List objList = SmsserviceDemand.querySmsrechargerecord(objSRRCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	
	/**
	 * 查询短信信息、短信发送统计
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String querySmsmessage(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		
		SmsmessageCondition objSmsmessageCondition = (SmsmessageCondition) objPD.getParameter(0, SmsmessageCondition.class);
		List objList = SmsserviceDemand.querySmsmessage(objSmsmessageCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	/**
	 * 添加短信规则
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String addSmsRule(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,2,this);
		
		List listSmsRuleColumns =  objPD.getParameterList(0, SmsreceiveruleColumns.class);
		String strCocode = (String) objPD.getParameter(1, String.class);
		Smsservice objSmsservice = new Smsservice();
		List listReturn = objSmsservice.addSmsRule(listSmsRuleColumns,strCocode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(listReturn);
		return objEncode.toString();
	}
	
	/**
	 * 查询短信规则
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String querySmsRule(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		
		SmsreceiveruleCondition objSmsRuleCondition = (SmsreceiveruleCondition) objPD.getParameter(0, SmsreceiveruleCondition.class);
		List objList = SmsserviceDemand.querySmsreceiverule(objSmsRuleCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	/**
	 * 删除短信规则
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String deleteSmsRule(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,2,this);
		
		String strRecipientOpId = (String) objPD.getParameter(0, String.class);
		String strSnkCode = (String) objPD.getParameter(1, String.class);
		
		Smsservice objSmsservice = new Smsservice();
		objSmsservice.deleteSmsRule(strRecipientOpId, strSnkCode);
		
		return "";
	}
	
	/**
	 * 发送多个用户短信
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String sendBatchSm(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,4,this);
		
		String strCocode = (String) objPD.getParameter(0, String.class);
		String strReceiveCocode = (String) objPD.getParameter(1, String.class);
		String[] astrMobiles = objPD.getParameterArray(2, String.class);
		String strSmText = (String) objPD.getParameter(3, String.class);
		
		Smsservice objSmsservice = new Smsservice();
		SMSReturn objSMSReturn = objSmsservice.sendBatchSm(strCocode,strReceiveCocode,astrMobiles, strSmText, "1");
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objSMSReturn.getSmsReturn());
		return objEncode.toString();
	}
	
	/**
	 * 发送单个用户短信
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String sendOneSm(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,4,this);
		
		String strCocode = (String) objPD.getParameter(0, String.class);
		String strReceiveCocode = (String) objPD.getParameter(1, String.class);
		String strMobile = (String) objPD.getParameter(2, String.class);
		String strSmText = (String) objPD.getParameter(3, String.class);
		
		Smsservice objSmsservice = new Smsservice();
		SMSReturn objSMSReturn = objSmsservice.sendOneSm(strCocode,strReceiveCocode,strMobile,strSmText);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objSMSReturn.getSmsReturn());
		return objEncode.toString();
	}
}
