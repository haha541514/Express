package kyle.leis.eo.customerservice.issue.bl.isa;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.customerservice.issue.da.IssueColumns;
import kyle.leis.eo.customerservice.issue.da.IssueactionColumns;
import kyle.leis.eo.customerservice.issue.dax.IIssueBasicData;
import kyle.leis.eo.customerservice.issue.dax.IssueDemand;
import kyle.leis.eo.operation.corewaybill.dax.CorewaybillDemand;

public class ReleaseIssueAction extends AIssueAction {
	
	public String rebuildIssue(IssueColumns objIssueColumns, 
			IssueactionColumns objIssueAction) throws Exception {
		// �ж��Ƿ��Ѿ��˼�
		if (StringUtility.isNull(objIssueColumns.getCwcwcode()))
			return null;
		String strCwHoldstatus = CorewaybillDemand.getIssueHoldstatus(objIssueColumns.getCwcwcode());
		if (!StringUtility.isNull(strCwHoldstatus) && 
				strCwHoldstatus.equals(IIssueBasicData.HOLD_STATUS_RETURN))
			return "";
		objIssueColumns.setIhsihscode(IIssueBasicData.HOLD_STATUS_RELEASEHOLD);
		// �ж��Ƿ��Ѿ�ȫ������ۼ�
		if (IssueDemand.isAllReleaseHold(objIssueColumns.getCwcwcode(),
				objIssueColumns.getIsuisuid()))
			return IIssueBasicData.HOLD_STATUS_RELEASEHOLD;
		return "";
	}

}
