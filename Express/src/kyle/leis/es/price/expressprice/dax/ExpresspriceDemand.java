package kyle.leis.es.price.expressprice.dax;

import java.util.List;

import kyle.leis.es.price.expressprice.da.PriceenterpriseQuery;

public class ExpresspriceDemand {
	
	public static List loadEnterprise(String strEpcode) throws Exception {
		PriceenterpriseQuery objPEQ = new PriceenterpriseQuery();
		objPEQ.setEpcode(strEpcode);
		return objPEQ.getResults();
	}
	
}
