package kyle.leis.eo.operation.transportwaybill.test;

import java.util.List;

import kyle.leis.eo.operation.transportwaybill.bl.TransportWaybill;
import kyle.leis.eo.operation.transportwaybill.da.TransportforcorewaybillColumns;

public class TransportWaybillTest {

	public static void main(String []args)
	{
		try
		{
			transportCwTest();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static void transportCwTest() throws Exception
	{
		TransportWaybill objTransportWaybill = new TransportWaybill();
		TransportforcorewaybillColumns objReturn = objTransportWaybill.loadByCwcode("4412");
		System.out.println(objReturn.getBwbwcode());
		
		
	}
}
