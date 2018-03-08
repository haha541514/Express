package kyle.leis.eo.customerservice.issue.dax;

import java.util.ArrayList;
import java.util.List;

import kyle.common.util.jlang.CollectionUtility;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.customerservice.issue.bl.isa.AIssueAction;
import kyle.leis.eo.customerservice.issue.bl.isa.IssueActionFactory;
import kyle.leis.eo.customerservice.issue.da.IssueColumns;
import kyle.leis.eo.customerservice.issue.da.IssueCondition;
import kyle.leis.eo.customerservice.issue.da.IssueQuery;
import kyle.leis.eo.customerservice.issue.da.IssueactionColumns;
import kyle.leis.eo.customerservice.issue.da.IssueactionCondition;
import kyle.leis.eo.customerservice.issue.da.IssueactionQuery;
import kyle.leis.eo.customerservice.issue.da.MaxissueactionColumns;
import kyle.leis.eo.customerservice.issue.da.MaxissueactionQuery;
import kyle.leis.es.company.customer.dax.CustomerDemand;
import kyle.leis.fs.authoritys.user.da.UserColumns;
import kyle.leis.fs.authoritys.user.da.UserCondition;
import kyle.leis.fs.authoritys.user.dax.UserDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TdiIssuegradeDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiIssueholdstatusDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiIssuestatusDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiIssuetypeDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.fs.dictionary.enterpriseelement.dax.EnterpriseelementDemand;
import kyle.leis.hi.TcsIssue;
import kyle.leis.hi.TdiIssuegrade;
import kyle.leis.hi.TdiIssueholdstatus;
import kyle.leis.hi.TdiIssuestatus;
import kyle.leis.hi.TdiIssuetype;
import kyle.leis.hi.TdiOperator;
import net.sf.hibernate.Session;

public class IssueDemand {
	/**
	 * 查询
	 * @param objIssueCondition
	 * @return
	 * @throws Exception
	 */
	public static List query(IssueCondition objIssueCondition) throws Exception {
		IssueQuery objIssueQuery = new IssueQuery();
		if(!StringUtility.isNull(objIssueCondition.getEestructurecode())){
			String strEestructurecode = EnterpriseelementDemand.getEestructurecode(objIssueCondition.getEestructurecode());
			objIssueCondition.setEestructurecode(strEestructurecode);
		}
		objIssueQuery.setCondition(objIssueCondition);
		return objIssueQuery.getResults();
	}
	
	/**
	 * 查询同种类型有效的问题
	 * @param strIsutcode
	 * @param strCwcode
	 * @return
	 * @throws Exception
	 */
	public static List queryValidIssue(String strIsutcode, 
			String strCwcode) throws Exception {
		IssueCondition objIssueCondition = new IssueCondition();
		objIssueCondition.setIsutcode(strIsutcode);
		objIssueCondition.setCwcode(strCwcode);
		objIssueCondition.setIsuscode("NW,CF,AS,DL");
		return query(objIssueCondition);
	}
	
	
	/**
	 * 根据运单查询
	 * @param strCwcode
	 * @return
	 * @throws Exception
	 */
	public static List loadIssueByCwcode(String strCwcode) throws Exception {
		IssueQuery objIssueQuery = new IssueQuery();
		objIssueQuery.setCwcode(strCwcode);
		return objIssueQuery.getResults();		
	}
	
	/**
	 * 根据问题ID装载记录
	 * @param strIsuid
	 * @return
	 * @throws Exception
	 */
	public static IssueColumns loadIssueById(String strIsuid) throws Exception {
		IssueQuery objIssueQuery = new IssueQuery();
		objIssueQuery.setIsuid(strIsuid);
		List objList = objIssueQuery.getResults();
		if (objList == null || objList.size() < 1) return null;
		return (IssueColumns)objList.get(0);
	}
	
	
	/**
	 * 通过快件问题装载问题操作
	 * @param strIsuid
	 * @return
	 * @throws Exception
	 */
	public static List loadIssueaction(String strIsuid) throws Exception {
		IssueactionQuery objIssueactionQuery = new IssueactionQuery();
		objIssueactionQuery.setIsuid(strIsuid);
		return objIssueactionQuery.getResults();
	}	
	
