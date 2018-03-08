package kyle.leis.eo.customerservice.issue.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.eo.customerservice.issue.bl.Issue;
import kyle.leis.eo.customerservice.issue.bl.IssueForEmail;
import kyle.leis.eo.customerservice.issue.da.IssueCondition;
import kyle.leis.eo.customerservice.issue.dax.IssueDemand;

public class IssueService extends AService {
	
	public String query(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		IssueCondition objIssueCondition = (IssueCondition)objPD.getParameter(0, IssueCondition.class);
		List objList = IssueDemand.query(objIssueCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	public String loadIssueaction(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		String strIsuid = (String)objPD.getParameter(0, String.class);
		List objList = IssueDemand.loadIssueaction(strIsuid);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	public String loadIssueactionCWR(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		String strIsuid = (String)objPD.getParameter(0, String.class);
		List objList = IssueDemand.loadIssueaction(strIsuid,"CWR");
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	public String loadIssueByCwcode(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		String strCwcode = (String)objPD.getParameter(0, String.class);
		List objList = IssueDemand.loadIssueByCwcode(strCwcode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	public String addHoldIssue(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 4, this);
		
		String strCwcode = (String)objPD.getParameter(0, String.class);
		String strItcode = (String)objPD.getParameter(1, String.class);
		String strIsuContent = (String)objPD.getParameter(2, String.class);
		String strOperId = (String)objPD.getParameter(3, String.class);
		
		Issue objIssue = new Issue();
		String strIssueHoldstatus = objIssue.addHoldIssue(strCwcode, 
				strItcode, 
				strIsuContent, 
				strOperId);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(strIssueHoldstatus);
		return objEncode.toString();
	}
	public String addNormalIssue(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 4, this);
		
		String strCwcode = (String)objPD.getParameter(0, String.class);
		String strItcode = (String)objPD.getParameter(1, String.class);
		String strIsuContent = (String)objPD.getParameter(2, String.class);
		String strOperId = (String)objPD.getParameter(3, String.class);
		
		Issue objIssue = new Issue();objIssue.addNormalIssue(strCwcode, 
				strItcode, 
				strIsuContent, 
				strOperId);
		return "";
	}	
	public String addIssueAction(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 4, this);
		
		String strAkcode = (String)objPD.getParameter(0, String.class);
		String strIsuid = (String)objPD.getParameter(1, String.class);
		String strIsacontent = (String)objPD.getParameter(2, String.class);
		String strOperId = (String)objPD.getParameter(3, String.class);
		
		Issue objIssue = new Issue();
		String strIssueHoldstatus = objIssue.addIssueAction(strAkcode, 
				strIsuid, 
				strIsacontent, 
				strOperId);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(strIssueHoldstatus);
		return objEncode.toString();
	}
	
	public String sendMail(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,5,this);
		String strCwcode = (String) objPD.getParameter(0, String.class);
		String strIssueId = (String) objPD.getParameter(1, String.class);
		String strIsuCountent = (String) objPD.getParameter(2, String.class);
		String strReceiver = (String) objPD.getParameter(3, String.class);
		String strOperId =(String) objPD.getParameter(4, String.class);
		
		IssueForEmail objIssueForEmail = new IssueForEmail();
		objIssueForEmail.sendIssueToEmail(strCwcode,strIssueId,strIsuCountent,strOperId, strReceiver);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter((objIssueForEmail.getIsSuccess()).toString());
		return objEncode.toString();
	}
}
