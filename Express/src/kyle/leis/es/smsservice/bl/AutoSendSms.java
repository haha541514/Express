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
	 * �����Զ����Ͷ��ż�¼
	 * @param strCocode
	 * @param strSnkcode
	 * @param otherParam ������֪ͨʱΪ�����δ��룬������֪ͨʱΪ�������
	 * @throws Exception
	 */
	public void saveAutoSmsmessage(String strCocode,
			String strSnkcode,
			String otherParam) throws Exception
	{
		//����
		String strPiece = null;
		//�����ܶ�
		String strTotalFee = null;
		//��������
		String strContent = null;
		//���Ҷ��Ź���
		SmsreceiveruleCondition objSMSRRCondition = new SmsreceiveruleCondition();
		objSMSRRCondition.setCocode(strCocode);
		objSMSRRCondition.setSnkcode(strSnkcode);
		List<SmsreceiveruleColumns> listSmsreceiveruleCol = (List<SmsreceiveruleColumns>)SmsserviceDemand.querySmsreceiverule(objSMSRRCondition);
		if(CollectionUtility.isNull(listSmsreceiveruleCol)) return;
		SmsreceiveruleColumns objSmsRRColumns = listSmsreceiveruleCol.get(0);
		//��ѯ���
		FinancestatisticsColumns objFSColumns = DunningDemand.load(strCocode);
		BigDecimal objBalanceAmount = new BigDecimal(objFSColumns.getFsfsbalanceamount());
		 
		if(strSnkcode.equals("SNK001"))// SNK001����֪ͨ
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
				strContent = objSmsRRColumns.getSreeeesname() + "���ѣ���˾��" + 
				DateFormatUtility.getStandardSysdate().substring(0,10) +
				"������" + strPiece + 
				"Ʊ�ܼ�" + strTotalFee + 
				"���ʻ����Ϊ" + objBalanceAmount + "���뱣֤�˻�������";
			else
				strContent = objSmsRRColumns.getSreeeesname()+"���ѣ���˾��" + 
				DateFormatUtility.getStandardSysdate().substring(0,10) +
				"������" + strPiece + 
				"Ʊ�ܼ�" + strTotalFee + 
				"��Ƿ���Ϊ" + objBalanceAmount.abs() + "�����ٲ����˷�";
			
		}
		else if(strSnkcode.equals("SNK002"))// SNK002 ����֪ͨ
		{
			if(objBalanceAmount.compareTo(new BigDecimal("0")) >=0)
				strContent = objSmsRRColumns.getSreeeesname() + "���ѣ���˾��" + 
				DateFormatUtility.getStandardSysdate().substring(0,10) + 
				"����" + otherParam + 
				"���ʻ����Ϊ" + objBalanceAmount + "���뱣֤�˻�������";
			else
				strContent = objSmsRRColumns.getSreeeesname()+"���ѣ���˾��" + 
				DateFormatUtility.getStandardSysdate().substring(0,10) + 
				"����" + otherParam + 
				"��Ƿ���Ϊ" + objBalanceAmount.abs() + "�����ٲ����˷�";
		}
		
		for(SmsreceiveruleColumns objSmsreceiveruleColumns:listSmsreceiveruleCol)
		{
			SmsmessageColumns objSmsmessageColumns = new SmsmessageColumns();
			objSmsmessageColumns.setCscocode(objSmsreceiveruleColumns.getSrcococode());//////
			objSmsmessageColumns.setSmssmscontent(strContent);
			objSmsmessageColumns.setSmssmscreatedate(DateFormatUtility.getSysdate());
			//��ý����ֻ���
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
			
			//���淢�Ͷ��ż�¼
			SaveSmsMessageTransaction objSaveSmsMessageTrans = new SaveSmsMessageTransaction();
			objSaveSmsMessageTrans.setParam(objSmsmessageColumns);
			objSaveSmsMessageTrans.execute();
		}
	}
	
	/**
	 * ���Ͷ��ţ������Զ����ͣ�
	 * @param listMssmessageCol
	 * @return
	 * @throws Exception
	 */
	public SMSResult sendSms(List<SmsmessageColumns> listMssmessageCol) throws Exception
	{
		//ʵ�ʷ�������
		int iRealCount = 0;
		SMSResult objSmsResult = new SMSResult();
		ISMSService objSmsService = SMSServiceFactory.getSMSService();
		SmsserviceCondition objSmsserviceCondition = new SmsserviceCondition();
		SmsserviceColumns objSmsserviceColumns = (SmsserviceColumns)SmsserviceDemand.querySmsservice(objSmsserviceCondition).get(0);
		BigDecimal objUnitprice = new BigDecimal(objSmsserviceColumns.getSsssunitprice());
		//Ԥ�Ʒ���
		BigDecimal objNewBalanceAmount = objUnitprice.multiply(new BigDecimal(listMssmessageCol.size()));
		//У����ú��Ƿ���ֹʹ��
		PromptUtility objPU = checkAutoSendSMS(objSmsserviceColumns,objNewBalanceAmount);
		if(objPU != null && !objPU.canGo(false))
		{
			objSmsResult.setSuccessSign(false);
			objSmsResult.setResultText(objPU.getDescribtion());
			objSmsResult.setAllRollbackSign(true);
			return objSmsResult;
		}
		//���ŷ���
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
					//�жϽ�����������
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
					//����
					objSmsResult = objSmsService.sendOneSms(objSmsmessageCol.getSmssmsmobilenumber(), objSmsmessageCol.getSmssmscontent());
					if(objSmsResult.isSuceess())
					{
						//���ͳɹ������޸Ķ��ŷ���״̬
						objSmsmessageCol.setSmssmsstatus("S");
						SaveSmsMessageTransaction objSaveSmsMessageTrans = new SaveSmsMessageTransaction();
						objSaveSmsMessageTrans.setParam(objSmsmessageCol);
						objSaveSmsMessageTrans.execute();
						iRealCount++;
					}
					else
					{
						System.out.println("�ֻ�����Ϊ��"+objSmsmessageCol.getSmssmsmobilenumber()+" ��������Ϊ��"+objSmsmessageCol.getSmssmscontent()+" �Ķ���δ���ͳɹ�!");
						System.out.println("���ŷ���ʧ�ܣ�"+objSmsResult.getResultText());
						
						objSmsmessageCol.setSmssmsstatus("U");
						SaveSmsMessageTransaction objSaveSmsMessageTrans = new SaveSmsMessageTransaction();
						objSaveSmsMessageTrans.setParam(objSmsmessageCol);
						objSaveSmsMessageTrans.execute();							
						
						continue;
					}
					
				}
			}
		}
		//ʵ�ʷ���
		objNewBalanceAmount = objUnitprice.multiply(new BigDecimal(iRealCount));
		objNewBalanceAmount = objNewBalanceAmount.multiply(new BigDecimal("-1"));
		objNewBalanceAmount = objNewBalanceAmount.add(new BigDecimal(objSmsserviceColumns.getSsssbalanceamount()));
		objSmsserviceColumns.setSsssbalanceamount(objNewBalanceAmount);
		objSmsserviceColumns.setSssssendtotal(iRealCount+Integer.parseInt(objSmsserviceColumns.getSssssendtotal()));
		
		//���¶��ŷ����
		SaveSmsserviceTransaction objSaveSmsserviceTrans = new SaveSmsserviceTransaction();
		objSaveSmsserviceTrans.setParam(objSmsserviceColumns);
		objSaveSmsserviceTrans.execute();
		
		return objSmsResult;
	}
	
	/**
	 * ���������ֹʹ��ʱ������
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
			PromptUtility objPromptUtility = new PromptUtility("E_SMS_001","���㡣","AutoSendSms.checkSendSMS");
			return objPromptUtility;
		}
		if(isInvalidDate(objSmsserviceColumns.getSsssstartdate(),objSmsserviceColumns.getSsssenddate()))
		{
			PromptUtility objPromptUtility = new PromptUtility("E_SMS_002","����ֹʹ��ʱ�䡣","AutoSendSms.checkSendSMS");
			return objPromptUtility;
		}
		return null;
	}
	
	/**
	 * ����Ƿ����ƽ�����Ϣ
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
			PromptUtility objPU = new PromptUtility("E_SMS_003","ʱ�����ޣ����ڷ���ʱ����!","AutoSendSms.checkReceiveType");
			return objPU;
		}
		return null;
	}
	
	
	/**
	 * 
	 * ʱ���жϣ��ж��Ƿ���ֹʹ��ʱ�䣩
	 * falseû����ֹʹ��ʱ��
	 * true����ֹʹ��ʱ��
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
