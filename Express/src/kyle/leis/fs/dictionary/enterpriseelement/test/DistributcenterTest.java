package kyle.leis.fs.dictionary.enterpriseelement.test;

import kyle.common.connectors.util.Decoder;
import kyle.leis.fs.dictionary.enterpriseelement.dax.EnterpriseelementDemand;
import kyle.leis.fs.dictionary.enterpriseelement.sv.DistributioncenterService;

public class DistributcenterTest {

	private static DistributioncenterService objDistributioncenterSv = new DistributioncenterService();
	
	public static void main(String [] args)
	{
		try
		{
			//System.out.println(testQuery());
			System.out.println(getUpBranchEecode());
			//System.out.println(testAddDistributioncenter());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static String testQuery() throws Exception
	{
		String strEecode = "1~`~`~`~@~#";
		Decoder objPD = new Decoder(strEecode);
		return objDistributioncenterSv.query(objPD);
	}
	
	public static String getUpBranchEecode() throws Exception
	{
		String strEecode = "211";
		return EnterpriseelementDemand.getUpBranchEecode(strEecode);
	}	
	
	
	public static String testAddDistributioncenter() throws Exception
	{
		/*//新增
		String str = "~`~`1~`Test1001~`Tdcname~`Tdcename~`Tdcsname~`Tdcesname~`Tdcaddress~`~`~`tommy@xswlsz.com~`7777777~`8888888~`~`~`~`~`719~`~`~`CN~`~`~`DC~`~`~`719~`~`~`~`~`~`~`1058~`~@~#0~`~@~#";
		*/
		
		//修改 注：修改的时候dcbreecode必须传过来
		String str = "1017~`~`1~`Test1001~`Tdcname~`Tdcename~`Tdcsname~`Tdcesname~`Tdcaddress~`~`~`tommy@xswlsz.com~`7777777~`8888888~`~`~`~`~`719~`~`~`CN~`~`~`DC~`~`~`719~`~`~`~`~`111~`111~`1058~`~@~#0~`~@~#";
		
		Decoder objPD = new Decoder(str);
		return objDistributioncenterSv.addDistributioncenter(objPD);
	}
}
