package kyle.leis.es.smsservice.dax;

import java.math.BigDecimal;
import java.util.List;

import kyle.common.util.jlang.CollectionUtility;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.smsservice.da.SmsmessageColumns;
import kyle.leis.es.smsservice.da.SmsmessageCondition;
import kyle.leis.es.smsservice.da.SmsmessageQuery;
import kyle.leis.es.smsservice.da.SmsreceiveruleColumns;
import kyle.leis.es.smsservice.da.SmsreceiveruleCondition;
import kyle.leis.es.smsservice.da.SmsreceiveruleQuery;
import kyle.leis.es.smsservice.da.SmsrechargerecordColumns;
import kyle.leis.es.smsservice.da.SmsrechargerecordCondition;
import kyle.leis.es.smsservice.da.SmsrechargerecordQuery;
import kyle.leis.es.smsservice.da.SmsserviceColumns;
import kyle.leis.es.smsservice.da.SmsserviceCondition;
import kyle.leis.es.smsservice.da.SmsserviceQuery;
import kyle.leis.hi.TcoCustomer;
import kyle.leis.hi.TesSmsmessage;
import kyle.leis.hi.TesSmsservice;
import net.sf.hibernate.Session;

public class SmsserviceDemand {
	
	/**
	 * 构建短信服务设置
	 * @param objTesSmsservice
	 * @param objSmsserviceColumns
	 * @param objSession
	 * @throws Exception
	 */
	public static void setSmsserviceByColumns(TesSmsservice objTesSmsservice,SmsserviceColumns objSmsserviceColumns,Session objSession) throws Exception
	{
		objTesSmsservice.setSsBalanceamount(new BigDecimal(objSmsserviceColumns.getSsssbalanceamount()));
		objTesSmsservice.setSsCreatedate(DateFormatUtility.getStandardDate(objSmsserviceColumns.getSssscreatedate()));
		objTesSmsservice.setSsEnddate(DateFormatUtility.getStandardDate(objSmsserviceColumns.getSsssenddate()));
		objTesSmsservice.setSsModifydate(DateFormatUtility.getSysdate());
		objTesSmsservice.setSsOpNameCreate(objSmsserviceColumns.getSsssopnamecreate());
		objTesSmsservice.setSsOpNameModify(objSmsserviceColumns.getSsssopnamemodify());
		objTesSmsservice.setSsSendtotal(Long.valueOf(objSmsserviceColumns.getSssssendtotal()));
		objTesSmsservice.setSsStartdate(DateFormatUtility.getStandardDate(objSmsserviceColumns.getSsssstartdate()));
		objTesSmsservice.setSsUnitprice(new BigDecimal(objSmsserviceColumns.getSsssunitprice()));
		if(!StringUtility.isNull(objSmsserviceColumns.getCscocode()))
			objTesSmsservice.setTcoCustomer((TcoCustomer)objSession.load(TcoCustomer.class, objSmsserviceColumns.getCscocode()));
	}
	
	/**
	 * 构建短信记录
	 * @param objTesSmsmessage
	 * @param objSmsmessageColumns
	 * @param objSession
	 * @throws Exception
	 */
	public static void setSmsMessageByColumns(TesSmsmessage objTesSmsmessage, SmsmessageColumns objSmsmessageColumns,Session objSession) throws Exception
	{
		objTesSmsmessage.setSmsContent(objSmsmessageColumns.getSmssmscontent());
		objTesSmsmessage.setSmsCreatedate(DateFormatUtility.getStandardDate(objSmsmessageColumns.getSmssmscreatedate()));
		objTesSmsmessage.setSmsMobilenumber(objSmsmessageColumns.getSmssmsmobilenumber());
		objTesSmsmessage.setSmsReceivecocode(objSmsmessageColumns.getSmssmsreceivecocode());
		objTesSmsmessage.setSmsStatus(objSmsmessageColumns.getSmssmsstatus());
		if(!StringUtility.isNull(objSmsmessageColumns.getCscocode()))
			objTesSmsmessage.setTcoCustomer((TcoCustomer)objSession.load(TcoCustomer.class, objSmsmessageColumns.getCscocode()));
	}
	
	/**
	 * 构建发送短信时的保存记录
	 * @param strCocode
	 * @param strReceiveCocode
	 * @param strMobiles
	 * @param strSMSText
	 * @param objTesSmsmessage
	 */
	public static void setSmsMessageForSendSm(String strCocode,String strReceiveCocode,String strMobile,String strSMSText,SmsmessageColumns objSmsmessageColumns)
//	public static void setSmsMessageForSendSm(String strReceiveCocode,String strMobile,String strSMSText,SmsmessageColumns objSmsmessageColumns)
	{
		if(!StringUtility.isNull(strCocode))
			objSmsmessageColumns.setCscocode(strCocode);
		objSmsmessageColumns.setSmssmscontent(strSMSText);
		objSmsmessageColumns.setSmssmscreatedate(DateFormatUtility.getSysdate());
		objSmsmessageColumns.setSmssmsmobilenumber(strMobile);
		objSmsmessageColumns.setSmssmsreceivecocode(strReceiveCocode);
		objSmsmessageColumns.setSmssmsstatus("S");
	}
	
	
	/**
	 * 短信设置查询
	 * @param objSmsserviceCondition
	 * @return
	 * @throws Exception
	 */
	public static List querySmsservice(SmsserviceCondition objSmsserviceCondition) throws Exception
	{
		SmsserviceQuery objSmsserviceQuery = new SmsserviceQuery();
		objSmsserviceQuery.setCondition(objSmsserviceCondition);
		return objSmsserviceQuery.getResults();
		
	}
	
