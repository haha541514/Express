package kyle.leis.es.smsservice.bl;

import java.math.BigDecimal;
import java.util.List;

import kyle.common.util.jlang.CollectionUtility;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtility;
import kyle.common.util.sms.ISMSService;
import kyle.common.util.sms.SMSResult;
import kyle.common.util.sms.SMSServiceFactory;
import kyle.leis.eo.finance.dunning.da.FinancestatisticsColumns;
import kyle.leis.eo.finance.dunning.dax.DunningDemand;
import kyle.leis.eo.operation.batchwaybill.da.BatchwaybillColumns;
import kyle.leis.eo.operation.batchwaybill.da.BatchwaybillQuery;
import kyle.leis.eo.operation.housewaybill.da.StatisticbybwcodeColumns;
import kyle.leis.eo.operation.housewaybill.da.StatisticbybwcodeCondition;
import kyle.leis.eo.operation.housewaybill.da.StatisticbybwcodeQuery;
import kyle.leis.eo.operation.housewaybill.da.SumchargeweightColumns;
import kyle.leis.eo.operation.housewaybill.da.SumchargeweightQuery;
import kyle.leis.es.smsservice.da.SmsmessageColumns;
import kyle.leis.es.smsservice.da.SmsreceiveruleColumns;
import kyle.leis.es.smsservice.da.SmsreceiveruleCondition;
import kyle.leis.es.smsservice.da.SmsreceiveruleQuery;
import kyle.leis.es.smsservice.dax.SmsserviceDemand;
import kyle.leis.es.smsservice.tp.SaveSmsMessageTransaction;

