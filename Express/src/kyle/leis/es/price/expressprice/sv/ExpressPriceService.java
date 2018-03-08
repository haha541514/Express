package kyle.leis.es.price.expressprice.sv;

import java.util.HashSet;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.common.util.jlang.CollectionUtility;
import kyle.leis.es.price.expressprice.bl.ExpressPrice;

public class ExpressPriceService extends AService {
	public String searchProductKind(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 4, this);

		String strCocode = (String)objPD.getParameter(0, String.class);		
		String strDtcode = (String)objPD.getParameter(1, String.class); 
		String strEecode = (String)objPD.getParameter(2, String.class);
		String strOperID = (String)objPD.getParameter(3, String.class);
		
		ExpressPrice objExpressPrice = new ExpressPrice();
		HashSet<String> hsPkcode = objExpressPrice.searchProductKind(strCocode, 
				strDtcode, 
				strEecode,
				strOperID);
		String[][] aastrPkcode = CollectionUtility.transferToStringArray(hsPkcode);
		if (aastrPkcode == null || aastrPkcode.length < 1)
			return "";
		Encoder objEncode = new Encoder();
		objEncode.addParameter(aastrPkcode);
		return objEncode.toString();
	}
}
