package kyle.leis.eo.customerservice.issue.bl.isa;

import kyle.leis.eo.customerservice.issue.da.IssueColumns;
import kyle.leis.eo.customerservice.issue.da.IssueactionColumns;

public abstract class AIssueAction {
	
	public abstract String rebuildIssue(IssueColumns objIssueColumns,
			IssueactionColumns objIssueAction) throws Exception;
	
}
