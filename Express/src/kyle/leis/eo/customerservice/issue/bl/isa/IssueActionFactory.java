package kyle.leis.eo.customerservice.issue.bl.isa;

import kyle.leis.eo.customerservice.issue.dax.IIssueBasicData;

public class IssueActionFactory {
	public static AIssueAction getIssueAction(String strAkcode) 
	throws Exception {
		if (strAkcode.equals(IIssueBasicData.ACTION_HOLDCARGO))
			return new HoldIssueAction();
		if (strAkcode.equals(IIssueBasicData.ACTION_RELEASEHOLD))
			return new ReleaseIssueAction();
		if (strAkcode.equals(IIssueBasicData.ACTION_RETURNCARGO))
			return new ReturnIssueAction();
		return new NormalIssueAction();
	}
	
}