	/**
	 * 通过快件问题及动作装载问题操作
	 * @param strIsuid
	 * @return
	 * @throws Exception
	 */
	public static List loadIssueaction(String strIsuid,String strAkcode) throws Exception {
		IssueactionQuery objIssueactionQuery = new IssueactionQuery();
		IssueactionCondition issueactionCondition= new IssueactionCondition();
		issueactionCondition.setIsuid(strIsuid);
		issueactionCondition.setAkcode(strAkcode);
		objIssueactionQuery.setCondition(issueactionCondition);
		return objIssueactionQuery.getResults();
	}	
	
	/**
	 * 获得最大的问题操作ID
	 * @param strIsuid
	 * @return
	 * @throws Exception
	 */
	public static String getMaxIsuactionId(String strIsuid) throws Exception {
		MaxissueactionQuery objMIAQuery = new MaxissueactionQuery();
		objMIAQuery.setIsuid(strIsuid);
		List objList = objMIAQuery.getResults();
		if (objList == null || objList.size() < 1) return "0";
		String strMaxisaid = ((MaxissueactionColumns)objList.get(0)).getMaxisaid();
		if (StringUtility.isNull(strMaxisaid)) return "0";
		return strMaxisaid;
	}
	
	public static String rebuildIssue(IssueColumns objIssueColumns,
			List listIssueAction) throws Exception {
		if (listIssueAction == null || listIssueAction.size() < 1)
			return null;
		String strIssueHoldstatus = "";
		for (int i = 0; i < listIssueAction.size(); i++) {
			IssueactionColumns objIssueactionColumns = (IssueactionColumns)listIssueAction.get(i);
			AIssueAction objIssueAction = IssueActionFactory.getIssueAction(objIssueactionColumns.getAkakcode());
			strIssueHoldstatus = objIssueAction.rebuildIssue(objIssueColumns, 
					objIssueactionColumns);
		}
		return strIssueHoldstatus;
	}
	
	/**
	 * 构建ODA的IssueColumns
	 * @param strCwcode
	 * @param strIsuContent
	 * @return
	 */
	public static IssueColumns buildODAIssueColumns(String strCwcode, 
			String strIsuContent) {
		IssueColumns objIssueColumns = new IssueColumns();
		if (!StringUtility.isNull(strCwcode))
			objIssueColumns.setCwcwcode(Long.parseLong(strCwcode));
		objIssueColumns.setIsutisutcode(IIssueBasicData.ISSUE_TYPE_ODA);
		objIssueColumns.setIsuiscontent(strIsuContent);
		objIssueColumns.setIhsihscode(IIssueBasicData.HOLD_STATUS_CONFIRM);
		return objIssueColumns;
	}	
	
	
	public static List buildHoldIssueAction(String strIsacontent) {
		List<IssueactionColumns> listIssueAction = new ArrayList<IssueactionColumns>();
		listIssueAction.addAll(buildAddIssueAction());
		// 创建问题
		IssueactionColumns objISActionColumns = new IssueactionColumns();
		// 扣件
		objISActionColumns = new IssueactionColumns();
		objISActionColumns.setAkakcode("HC");
		objISActionColumns.setIsacomp_idisaid(3);
		objISActionColumns.setIsaisacontent("因为" + strIsacontent + "的问题而扣件");
		listIssueAction.add(objISActionColumns);		
		
		return listIssueAction;
	}
	
	public static List<IssueactionColumns> buildAddIssueAction() {
		List<IssueactionColumns> listIssueAction = new ArrayList<IssueactionColumns>();
		// 创建问题
		IssueactionColumns objISActionColumns = new IssueactionColumns();
		objISActionColumns.setAkakcode("AD");
		objISActionColumns.setIsacomp_idisaid(1);
		objISActionColumns.setIsaisacontent("创建问题");
		listIssueAction.add(objISActionColumns);
		// 指派
		objISActionColumns = new IssueactionColumns();
		objISActionColumns.setAkakcode("AS");
		objISActionColumns.setIsacomp_idisaid(2);
		objISActionColumns.setIsaisacontent("指派给客服员处理");
		listIssueAction.add(objISActionColumns);		
		
		return listIssueAction;
	}
	
	public static List<IssueactionColumns> buildNoticIssucAction(){
		List<IssueactionColumns> listIssueAction = new ArrayList<IssueactionColumns>();
		IssueactionColumns objISActionColumns = new IssueactionColumns();
		objISActionColumns.setAkakcode("NC_MAIL");
		objISActionColumns.setIsacomp_idisaid(4);
		objISActionColumns.setIsaisacontent("发送邮件通知客户");
		listIssueAction.add(objISActionColumns);
		
		return listIssueAction;
	}
	
