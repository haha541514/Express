package kyle.leis.eo.customerservice.issue.test;

import kyle.common.connectors.conf.ActionServiceConfig;
import kyle.common.connectors.util.Decoder;
import kyle.common.dbaccess.query.HSingleQuery;
import kyle.leis.eo.customerservice.issue.sv.IssueService;
import kyle.leis.hi.TcsIssue;

public class IssueTest {
	public static void main(String[] args) {
		try {
			// System.out.println(addHoldIssue());
//			System.out.println(addIssueAction());
//			System.out.println(addHoldIssue());	
			System.out.println(sendTest());
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
	}
	
	public static String addHoldIssue() throws Exception {
//		String str = "266~`~@~#327~`~@~#��˾������������Ƕ���˹�������ǡ�AER��, ��GDX��, MCX������YKS����\"IJK, NOI, SKX, TYA, VOG, VOZ ��ĿǰDHL�ް����������ṩ���������~`~@~#1~`~@~#";
		String str = "9358~`~@~#213~`~@~#ŷ��һƱ����������ո��ӷ�~`~@~#1~`~@~#";//CS34,MK002,MK001,242,245,CS38,322
		new ActionServiceConfig("D:\\tomcat6.0.18\\webapps\\Express\\WEB-INF\\ServiceConfig.xml");
		Decoder objPD = new Decoder(str);
		IssueService objIssueService = new IssueService();
		return objIssueService.addHoldIssue(objPD);
	}
	
	public static String addIssueAction() throws Exception {
		String str = "RC~`~@~#11~`~@~#sasdfasf~`~@~#1~`~@~#";
		Decoder objPD = new Decoder(str);
		IssueService objIssueService = new IssueService();
		return objIssueService.addIssueAction(objPD);		
	}
	
	public static String sendTest() throws Exception
	{
		String str = "327483~`~@~#182878~`~@~#��ΪƷ������ϸ��������ۼ�~`~@~#16752292@qq.com~`~@~#1~`~@~#";
		new ActionServiceConfig("E:\\jakarta-tomcat-5.5.9\\webapps\\Express\\WEB-INF\\ServiceConfig.xml");
		Decoder objPD = new Decoder(str);
		IssueService objIssueService = new IssueService();
		return objIssueService.sendMail(objPD);
		
		/*HSingleQuery objHSingleQuery = new HSingleQuery();
		TcsIssue objTcsIssue = (TcsIssue)objHSingleQuery.load(TcsIssue.class, 5871l);
		System.out.println(objTcsIssue.getIsContent());*/
	}
}
