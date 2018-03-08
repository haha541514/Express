package kyle.leis.eo.billing.calculate.currency.sv;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.eo.billing.calculate.currency.bl.Currency;

public class CurrencyService extends AService {
	
	public String getCurrencyrate(Decoder objPD) throws Exception {
		int iParameterCount = objPD.getParameterCount();
		String strCurrencyrate = "0";
		
		if (iParameterCount == 5) {
			// 获得参数
			String strCocode = (String)objPD.getParameter(0, String.class);
			String strPkcode = (String)objPD.getParameter(1, String.class);
			String strSearchDate = (String)objPD.getParameter(2, String.class);
			String strEecode = (String)objPD.getParameter(3, String.class);
			String strCkcodeChange = (String)objPD.getParameter(4, String.class);
			
			Currency objCurrency = new Currency(); 
			strCurrencyrate = objCurrency.getCurrencyrate(strCocode, 
					strPkcode, 
					strSearchDate, 
					strEecode, 
					strCkcodeChange);
		} else if (iParameterCount == 1) {
			String strCkcode = (String)objPD.getParameter(0, String.class);
			Currency objCurrency = new Currency(); 
			strCurrencyrate = objCurrency.getCurrencyrate(strCkcode);
		}
		Encoder objEncode = new Encoder();
		objEncode.addParameter(strCurrencyrate);
		return objEncode.toString();
	}
}
