package kyle.leis.es.systemcertification.test;

import java.util.List;

import kyle.common.connectors.util.Decoder;
import kyle.leis.es.systemcertification.da.SystemcertificationCondition;
import kyle.leis.es.systemcertification.da.SystemcertificationQuery;
import kyle.leis.es.systemcertification.sv.SystemcertificationService;
import kyle.leis.fs.dictionary.operator.sv.OperatorService;

public class SystemcertificationTest {
	private static SystemcertificationService objSystemcertificationSv = new SystemcertificationService();
	private static OperatorService objOperatorSv = new OperatorService();
	
	public static void main(String [] args)
	{
		try
		{
			query();
//			System.out.println(login());//登录时的认证检查
			
//			System.out.println(confrimSystemcertification());//认证通过
			
//			System.out.println(eliminateSystemcertification()); //认证失效
			
//			System.out.println(extendSystemcertification());//认证延长
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void query() throws Exception
	{
		// SystemcertificationCondition objSystemcertificationCon = new SystemcertificationCondition();
		//objSystemcertificationCon.setNotinsscode("OFF,ON");
		
		/*objSystemcertificationCon.setStartscstartdate("2009-12-27");
		objSystemcertificationCon.setEndscstartdate("2011-07-05");*/
		
		
		//objSystemcertificationCon.setStartscenddate("2009-12-27");
		//objSystemcertificationCon.setEndscenddate("2011-07-05");
		/*objSystemcertificationCon.setScid("51");
		
		List objList = SystemCertificationDemand.query(objSystemcertificationCon);
		
		System.out.println(objList.size());*/
		
		
		SystemcertificationQuery query = new SystemcertificationQuery();
		SystemcertificationCondition conditon = new SystemcertificationCondition();
		//conditon.setScid("15");
		query.setCondition(conditon);
		List list = query.getResults();
		System.out.println(list.size());
	}
	
	public static String login() throws Exception
	{
		//String str = "TOMMYCHEN~`~@~#123~`~@~#LEDIS~`~@~#~`Hd001~`Mac001~`192.168.0.1~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~@~#";
		//String str = "XS001~`~@~#123~`~@~#LEDIS~`~@~#~`Hd005~`Mac005~`192.168.0.5~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~@~#";
		
		//String str = "TOMMYCHEN~`~@~#123~`~@~#LEDIS~`~@~#HD1010~`~@~#MAC101~`~@~#192.168.1.101~`~@~#";
		
		String str = "XS001~`~@~#123~`~@~#LEDIS~`~@~#48A6367D~`~@~#00:25:64:DC:AA:30~`~@~#192.168.1.101~`~@~#";
		
		
		
		Decoder objPD = new Decoder(str);
		return objOperatorSv.login(objPD);
	}
	
	public static String confrimSystemcertification() throws Exception
	{
		String str = "0~`~@~#63~`~@~#";
		Decoder objPD = new Decoder(str);
		return objSystemcertificationSv.confrimSystemcertification(objPD);
	}
	
	public static String eliminateSystemcertification() throws Exception
	{
		String str = "0~`~@~#51~`~@~#";
		Decoder objPD = new Decoder(str);
		return objSystemcertificationSv.eliminateSystemcertification(objPD);
	}
	
	public static String extendSystemcertification() throws Exception
	{
		String str = "0~`~@~#63~`~@~#2010-7-01~`~@~#";
		Decoder objPD = new Decoder(str);
		return objSystemcertificationSv.extendSystemcertification(objPD);
	}
}
