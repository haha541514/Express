package kyle.leis.eo.customerservice.issue.bl.isa;

import kyle.leis.eo.customerservice.issue.da.IssueColumns;
import kyle.leis.eo.customerservice.issue.da.IssueactionColumns;
import kyle.leis.eo.customerservice.issue.dax.IIssueBasicData;

public class ReturnIssueAction extends AIssueAction {

	public String rebuildIssue(IssueColumns objIssueColumns, 
			IssueactionColumns objIssueAction) throws Exception {
		objIssueColumns.setIhsihscode(IIssueBasicData.HOLD_STATUS_RETURN);
		return IIssueBasicData.HOLD_STATUS_RETURN;
	}

}
