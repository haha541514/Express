package kyle.leis.eo.billing.calculate.currency.dax;

import kyle.leis.eo.billing.calculate.feecalculate.dax.IFeeCalculateBasicData;
import kyle.leis.es.price.currency.da.CurrencyCondition;

public class CurrencyDemand {
	
	public static CurrencyCondition transferToCondition(String strCocode, 
			String strPkcode, 
			String strSearchDate,
			String strEecode) {
		CurrencyCondition objCurrencyCondition = new CurrencyCondition();
		
		objCurrencyCondition.setCocode(strCocode);
		objCurrencyCondition.setPkcode(strPkcode);
		objCurrencyCondition.setEpstartdate(strSearchDate);
		objCurrencyCondition.setEpstartdate2(strSearchDate);
		// objCurrencyCondition.setEecode(strEecode);
		objCurrencyCondition.setPscode(IFeeCalculateBasicData.PRICESTATUS_RELEASE);		
		
		return objCurrencyCondition;
	}
	
	
}