public class WeChatMsgSend extends AutoSendSms {
	@Override
	public void saveAutoSmsmessage(String strCocode, String strSnkcode,
			String otherParam) throws Exception {

		// 件数
		String strPiece = null;
		String totalWeight = null;// 总重量
		String labelCode = null;
		// 短信内容
		StringBuilder strContent = new StringBuilder();
		// 查找短信规则
		SmsreceiveruleCondition objSMSRRCondition = new SmsreceiveruleCondition();
		objSMSRRCondition.setCocode(strCocode);
		objSMSRRCondition.setSnkcode(strSnkcode);
		List<?> listSmsreceiveruleCol = SmsserviceDemand.querySmsreceiverule(objSMSRRCondition);
		if (CollectionUtility.isNull(listSmsreceiveruleCol))
			return;
		
		boolean isAllwechatnonull = true;
		for (Object object : listSmsreceiveruleCol) {
			SmsreceiveruleColumns objSmsreceiveruleColumns = (SmsreceiveruleColumns) object;
			if (!StringUtility.isNull(objSmsreceiveruleColumns.getSropmobile())) {
				isAllwechatnonull = false;
				break;
			}
		}		
		if (isAllwechatnonull) return;
		
		SmsreceiveruleColumns objSmsRRColumns = (SmsreceiveruleColumns) listSmsreceiveruleCol
				.get(0);
		// 查询余额
		FinancestatisticsColumns objFSColumns = DunningDemand.load(strCocode);
		BigDecimal objBalanceAmount = new BigDecimal(objFSColumns
				.getFsfsbalanceamount());

		if (strSnkcode.equals("SNK001")) {// SNK001到货通知
			// 件数
			StatisticbybwcodeQuery objSBBWCQuery = new StatisticbybwcodeQuery();
			StatisticbybwcodeCondition objSBBWCCondition = new StatisticbybwcodeCondition();
			objSBBWCCondition.setBw_code_arrival(otherParam);
			objSBBWCCondition.setCo_code_customer(strCocode);
			objSBBWCQuery.setCondition(objSBBWCCondition);
			List<?> objList = objSBBWCQuery.getResults();
			StatisticbybwcodeColumns objSBBWC = (StatisticbybwcodeColumns) objList
					.get(0);
			strPiece = objSBBWC.getPieces();
			// 总费用
			/*
			ReceivableforsmsQuery objReceivableforsmsQuery = new ReceivableforsmsQuery();
			ReceivableforsmsCondition objReceivableforsmsCondition = new ReceivableforsmsCondition();
			objReceivableforsmsCondition.setBw_code_arrival(otherParam);
			objReceivableforsmsQuery.setCondition(objReceivableforsmsCondition);
			List<?> objReceivable = objReceivableforsmsQuery.getResults();
			ReceivableforsmsColumns objReceivableforsmsColumns = (ReceivableforsmsColumns) objReceivable
					.get(0);
			strTotalFee = objReceivableforsmsColumns.getRvrv_actualtotal();
			*/
			// 总重量
			SumchargeweightQuery sumchargeweightQuery = new SumchargeweightQuery();
			sumchargeweightQuery.setBw_code_arrival(otherParam);
			SumchargeweightColumns sumchargeweightColumns = (SumchargeweightColumns) sumchargeweightQuery
					.getResults().get(0);
			totalWeight = sumchargeweightColumns.getSumchargeweight();
			// 主单labelcode查询
			BatchwaybillQuery batchwaybillQuery = new BatchwaybillQuery();
			batchwaybillQuery.setBwcode(otherParam);
			BatchwaybillColumns batchwaybillColumns = (BatchwaybillColumns) batchwaybillQuery
					.getResults().get(0);
			labelCode = batchwaybillColumns.getBwbwlabelcode();

			strContent.append("{\"action\": \"consignment\",\"data\": {");
			strContent.append("\"customer\": \""
					+ objSmsRRColumns.getSrcoconame() + "\"");
			strContent.append(",\"date\": \""
					+ DateFormatUtility.getStandardSysdate().substring(0, 10)
					+ "\"");
			strContent.append(",\"labelCode\": \"" + labelCode + "\"");
			strContent.append(",\"pieces\":\"" + strPiece + "\"");
			strContent.append(",\"totalWeight\":\"" + totalWeight + "\"");
			strContent.append(",\"balance\": \"" + objBalanceAmount
					+ "元(RMB)\"");
			strContent
					.append(",\"remark\":\"温馨提示：为了您的货物能正常中转，请保持帐户余额为正数，以免耽误。谢谢！\"}}");
		} else if (strSnkcode.equals("SNK002")) {// SNK002 交款通知

			strContent.append("{\"action\": \"payment\",\"data\": {");
			strContent.append("\"customer\": \""
					+ objSmsRRColumns.getSrcoconame() + "\"");
			strContent.append(",\"date\": \""
					+ DateFormatUtility.getStandardSysdate().substring(0, 10)
					+ "\"");
			strContent.append(",\"paymentAmount\": \"" + otherParam + "\"");
			strContent
					.append(",\"balance\":\"" + objBalanceAmount + "元(RMB)\"");
			strContent
					.append(",\"remark\":\"温馨提示：为了您的货物能正常中转，请保持帐户余额为正数，以免耽误。谢谢！\"}}");
		}

		for (Object object : listSmsreceiveruleCol) {
			SmsreceiveruleColumns objSmsreceiveruleColumns = (SmsreceiveruleColumns) object;
			SmsmessageColumns objSmsmessageColumns = new SmsmessageColumns();
			objSmsmessageColumns.setCscocode(objSmsreceiveruleColumns
					.getSrcococode());// ////
			objSmsmessageColumns.setSmssmscontent(strContent.toString());
			objSmsmessageColumns.setSmssmscreatedate(DateFormatUtility
					.getSysdate());
			// 获得接收手机号
			/*
			 * OperatorQuery objOperatorQuery = new OperatorQuery();
			 * OperatorCondition objOperatorCon = new OperatorCondition();
			 * objOperatorCon.setOpid(objSmsreceiveruleColumns.getSropid());
			 * objOperatorQuery.setCondition(objOperatorCon); List listOperator
			 * = objOperatorQuery.getResults(); OperatorColumns objOperator =
			 * (OperatorColumns)listOperator.get(0);
			 * objSmsmessageColumns.setSmssmsmobilenumber
			 * (objOperator.getOpmobile());
			 */
			objSmsmessageColumns.setSmssmsmobilenumber(objSmsreceiveruleColumns
					.getSropmobile());
			objSmsmessageColumns
					.setSmssmsreceivecocode(objSmsreceiveruleColumns
							.getSrcococode());// ////
			objSmsmessageColumns.setSmssmsstatus("U");
			if (StringUtility.isNull(objSmsreceiveruleColumns.getSropmobile()))
				continue;
			// 保存发送短信记录
			SaveSmsMessageTransaction objSaveSmsMessageTrans = new SaveSmsMessageTransaction();
			objSaveSmsMessageTrans.setParam(objSmsmessageColumns);
			objSaveSmsMessageTrans.execute();
		}

	}

