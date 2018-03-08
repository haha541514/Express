package kyle.leis.es.price.adjustiveprice.test;

import java.util.List;

import kyle.common.connectors.util.Decoder;
import kyle.leis.es.price.adjustiveprice.sv.AdjustivepriceService;

public class AdjustivepriceTest{
	public static AdjustivepriceService objAdjustivepriceSv = new AdjustivepriceService();

	public static void main(String[] args) 
	{
		try
		{
			System.out.println(queryTest());//查询
//			System.out.println(queryByEpCode());//根据编号查询
//			System.out.println(addAdjustivepricevalueTest());//新增价格值
//			System.out.println(modifyAdjustivepriceTest());//调整设置
//			System.out.println(auditTest());//审核
//			System.out.println(approveTest());//发布
//			System.out.println(test());
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static String queryTest() throws Exception
	{
		String str = "501~`~`~`~`~`~`~`~`~`~@~#";
		Decoder objPD = new Decoder(str);
		return objAdjustivepriceSv.query(objPD);
	}
	
	public static String queryByEpCode() throws Exception
	{
		String strEpCode = "501~`~@~#";
		Decoder objPD = new Decoder(strEpCode);
		return objAdjustivepriceSv.queryByEpCode(objPD);
	}
	
	/*public static String addAdjustivepricevalueTest() throws Exception
	{
		String str = "501~`1~`Monday~`10~`AWPX~`~`719~`~`PCE~`~`~@~#";
		Decoder objPD = new Decoder(str);
		return objAdjustivepriceSv.addAdjustivepricevalue(objPD);
	}*/
	
	public static String modifyAdjustivepriceTest() throws Exception
	{
		//新增
//		String str = "~`~`~`2010-08-10~`2010-09-10~`RemarkRemark~`N~`1~`~`~`N~`~`A01~`~`~`~`~`~`1~`~`~@~#~`~`Sunday~`10~`AWPX~`~`719~`~`PCE~`~`~@~#1058~`~@~#false~`~@~#";
//		String str = "~`~`~`2010-08-10~`2010-09-10~`RemarkRemark~`N~`1~`~`~`N~`~`A01~`~`~`~`~`~`1~`~`~@~#~`~@~#1058~`~@~#false~`~@~#";
		//修改
		String str = "10000~`~`~`2010-08-10~`2010-09-10~`Remark~`N~`1~`~`~`N~`~`A01~`~`~`~`~`~`1~`~`~@~#~`~`monday~`10~`ADOX~`~`719~`~`PCE~`~`~@~#1058~`~@~#false~`~@~#";
		Decoder objPD = new Decoder(str);
		return objAdjustivepriceSv.saveAdjustiveprice(objPD);
	}
	
	public static String auditTest() throws Exception
	{
		String str = "10000~`~@~#1058~`~@~#false~`~@~#";
		
		Decoder objPD = new Decoder(str);
		return objAdjustivepriceSv.audit(objPD);
	}
	
	public static String approveTest() throws Exception
	{
		String str = "10000~`~@~#1058~`~@~#false~`~@~#";
		
		Decoder objPD = new Decoder(str);
		return objAdjustivepriceSv.approve(objPD);
	}
	
	public static String test() throws Exception
	{
		// ExpresspricekindQuery query = new ExpresspricekindQuery();
		// List objList = query.getResults();
		
		return null;
	}
}
