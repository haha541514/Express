package kyle.leis.eo.customerservice.issue.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.customerservice.issue.da.IssueColumns;
import kyle.leis.eo.customerservice.issue.dax.IIssueBasicData;
import kyle.leis.eo.customerservice.issue.dax.IssueDemand;
import kyle.leis.hi.TcsIssue;
import kyle.leis.hi.TdiIssueholdstatus;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TdiPaymentmode;
import kyle.leis.hi.TopCorewaybill;

public class SaveIssueTrans extends AbstractTransaction {
	private IssueColumns m_objIssueColumns;
	private List m_listIssueAction;
	private String m_strOperId;
	private String m_strIssueHoldStatus;
	private Long m_lNewIsuid;
	
	public void setHoldIssueParam(String strCwcode,
			String strIsutcode,
			String strIsuContent,
			String strOperId) throws Exception {
		// 构建问题
		IssueColumns objIssueColumns = new IssueColumns();
		objIssueColumns.setCwcwcode(Long.parseLong(strCwcode));
		objIssueColumns.setIsusisuscode(IIssueBasicData.ISSUE_STATUS_DEALING);
		objIssueColumns.setIsuiscontent(strIsuContent);
		objIssueColumns.setIsugisugcode(IIssueBasicData.ISSUE_GRADE_NORMAL);
		objIssueColumns.setIsutisutcode(strIsutcode);
		// 构建问题操作
		List listIssueAction = IssueDemand.buildHoldIssueAction(strIsuContent);
		setParamByIssueAction(objIssueColumns, listIssueAction, strOperId);
	}	
	
	public void setAddParam(IssueColumns objIssueColumns, 
			String strOperId) {
		objIssueColumns.setIsusisuscode(IIssueBasicData.ISSUE_STATUS_ASSIGN);
		objIssueColumns.setIsugisugcode(IIssueBasicData.ISSUE_GRADE_NORMAL);
		// 构建问题操作
		List listIssueAction = IssueDemand.buildAddIssueAction();
		setParamByIssueAction(objIssueColumns, listIssueAction, strOperId);
	}
	
	public void setParamByIssueAction(IssueColumns objIssueColumns,
			List listIssueAction,
			String strOperId) {
		m_listIssueAction = listIssueAction;
		setParam(objIssueColumns, strOperId);
	}
	
	public void setParam(IssueColumns objIssueColumns, 
			String strOperId) {
		m_objIssueColumns = objIssueColumns;
		m_strOperId = strOperId;
	}
	
	public Long getNewIsuid() {
		return m_lNewIsuid;
	}
	
	public String getIssueHoldStatus() {
		return m_strIssueHoldStatus;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_objIssueColumns == null) return;
		// 保存问题
		TcsIssue objTcsIssue = null;
		TopCorewaybill objTopCorewaybill = (TopCorewaybill)objSession.load(TopCorewaybill.class, 
				Long.parseLong(m_objIssueColumns.getCwcwcode()));	
		if (StringUtility.isNull(m_objIssueColumns.getIsuisuid())) {
			TdiOperator objTdiOperator = (TdiOperator)objSession.load(TdiOperator.class, 
					Long.parseLong(m_strOperId));				
			objTcsIssue = new TcsIssue();
			objTcsIssue.setTdiOperatorByOpIdCreator(objTdiOperator);
			objTcsIssue.setIsuCreatedate(DateFormatUtility.getSysdate());
		} else {
			objTcsIssue = (TcsIssue)objSession.load(TcsIssue.class, 
					Long.parseLong(m_objIssueColumns.getIsuisuid()));
		}
		IssueDemand.setIssueByColumns(objTcsIssue, 
				m_objIssueColumns, 
				m_strOperId,
				objTopCorewaybill.getTdiEnterpriseelement().getEeCode(),
				objTopCorewaybill.getTcoCorporationByCoCodeCustomer().getCoCode(),
				objSession);
		// 重构问题
		m_strIssueHoldStatus = IssueDemand.rebuildIssue(m_objIssueColumns, m_listIssueAction);
		if (!StringUtility.isNull(m_objIssueColumns.getIhsihscode())) {
			TdiIssueholdstatus objIHoldstatus = (TdiIssueholdstatus)objSession.load(TdiIssueholdstatus.class, 
					m_objIssueColumns.getIhsihscode());			
			objTcsIssue.setTdiIssueholdstatus(objIHoldstatus);
		}
		objTcsIssue.setTopCorewaybill(objTopCorewaybill);
		objSession.save(objTcsIssue);
		m_lNewIsuid = objTcsIssue.getIsuId();
		// 保存问题处理操作
		SaveIssueactionTrans objSIActionTrans = new SaveIssueactionTrans();
		objSIActionTrans.setParam(m_listIssueAction, objTcsIssue, m_strOperId);
		// 设置运单扣件状态
		if (!StringUtility.isNull(m_strIssueHoldStatus)) {
			TdiIssueholdstatus objIHoldstatus = (TdiIssueholdstatus)objSession.load(TdiIssueholdstatus.class, 
					m_strIssueHoldStatus);
			objTopCorewaybill.setTdiIssueholdstatus(objIHoldstatus);
		}
		// 退件
		if (!StringUtility.isNull(m_strIssueHoldStatus) && 
				m_strIssueHoldStatus.equals(IIssueBasicData.HOLD_STATUS_RETURN)) {
			TdiPaymentmode objTdiPaymentmode = (TdiPaymentmode)objSession.load(TdiPaymentmode.class, "AFR");
			objTopCorewaybill.setTdiPaymentmode(objTdiPaymentmode);
		}
		objSIActionTrans.transaction(objSession);
		// 更改运单状态
		if (objTopCorewaybill != null)
			objSession.save(objTopCorewaybill);
	}
}
