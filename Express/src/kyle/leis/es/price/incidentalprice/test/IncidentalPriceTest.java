package kyle.leis.es.price.incidentalprice.test;

import java.util.List;

import kyle.common.connectors.util.Decoder;
import kyle.leis.es.price.incidentalprice.da.IncidentalpriceCondition;
import kyle.leis.es.price.incidentalprice.dax.IncidentalPriceDemand;
import kyle.leis.es.price.incidentalprice.sv.IncidentalPriceService;

public class IncidentalPriceTest {
	private static IncidentalPriceService m_objIPS = new IncidentalPriceService();
	public static void main(String[] args) {
		try {
			System.out.println(save());
			//System.out.println(query());
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
	}
	
	public static String save() throws Exception {
		String str = "~`2016-03-11~`~`N~`~`~`~`~`A01~`~`~`~`~`1~`~`~@~#";
		Decoder objPD = new Decoder(str);
		return m_objIPS.query(objPD);		
	}
	
	public static String query() throws Exception {
		IncidentalpriceCondition objIPC = new IncidentalpriceCondition();
		objIPC.setEpecode("1");
		objIPC.setEecode("1");
		List list = IncidentalPriceDemand.query(objIPC);
		return String.valueOf(list.size());
	}	
}