	/**
	 * 根据短信服务编号(ssId)查询短信设置
	 * @param strSsId
	 * @return
	 * @throws Exception
	 */
	public static SmsserviceColumns querySmsserviceBySsId(String strSsId) throws Exception
	{
		SmsserviceCondition objSmsserviceCondition = new SmsserviceCondition();
		objSmsserviceCondition.setSsid(strSsId);
		List objList = querySmsservice(objSmsserviceCondition);
		if(!CollectionUtility.isNull(objList))
			return (SmsserviceColumns)objList.get(0);
		return null;
	}
	
	/**
	 * 根据公司编号查询短信服务信息
	 * @param strCocode
	 * @return
	 * @throws Exception
	 */
	public static SmsserviceColumns querySmsserviceByCocode(String strCocode) throws Exception
	{
		SmsserviceCondition objSmsserviceCondition = new SmsserviceCondition();
		objSmsserviceCondition.setCocode(strCocode);
		List objList = querySmsservice(objSmsserviceCondition);
		if(!CollectionUtility.isNull(objList))
			return (SmsserviceColumns) objList.get(0);
		return null;
	}
	
	/**
	 * 短信冲值查询
	 * @param objSRRCondition
	 * @return
	 * @throws Exception
	 */
	public static List querySmsrechargerecord(SmsrechargerecordCondition objSRRCondition) throws Exception
	{
		SmsrechargerecordQuery objSRRQuery = new SmsrechargerecordQuery();
		objSRRQuery.setCondition(objSRRCondition);
		return objSRRQuery.getResults();
	}
	
	/**
	 * 根据主键查询短信冲值
	 * @param strSrrId
	 * @param strSsId
	 * @return
	 * @throws Exception
	 */
	public static SmsrechargerecordColumns querySrrByPk(String strSrrId,String strSsId) throws Exception
	{
		SmsrechargerecordCondition objSRRCondition = new SmsrechargerecordCondition();
		objSRRCondition.setSrrid(strSrrId);
		objSRRCondition.setSsid(strSsId);
		List objList = querySmsrechargerecord(objSRRCondition);
		if(CollectionUtility.isNull(objList))
			return null;
		return (SmsrechargerecordColumns)objList.get(0);
		
	}
	
	/**
	 * 短信信息、发送统计查询
	 * @param objSmsmessageCondition
	 * @return
	 * @throws Exception
	 */
	public static List querySmsmessage(SmsmessageCondition objSmsmessageCondition) throws Exception
	{
		SmsmessageQuery objSmsmessageQuery = new SmsmessageQuery();
		objSmsmessageQuery.setCondition(objSmsmessageCondition);
		return objSmsmessageQuery.getResults();
	}
	
	/**
	 * 查询短信发送规则
	 * @param objSMSRRCondition
	 * @return
	 * @throws Exception
	 */
	public static List querySmsreceiverule(SmsreceiveruleCondition objSMSRRCondition) throws Exception
	{
		SmsreceiveruleQuery objSMSRRQuery = new SmsreceiveruleQuery();
		objSMSRRQuery.setCondition(objSMSRRCondition);
		return objSMSRRQuery.getResults();
	}
	
	/**
	 * 根据手机号查询短信规则
	 * @param strMobile
	 * @return
	 * @throws Exception
	 */
	public static SmsreceiveruleColumns querySmsreceiveruleByMobile(String strMobile) throws Exception
	{
		SmsreceiveruleCondition objSMSRRCondition = new SmsreceiveruleCondition();
		objSMSRRCondition.setOpmobile(strMobile);
		List objList = querySmsreceiverule(objSMSRRCondition);
		if(!CollectionUtility.isNull(objList))
			return (SmsreceiveruleColumns)objList.get(0);
		return null;
	}
	
	/**
	 * 判断是否存在对应短信规则
	 * @param strSnkCode
	 * @param strSrrOpId
	 * @return true 存在
	 * @return false 不存在
	 * @throws Exception
	 */
	/*public static boolean isExist(String strSrrOpId) throws Exception
	{
		SmsreceiveruleCondition objSMSRRCondition = new SmsreceiveruleCondition();
		objSMSRRCondition.setOpid(strSrrOpId);
		List list = querySmsreceiverule(objSMSRRCondition);
		if(!CollectionUtility.isNull(list))
			return true;
		return false;
	}*/
	
	public static boolean isExist(String strCocode) throws Exception
	{
		SmsreceiveruleCondition objSMSRRCondition = new SmsreceiveruleCondition();
		objSMSRRCondition.setCocode(strCocode);
		List list = querySmsreceiverule(objSMSRRCondition);
		if(!CollectionUtility.isNull(list))
			return true;
		return false;
	}
	
}
