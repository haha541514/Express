package kyle.leis.eo.customerservice.issue.bl;

import java.util.List;
import java.util.logging.Logger;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.common.util.email.EMail;
import kyle.common.util.jlang.CollectionUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.customerservice.issue.dax.HtmlForEmailTemplate;
import kyle.leis.eo.customerservice.issue.dax.IssueDemand;
import kyle.leis.eo.customerservice.issue.dax.IssueHawbInfo;
import kyle.leis.eo.customerservice.issue.tp.SaveIssueactionTrans;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;
import kyle.leis.fs.authoritys.user.da.UserColumns;
import kyle.leis.fs.authoritys.user.da.UserCondition;
import kyle.leis.fs.authoritys.user.dax.UserDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TdiEnterpriseelementDC;
import kyle.leis.hi.TcsIssue;
import kyle.leis.hi.TdiEnterpriseelement;
import kyle.leis.hi.TdiOperator;

public class IssueForEmail {

	protected static Logger s_objLogger = Logger.getLogger(IssueForEmail.class
			.getName());
	
	private boolean m_bIsSuccess = false;
	
	public void sendIssueToEmail(String strCwcode,String strIssueId,
			 String strIsuCountent,String strOperId)
			throws Exception {
		sendIssueToEmail(strCwcode,strIssueId,strIsuCountent,strOperId,"");
	}

	public void sendIssueToEmail(String strCwcode,String strIssueId,
			 String strIsuCountent,String strOperId,
			String strReceiver) throws Exception {
		HousewaybillColumns objHWBColumns = HousewaybillDemand
				.loadByCwcode(strCwcode);
		TdiEnterpriseelement objTdiEnterpriseelement = TdiEnterpriseelementDC
				.loadByKey(objHWBColumns.getEeeecode());
		HSingleQuery objHSingleQuery = new HSingleQuery();
		TcsIssue objTcsIssue = (TcsIssue)objHSingleQuery.load(TcsIssue.class, Long.valueOf(strIssueId));
		String strIssueType = objTcsIssue.getTdiIssuetype().getIsutName() ;
		TdiOperator objExecutor = objTcsIssue.getTdiOperatorByOpIdExecutor();

		String strContacters = null;
		if(!StringUtility.isNull(strReceiver))
			strContacters = strReceiver;
		else
			strContacters = getContacter(objHWBColumns.getCcococode(), "GCS");
		if (strContacters == null)
			return;
		IssueHawbInfo objIssueHawbInfo = new IssueHawbInfo();
		objIssueHawbInfo.setCustomerName(objHWBColumns.getCcoconame());
		objIssueHawbInfo.setSignindate(objHWBColumns.getHwhwsignindate());
		objIssueHawbInfo.setCargoType(objHWBColumns.getCtctname());
		objIssueHawbInfo.setChargeWeight(objHWBColumns.getCwcwchargeweight());
		objIssueHawbInfo.setCustomerewbCode(objHWBColumns
				.getCwcwcustomerewbcode());
		if (!StringUtility.isNull(objHWBColumns.getCddtdtname()))
			objIssueHawbInfo.setDestinationCountry(objHWBColumns
					.getCddtdtname());
		else
			objIssueHawbInfo
					.setDestinationCountry(objHWBColumns.getSdtdtname());
		objIssueHawbInfo.setIssueContent(strIsuCountent);
		objIssueHawbInfo.setIssueType(strIssueType);
		objIssueHawbInfo.setProductKind(objHWBColumns.getPkpkname());

		objIssueHawbInfo.setSenderName(objExecutor.getOpName());//
		objIssueHawbInfo.setSenderTelephone(objExecutor.getOpTelephone());//
		
		String strContent = HtmlForEmailTemplate.getIssueEmailContent(
				objIssueHawbInfo, objTdiEnterpriseelement);
		
		String strCc = "";
		String strEnterprise = SystempropertyDemand.getEnterprise();
		if (!StringUtility.isNull(strEnterprise) && strEnterprise.startsWith("WC")) {
			strCc = objExecutor.getOpEmail();
		}
		EMail.sendHtmlMail(objExecutor.getOpEmail(), strContacters, strCc, "",
				"运单号为：" + objIssueHawbInfo.getCustomerewbCode() + " 的问题件通知",
				strContent, null, null);
		// new ActionServiceConfig("ServiceConfig.xml");
		/*
		 * EMail.sendHtmlMail("tommychen@xinsofts.com", "119220683@qq.com",
		 * "897952158@qq.com", "", "运单号为：" +
		 * objIssueHawbInfo.getCustomerewbCode() + " 的问题件通知", strContent, null,
		 * null);
		 */

		// 记录问题动作
		SaveIssueactionTrans objSIATrans = new SaveIssueactionTrans();
		objSIATrans.setParam(IssueDemand.buildNoticIssucAction(), objTcsIssue, strOperId);
		objSIATrans.execute();
		m_bIsSuccess = true;
	}

	/**
	 * 取得联系人
	 * 
	 * @param strCocode
	 * @return
	 * @throws Exception
	 */
	public String getContacter(String strCocode, String strFccode)
			throws Exception {
		String strContacters = new String();
		UserCondition objUserCondition = new UserCondition();
		objUserCondition.setFccode(strFccode);
		objUserCondition.setCocode(strCocode);
		List<UserColumns> listUser = UserDemand.query(objUserCondition);
		if (CollectionUtility.isNull(listUser)) {
			s_objLogger.warning("Send mail failed,No contact!");
			return null;
		}
		for (int i = 0; i < listUser.size(); i++) {
			strContacters = strContacters + listUser.get(i).getOpopemail()
					+ ";";
		}
		if (strContacters.endsWith(";"))
			strContacters = strContacters.substring(0, strContacters.length() - 1);
		return strContacters;
	}
	
	public Boolean getIsSuccess()
	{
		return new Boolean(m_bIsSuccess);
	}
}
