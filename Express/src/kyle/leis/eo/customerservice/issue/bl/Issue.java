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
	 * ���ӿۼ�����
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
		// �ж��Ƿ����ͬ������δ��ɵ�����
		List objList = IssueDemand.queryValidIssue(strItcode, strCwcode);
		if (objList != null && objList.size() > 0 )
			return null;
		SaveIssueTrans objSaveIssueTrans = new SaveIssueTrans();
		objSaveIssueTrans.setHoldIssueParam(strCwcode,
				strItcode,
				strIsuContent,
				strOperId);
		objSaveIssueTrans.execute();
		//����Email֪ͨ
		String strIssueId = String.valueOf(objSaveIssueTrans.getNewIsuid());
		SendIssueEmailThread objSendIssueEmailThread = new SendIssueEmailThread(strCwcode,strIssueId,strIsuContent,strOperId);
		objSendIssueEmailThread.start();
		
		//������Ϣ���ƶ��豸
		// IssueDemand.sendMessage(strCwcode, strIsuContent);
		
		// ����΢����Ϣ
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
		// �ж��Ƿ����ͬ������δ��ɵ�����
		List objList = IssueDemand.queryValidIssue(strItcode, strCwcode);
		if (objList != null && objList.size() > 0 )
			return;
		// ���������Columns
		SaveIssueTrans objSaveIssueTrans = new SaveIssueTrans();
		IssueColumns objIssueColumns = new IssueColumns();
		objIssueColumns.setCwcwcode(Long.parseLong(strCwcode));
		objIssueColumns.setIsutisutcode(strItcode);
		objIssueColumns.setIsuiscontent(strIsuContent);
		// ��¼����
		objSaveIssueTrans.setAddParam(objIssueColumns, strOperId);
		objSaveIssueTrans.execute();
	}	
	
	public void addArrearageIssue(String strCwcode) throws Exception {
		SimplecorewaybillColumns objSWaybillColumns = CorewaybillDemand.loadSimpleCorewaybill(strCwcode);
		String strCocode = objSWaybillColumns.getCwco_code_customer();
		// String strIhscode = objSWaybillColumns.getCwihs_code();
		// �ж��Ƿ�Ƿ��
		Dunning objDunning = new Dunning();
		BigDecimal objCreditbalance = new BigDecimal(objDunning.getCreditbalance(strCocode));
		if (objCreditbalance.compareTo(new BigDecimal("0")) >= 0)
			return;
		// �ж��Ƿ����ͬ�������ҿۼ�������
		/*
		List objList = IssueDemand.queryValidIssue(IIssueBasicData.ISSUE_TYPE_ARREARGE, strCwcode);
		if (objList != null && 
				objList.size() > 0 && 
				strIhscode.equals(IIssueBasicData.HOLD_STATUS_CONFIRM))
			return null;*/
		// ��¼����
		addNormalIssue(strCwcode,
				IIssueBasicData.ISSUE_TYPE_ARREARGE,
				"�ͻ�Ƿ�ѣ����Ϊ��" + objCreditbalance.toString(),
				"0");
	}
	
	/**
	 * �������⴦��
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
		// �������⴦���б�
		List<IssueactionColumns> listIssueAction = new ArrayList<IssueactionColumns>();
		IssueactionColumns objISActionColumns = new IssueactionColumns();
		objISActionColumns.setAkakcode(strAkcode);
		objISActionColumns.setIsacomp_idisaid(1);
		objISActionColumns.setIsaisacontent(strIsacontent);
		
		listIssueAction.add(objISActionColumns);
		// ִ������
		SaveIssueTrans objSaveIssueTrans = new SaveIssueTrans();
		objSaveIssueTrans.setParamByIssueAction(objIssueColumns, 
				listIssueAction, 
				strOperId);
		objSaveIssueTrans.execute();
		String strIhscode = objSaveIssueTrans.getIssueHoldStatus();
		// �˼������¼Ʒ�
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
