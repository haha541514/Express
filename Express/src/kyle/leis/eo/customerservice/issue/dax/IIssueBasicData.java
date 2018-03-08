package kyle.leis.eo.customerservice.issue.dax;

public interface IIssueBasicData {
	public static final String ISSUE_STATUS_NEW = "NW";
	public static final String ISSUE_STATUS_CONFIRM = "CF";
	public static final String ISSUE_STATUS_ASSIGN = "AS";
	public static final String ISSUE_STATUS_DEALING = "DL";
	public static final String ISSUE_STATUS_FINISH = "FS";
	public static final String ISSUE_STATUS_CLOSE = "CL";
	public static final String ISSUE_STATUS_ELIMINATE = "EL";
	
	public static final String ISSUE_GRADE_NORMAL = "NM";
	
	public static final String ISSUE_TYPE_ARREARGE = "ST01";
	public static final String ISSUE_TYPE_ODA = "CS13";
	
	public static final String HOLD_STATUS_CONFIRM = "CH";
	public static final String HOLD_STATUS_RELEASEHOLD = "RH";
	public static final String HOLD_STATUS_RETURN = "CR";
	
	public static final String ACTION_HOLDCARGO = "HC";
	public static final String ACTION_RELEASEHOLD = "RH";
	public static final String ACTION_RETURNCARGO = "RC";
}
