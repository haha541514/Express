package kyle.leis.es.company.supplier.test;

import kyle.common.connectors.util.Decoder;
import kyle.leis.es.company.channel.da.ChannelCondition;
import kyle.leis.es.company.channel.dax.ChannelDemand;
import kyle.leis.es.company.supplier.sv.SupplierService;

public class SupplierTest {
	public static void main(String[] args) {
		try {
			//System.out.println(getManifestSeriesNumber());
			queryChannel();
		} catch(Exception ex) {
			ex.printStackTrace();
			ex.getMessage();
		}
	}
	
	public static String getManifestSeriesNumber() throws Exception {
		String str = "2~`~@~#";
		Decoder objPD = new Decoder(str);
		SupplierService objSupplierService = new SupplierService();
		return objSupplierService.getManifestSeriesNumber(objPD);
	}
	
	public static void queryChannel() throws Exception {
		ChannelCondition objChannelCondition = new ChannelCondition();
		objChannelCondition.setCstcode("S");
		objChannelCondition.setCscode("AP");
		ChannelDemand.query(objChannelCondition);
	}
}
