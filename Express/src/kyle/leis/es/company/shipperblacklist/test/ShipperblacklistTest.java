package kyle.leis.es.company.shipperblacklist.test;

import kyle.common.connectors.util.Decoder;
import kyle.leis.es.company.shipperblacklist.sv.ShipperblacklistService;

public class ShipperblacklistTest {
	private static ShipperblacklistService objSBLService = new ShipperblacklistService();
	
	public static void main(String [] args)
	{
		try
		{
			System.out.println(addTest());
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static String addTest() throws Exception
	{
		String str="~`TestCompany~`~`2011-02-15 10:10:45~`~`~`~`~`~`~`~`~`DHL~`~`~`~@~#1~`~@~#";
		Decoder objPD = new Decoder(str);
		return objSBLService.add(objPD);
	}
	
	public static String query() throws Exception
	{
		String str = "";
		Decoder objPD = new Decoder(str);
		return objSBLService.query(objPD);
	}
}
