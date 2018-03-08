package kyle.leis.eo.customerservice.issue.bl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.calculate.feecalculate.blx.AutoFeeCalculateThread;
import kyle.leis.eo.customerservice.issue.da.IssueColumns;
import kyle.leis.eo.customerservice.issue.da.IssueactionColumns;
import kyle.leis.eo.customerservice.issue.dax.IIssueBasicData;
import kyle.leis.eo.customerservice.issue.dax.IssueDemand;
import kyle.leis.eo.customerservice.issue.tp.SaveIssueTrans;
import kyle.leis.eo.finance.dunning.bl.Dunning;
import kyle.leis.eo.operation.corewaybill.da.SimplecorewaybillColumns;
import kyle.leis.eo.operation.corewaybill.dax.CorewaybillDemand;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
import kyle.leis.es.smsservice.bl.IssueSms;
import kyle.leis.es.smsservice.bl.Sms;
import kyle.leis.es.smsservice.bl.SmsSaver;
import kyle.leis.es.smsservice.bl.WeChatMsgSaver;

public class Issue {
	/**
	 * 增加扣件问题
	 * @param strCwcode
	 * @param strItcode
	 * @param strIsuContent
	 * @param strOperId
	 * @throws Exception
	 */
	public String addHoldIssue(String strCwcode,
			String strItcode,
			String strIsuContent,
			String strOperId) throws Exception {
		// 判断是否存在同种类型未完成的问题
		List objList = IssueDemand.queryValidIssue(strItcode, strCwcode);
		if (objList != null && objList.size() > 0 )
			return null;
		SaveIssueTrans objSaveIssueTrans = new SaveIssueTrans();
		objSaveIssueTrans.setHoldIssueParam(strCwcode,
				strItcode,
				strIsuContent,
				strOperId);
		objSaveIssueTrans.execute();
		//发送Email通知
		String strIssueId = String.valueOf(objSaveIssueTrans.getNewIsuid());
		SendIssueEmailThread objSendIssueEmailThread = new SendIssueEmailThread(strCwcode,strIssueId,strIsuContent,strOperId);
		objSendIssueEmailThread.start();
		
		//发送消息到移动设备
		// IssueDemand.sendMessage(strCwcode, strIsuContent);
		
		// 保存微信消息
		try {
			saveWeChatMsg(strCwcode, strIssueId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return objSaveIssueTrans.getIssueHoldStatus();
	}

	/**
	 * @param strCwcode
	 * @param strIssueId
	 * @throws Exception
	 */
	private void saveWeChatMsg(String strCwcode, String strIssueId)
			throws Exception {
		SmsSaver chatMsgSaver = new WeChatMsgSaver();
		HousewaybillColumns objHWBColumns = HousewaybillDemand.loadByCwcode(strCwcode);
		Sms issueSms = new IssueSms(objHWBColumns.getCcoconame(), objHWBColumns.getCwcwcustomerewbcode(), strIssueId);
		chatMsgSaver.saveAutoSmsmessage(objHWBColumns.getCcococode(), issueSms);
	}
	
	public void addNormalIssue(String strCwcode,
			String strItcode,
			String strIsuContent,
			String strOperId) throws Exception {
		// 判断是否存在同种类型未完成的问题
		List objList = IssueDemand.queryValidIssue(strItcode, strCwcode);
		if (objList != null && objList.size() > 0 )
			return;
		// 构造问题的Columns
		SaveIssueTrans objSaveIssueTrans = new SaveIssueTrans();
		IssueColumns objIssueColumns = new IssueColumns();
		objIssueColumns.setCwcwcode(Long.parseLong(strCwcode));
		objIssueColumns.setIsutisutcode(strItcode);
		objIssueColumns.setIsuiscontent(strIsuContent);
		// 记录问题
		objSaveIssueTrans.setAddParam(objIssueColumns, strOperId);
		objSaveIssueTrans.execute();
	}	
	
	public void addArrearageIssue(String strCwcode) throws Exception {
		SimplecorewaybillColumns objSWaybillColumns = CorewaybillDemand.loadSimpleCorewaybill(strCwcode);
		String strCocode = objSWaybillColumns.getCwco_code_customer();
		// String strIhscode = objSWaybillColumns.getCwihs_code();
		// 判断是否欠费
		Dunning objDunning = new Dunning();
		BigDecimal objCreditbalance = new BigDecimal(objDunning.getCreditbalance(strCocode));
		if (objCreditbalance.compareTo(new BigDecimal("0")) >= 0)
			return;
		// 判断是否存在同种类型且扣件的问题
		/*
		List objList = IssueDemand.queryValidIssue(IIssueBasicData.ISSUE_TYPE_ARREARGE, strCwcode);
		if (objList != null && 
				objList.size() > 0 && 
				strIhscode.equals(IIssueBasicData.HOLD_STATUS_CONFIRM))
			return null;*/
		// 记录问题
		addNormalIssue(strCwcode,
				IIssueBasicData.ISSUE_TYPE_ARREARGE,
				"客户欠费，余额为：" + objCreditbalance.toString(),
				"0");
	}
	
	/**
	 * 新增问题处理
	 * @param strAkcode
	 * @param strIsucode
	 * @param strIsacontent
	 * @param strOperId
	 */
	public String addIssueAction(String strAkcode,
			String strIsuid,
			String strIsacontent,
			String strOperId) throws Exception {
		IssueColumns objIssueColumns = IssueDemand.loadIssueById(strIsuid);
		// 设置问题处理列表
		List<IssueactionColumns> listIssueAction = new ArrayList<IssueactionColumns>();
		IssueactionColumns objISActionColumns = new IssueactionColumns();
		objISActionColumns.setAkakcode(strAkcode);
		objISActionColumns.setIsacomp_idisaid(1);
		objISActionColumns.setIsaisacontent(strIsacontent);
		
		listIssueAction.add(objISActionColumns);
		// 执行事务
		SaveIssueTrans objSaveIssueTrans = new SaveIssueTrans();
		objSaveIssueTrans.setParamByIssueAction(objIssueColumns, 
				listIssueAction, 
				strOperId);
		objSaveIssueTrans.execute();
		String strIhscode = objSaveIssueTrans.getIssueHoldStatus();
		// 退件则重新计费
		if (!StringUtility.isNull(strIhscode) && 
				strIhscode.equals(IIssueBasicData.HOLD_STATUS_RETURN)) {
			AutoFeeCalculateThread objAFCThread = new AutoFeeCalculateThread(objIssueColumns.getCwcwcode(),
					"",
					false);
			objAFCThread.start();
		}
		
		if ("HC".equals(strAkcode) || "RC".equals(strAkcode)) {
			try {
				saveWeChatMsg(objIssueColumns.getCwcwcode(), strIsuid);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return strIhscode;
	}
}
