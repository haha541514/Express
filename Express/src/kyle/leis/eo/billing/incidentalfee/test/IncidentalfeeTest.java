package kyle.leis.eo.billing.incidentalfee.test;

import java.util.List;

import kyle.common.connectors.util.Decoder;
import kyle.leis.eo.billing.incidentalfee.da.IncidentalfeeCondition;
import kyle.leis.eo.billing.incidentalfee.da.IncidentalfeeQuery;
import kyle.leis.eo.billing.incidentalfee.sv.IncidentalfeeService;

public class IncidentalfeeTest {
	private static IncidentalfeeService objIncidentalfeeSv = new IncidentalfeeService();
	public static void main(String [] args)
	{
		try
		{
			//查询
//			System.out.println(queryTest());
			System.out.println(addIncidentalfeeTest());
//			System.out.println(confrimTest());
//			System.out.println(test());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private static String queryTest() throws Exception
	{
		String str = "11~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~`~@~#";
		Decoder objPD = new Decoder(str);
		
		return objIncidentalfeeSv.query(objPD);
	}
	
	private static String addIncidentalfeeTest() throws Exception
	{
//		String str = "~`1~`10~`10~`2010-08-16 16:58:34~`~`~`1~`remark~`RMB~`~`~`812~`1145~`~`~`~`~`P~`~`~`~`~`~`~`~`~`~`~`~`~`A1010~`~`338~`~`A0101~`~`~@~#1058~`~@~#";
//		String str = "~`1~`10~`10~`2010-08-16 17:33:33~`2010-08-16 17:33:33~`2010-08-16 17:33:33~`~`1~`remark~`RMB~`~`~`812~`1145~`~`~`~`~`P~`~`~`~`~`~`~`~`~`~`~`~`A1010~`~`338~`~`A0101~`~`~@~#1058~`~@~#";
		//新增
//		String str = "~`10~`100~`100~`2010-08-16 17:33:33~`~`2010-08-16 17:33:33~`~`~`remark~`RMB~`~`~`~`~`~`~`~`~`P~`~`~`~`~`~`~`~`~`~`~`~`A1010~`~`338~`~`~`~`A0101~`~`~@~#1058~`~@~#";
		//修改
		String str = "~`10~`100~`100~`2010-08-16 17:33:33~`~`2010-08-16 17:33:33~`~`~`remarkkkkk~`RMB~`~`~`~`~`~`~`~`~`P~`~`~`~`~`~`~`~`~`~`~`~`A1010~`~`338~`~`~`~`A0101~`~`~@~#1058~`~@~#";
		Decoder objPD = new Decoder(str);
		
		return objIncidentalfeeSv.addIncidentalfee(objPD);
	}
	
	private static String confrimTest() throws Exception
	{
		String str = "1~`~@~#C~`~@~#1058~`~@~#";
		Decoder objPD = new Decoder(str);
		
		return "";//objIncidentalfeeSv.confrim(objPD);
	}
	
	public static String test() throws Exception
	{
		IncidentalfeeQuery query = new IncidentalfeeQuery();
		IncidentalfeeCondition condition = new IncidentalfeeCondition();
		condition.setIfid("12");
		query.setCondition(condition);
		List list = query.getResults();
		System.out.println(list.size());
		return "";
	}
}