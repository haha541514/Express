package kyle.leis.fs.dictionary.feekind.test;

import kyle.common.connectors.util.Decoder;
import kyle.leis.fs.dictionary.feekind.sv.FeekindService;

public class FeekindTest {

	public static FeekindService m_objFeekindSv = new FeekindService();
	public static void main(String [] args)
	{
		try
		{
			System.out.println(query());//��ѯ
//			System.out.println(addFeekind());//��ӷ�������
//			System.out.println(eliminate()); //����
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static String query() throws Exception
	{
		//String str = "A0101~`~`~`~`~`~`~`~@~#";
		
		String str = "T01~`~`~`~`~`~`~`~@~#";
		Decoder objPD = new Decoder(str);
		return m_objFeekindSv.queryFeekind(objPD);
	}
	
	public static String addFeekind() throws Exception
	{
		String str = "T02~`���Զ�~`Test02~`0~`Y~`Y~`some remark~`~`~`~`ON~`~`~@~#";
		
		Decoder objPD = new Decoder(str);
		return m_objFeekindSv.addFeekind(objPD);
	}
	
	public static String eliminate() throws Exception
	{
		String str = "T01~`~@~#";
		Decoder objPD = new Decoder(str);
		return m_objFeekindSv.eliminate(objPD);
	}
}
