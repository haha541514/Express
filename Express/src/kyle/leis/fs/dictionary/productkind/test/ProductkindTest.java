package kyle.leis.fs.dictionary.productkind.test;

import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.fs.dictionary.productkind.sv.ProductkindService;

public class ProductkindTest {
	public static ProductkindService s_objProductkindSv = new ProductkindService();
	
	public static void main(String [] args)
	{
		try
		{
			System.out.println(queryBypkCode());
			
			//System.out.println(query());
			
			//System.out.println(addProductkind());
			
			//System.out.println(modifyStatus());
			
			//System.out.println(modifySign());
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	public static String queryBypkCode() throws Exception
	{
		String strpkCode = "D0102";
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(strpkCode);
		String str = objEncode.toString();
		Decoder objPD = new Decoder(str);
		
		return s_objProductkindSv.queryBypkCode(objPD);
	}
	
	public static String query() throws Exception
	{
		String strpkCode = "D0102~`~`~`~@~#";
		Decoder objPD = new Decoder(strpkCode);
		
		return s_objProductkindSv.query(objPD);
	}
	
	public static String addProductkind() throws Exception
	{
		String str = "Test01~`测试产品一~`TestPk01~`一~`Pk1~`This is a test Productkind~`N~`NW~`~`~@~#";
		
		Decoder objPD = new Decoder(str);
		
		return s_objProductkindSv.addProductkind(objPD);
	}
	
	public static String modifyStatus() throws Exception
	{
		String str = "Test01~`~@~#OFF~`~@~#";
		
		Decoder objPD = new Decoder(str);

		return s_objProductkindSv.modifyStatus(objPD);
	}
	
	public static String modifySign() throws Exception
	{
		String str = "Test01~`~@~#N~`~@~#";
		
		Decoder objPD = new Decoder(str);
		
		return s_objProductkindSv.modifySign(objPD);
	}
}
