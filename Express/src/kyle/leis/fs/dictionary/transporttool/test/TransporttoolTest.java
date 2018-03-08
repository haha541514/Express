package kyle.leis.fs.dictionary.transporttool.test;

import kyle.common.connectors.util.Decoder;
import kyle.leis.fs.dictionary.transporttool.sv.TransporttoolService;

public class TransporttoolTest {

	private static TransporttoolService objTransporttoolSv = new TransporttoolService();
	
	public static void main(String [] args)
	{
		try
		{
			System.out.println(query());
			
//			addTransporttool();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static String query() throws Exception
	{
//		String str = "AT01~`~`~`~`~`~`~`~`~`~`~`~@~#";
		String str = "~`~`~`~`~`~`~`~`~`~`~`1~@~#";
		Decoder objPD = new Decoder(str);
		return objTransporttoolSv.query(objPD);
	}
	
	public static String addTransporttool() throws Exception
	{
//		String str = "Test02~`XXX~`18:00:00~`22:00:00~`’≈ ¶∏µ2~`1~`~`~`718~`~`~`AT~`~`~@~#";
		String str = "TT02~`1233~`09:00:00~`17:00:00~`≤‚ ‘’À∫≈~`330~`~`~`719~`~`~`TT~`~`~`1~`~`~`~@~#";
		Decoder objPD = new Decoder(str);
		return objTransporttoolSv.addTransporttool(objPD);
	}
}
