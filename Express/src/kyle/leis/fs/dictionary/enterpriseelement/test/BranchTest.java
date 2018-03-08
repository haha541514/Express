package kyle.leis.fs.dictionary.enterpriseelement.test;

import kyle.common.connectors.util.Decoder;
import kyle.leis.fs.dictionary.enterpriseelement.sv.BranchService;

public class BranchTest {
	private static BranchService objBranchSv = new BranchService();

	public static void main(String [] args)
	{
		try
		{
			System.out.println(testQuery());
			
			//System.out.println(testAddBranch());
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static String testQuery() throws Exception
	{
		String strEecode = "2~`~`~`~`~`~`~@~#";
		
		Decoder objPD = new Decoder(strEecode);
		
		return objBranchSv.query(objPD);
	}
	
	public static String testAddBranch() throws Exception
	{
		String str = "1013~`1058~`111~`111~`111~`~`1~`Test10~`tname~`tenname~`tsname~`tesname~`taddress~`~`~`tommy@123.com~`8876878~`8888888~`~`~`~`~`719~`~`~`~`~`~`CN~`~`~`BR~`~`~`~@~#0~`~@~#";
		Decoder objPD = new Decoder(str);
		return objBranchSv.addBranch(objPD);
	}
}