	@Override
	public SMSResult sendSms(List<SmsmessageColumns> listMssmessageCol)
			throws Exception {
		PromptUtility objPU = null;
		SMSResult objSmsResult = new SMSResult();
		if (listMssmessageCol.isEmpty()) {
			objSmsResult.setSuccessSign(false);
			objSmsResult.setResultText("没有查询到需要发送的消息！");
			return objSmsResult;
		}
		ISMSService objSmsService = SMSServiceFactory.getSMSService();
		for (SmsmessageColumns objSmsmessageCol : listMssmessageCol) {

			SmsreceiveruleCondition objSMSRRCondition = new SmsreceiveruleCondition();
			objSMSRRCondition.setCocode(objSmsmessageCol.getCscocode());
			objSMSRRCondition.setOpmobile(objSmsmessageCol
					.getSmssmsmobilenumber());
			SmsreceiveruleQuery objSmsrecieveruleQuery = new SmsreceiveruleQuery();
			objSmsrecieveruleQuery.setCondition(objSMSRRCondition);
			List<SmsreceiveruleColumns> listSmsreceiverule = objSmsrecieveruleQuery.getResults();
			if (listSmsreceiverule.isEmpty()) {// 没有规则，则回滚
				objSmsResult.setSuccessSign(false);
				objSmsResult.setResultText("没有查询到规则！");

				objSmsmessageCol.setSmssmsstatus("U");
				SaveSmsMessageTransaction objSaveSmsMessageTrans = new SaveSmsMessageTransaction();
				objSaveSmsMessageTrans.setParam(objSmsmessageCol);
				objSaveSmsMessageTrans.execute();

				continue;
			}

			SmsreceiveruleColumns objSmsreceiverule = listSmsreceiverule.get(0);
			// 判断接收日期限制
			if (objSmsreceiverule.getSnttsnttcode().equals("SNTT2"))
				objPU = checkReceiveType(objSmsreceiverule);
			if (objPU != null && !objPU.canGo(false)) {
				objSmsResult.setSuccessSign(false);
				objSmsResult.setResultText(objPU.getDescribtion());

				objSmsmessageCol.setSmssmsstatus("U");
				SaveSmsMessageTransaction objSaveSmsMessageTrans = new SaveSmsMessageTransaction();
				objSaveSmsMessageTrans.setParam(objSmsmessageCol);
				objSaveSmsMessageTrans.execute();

				continue;
			}
			// 发送
			objSmsResult = objSmsService.sendOneSms(objSmsmessageCol
					.getSmssmsmobilenumber(), objSmsmessageCol
					.getSmssmscontent());
			if (objSmsResult.isSuceess()) {
				// 发送成功，则修改短信发送状态
				objSmsmessageCol.setSmssmsstatus("S");
				SaveSmsMessageTransaction objSaveSmsMessageTrans = new SaveSmsMessageTransaction();
				objSaveSmsMessageTrans.setParam(objSmsmessageCol);
				objSaveSmsMessageTrans.execute();
			} else {
				System.out.println("微信号为："
						+ objSmsmessageCol.getSmssmsmobilenumber() + " 消息内容为："
						+ objSmsmessageCol.getSmssmscontent() + " 的消息未发送成功!");
				System.out.println("消息发送失败：" + objSmsResult.getResultText());

				objSmsmessageCol.setSmssmsstatus("U");
				SaveSmsMessageTransaction objSaveSmsMessageTrans = new SaveSmsMessageTransaction();
				objSaveSmsMessageTrans.setParam(objSmsmessageCol);
				objSaveSmsMessageTrans.execute();

				continue;
			}

		}
		return objSmsResult;
	}

}