	/**
	 * 除自身问题外判断是否全部放行扣件
	 * @param strCwcode
	 * @param strSelfIsuid
	 * @return
	 * @throws Exception
	 */
	public static boolean isAllReleaseHold(String strCwcode,
			String strSelfIsuid) throws Exception {
		if (StringUtility.isNull(strCwcode)) return true;
		
		List objList = IssueDemand.loadIssueByCwcode(strCwcode);
		if (objList != null && objList.size() > 1)
			for (int i = 0; i < objList.size(); i++) {
				IssueColumns objIssueColumns = (IssueColumns)objList.get(i);
				if (!StringUtility.isNull(strSelfIsuid) &&
						strSelfIsuid.equals(objIssueColumns.getIsuisuid()))
					continue;
				if (objIssueColumns.getIsusisuscode().equals(IIssueBasicData.ISSUE_STATUS_ELIMINATE))
					continue;
				String strIhscode = objIssueColumns.getIhsihscode();
				if (StringUtility.isNull(strIhscode))
					continue;
				if (strIhscode.equals(IIssueBasicData.HOLD_STATUS_CONFIRM) ||
						strIhscode.equals(IIssueBasicData.HOLD_STATUS_RETURN))
					return false;
			}
		return true;
	}
	
	
	
	public static void setIssueByColumns(TcsIssue objTcsIssue,
			IssueColumns objIssueColumns, 
			String strOperId,
			String strEecode,
			String strCocode,
			Session objSession) throws Exception {
		objTcsIssue.setIsContent(objIssueColumns.getIsuiscontent());
		objTcsIssue.setIsuModifydate(DateFormatUtility.getSysdate());
		objTcsIssue.setTdiOperatorByOpIdExecutor(getContactperson(strEecode, strCocode));
		
		if (!StringUtility.isNull(objIssueColumns.getIsugisugcode())) {
			TdiIssuegrade objTdiIssuegrade = TdiIssuegradeDC.loadByKey(objIssueColumns.getIsugisugcode());
			objTcsIssue.setTdiIssuegrade(objTdiIssuegrade);
		}
		if (!StringUtility.isNull(objIssueColumns.getIhsihscode())) {
			TdiIssueholdstatus objTdiIssueholdstatus = TdiIssueholdstatusDC.loadByKey(objIssueColumns.getIhsihscode());
			objTcsIssue.setTdiIssueholdstatus(objTdiIssueholdstatus);
		}
		if (!StringUtility.isNull(objIssueColumns.getIsusisuscode())) {
			TdiIssuestatus objTdiIssuestatus = TdiIssuestatusDC.loadByKey(objIssueColumns.getIsusisuscode());
			objTcsIssue.setTdiIssuestatus(objTdiIssuestatus);
		}
		if (!StringUtility.isNull(objIssueColumns.getIsutisutcode())) {
			TdiIssuetype objTdiIssuetype = TdiIssuetypeDC.loadByKey(objIssueColumns.getIsutisutcode());
			objTcsIssue.setTdiIssuetype(objTdiIssuetype);
		}
		if (!StringUtility.isNull(strOperId)) {
			TdiOperator objTdiOperator = (TdiOperator)objSession.load(TdiOperator.class, 
					Long.parseLong(strOperId));
			objTcsIssue.setTdiOperatorByOpIdModifier(objTdiOperator);
		}
	}
	
	/**
	 * 问题件执行者
	 * @param strOperId
	 * @param strCwcode
	 * @return
	 * @throws Exception
	 */
	public static TdiOperator getContactperson(String strEecode, 
			String strCocode) throws Exception
	{
		// HousewaybillColumns objHWBColumns = HousewaybillDemand.loadByCwcode(strCwcode);
		UserCondition objUserCondition = new UserCondition();
		//objUserCondition.setEecode(strEecode);
		objUserCondition.setOpissuecontactpersonsign("Y");
		List listUser = UserDemand.query(objUserCondition);
		if(!CollectionUtility.isNull(listUser))
			return TdiOperatorDC.loadByKey(((UserColumns)listUser.get(0)).getOpopid());
		//如果没有(本公司)问题件联系人,则使用客户对应的(本公司)客服
		return TdiOperatorDC.loadByKey(CustomerDemand.load(strCocode).getCsopopid());
	}
}
