package kyle.leis.es.systemproperty.test;

import java.util.List;

import kyle.common.connectors.util.Decoder;
import kyle.leis.es.systemproperty.da.SystempropertyCondition;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;
import kyle.leis.es.systemproperty.sv.SystempropertyService;

public class SystempropertyTest {

	private static SystempropertyService objSv ;
	public static void main(String [] args)
	{
		try
		{
//			queryTest();
			//queryCaTest();
			getWaybillFilePath();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static void queryTest() throws Exception
	{
		SystempropertyCondition objSPCondition = new SystempropertyCondition();
//		objSPCondition.setEecode("1");
		List objList = SystempropertyDemand.query(objSPCondition);
		System.out.println(objList.size());
	}
	
	public static void getWaybillFilePath() throws Exception
	{
		String str = SystempropertyDemand.getWaybillFilePath();
		System.out.println(str);
	}	
	
	public static void queryCaTest() throws Exception
	{
		objSv = new SystempropertyService();
		
		String strquery = "1~`~`1~`~@~#";
		Decoder objPD1 = new Decoder(strquery);
		String strqueryResult = objSv.query(objPD1);
		System.out.println("strqueryResult: "+strqueryResult);
		
		String str = "1~`~`��ע����������嵥����ʾ��Ŀ�ĵع��ҽ����ο�����ʽ¼��ʱ���ٴ�ȷ�ϣ����յ�Ŀ�ĵع��������˵�Ϊ׼���緢�ֿ�������嵥��Ŀ�ĵع���������������֪���ǩ����Ա���븺���˾�������Ŀͷ�רԱ��ϵ��лл������~`1~`~`~`~@2~`~`MANIFEST~`1~`~`~`~@3~`~`HKG0000001279SPS~`1~`~`~`~@4~`~`System~`1~`~`~`~@5~`~`����Ͷ�ߵ绰:0755-29778781   ��С��/������ 9999996666666666~`1~`~`~`~@6~`~`C:\\LEIS\\��������~`1~`~`~`~@7~`~`C:\\CountryCacheData~`1~`~`~`~@~#";
		Decoder objPD = new Decoder(str);
		String strResult = objSv.add(objPD);
		System.out.println("strResult: "+strResult);
		
		
		Decoder objPD2 = new Decoder(strquery);
		String strqueryResult2 = objSv.query(objPD2);
		System.out.println("strqueryResult2: "+strqueryResult2);
	}
}
