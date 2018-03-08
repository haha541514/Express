package kyle.leis.eo.billing.calculate.pricegroup.dax;

import kyle.leis.es.price.currency.da.CurrencyCondition;
import kyle.leis.es.price.expressprice.dax.IExpressPriceBasicData;
import kyle.leis.es.price.freightprice.da.FreightpriceCondition;
import kyle.leis.es.price.incidentalprice.da.IncidentalpriceCondition;
import kyle.leis.es.price.pricegroup.da.CustomerpricegroupCondition;

public class PricegroupDemand {
	public static CustomerpricegroupCondition transferByFreight(FreightpriceCondition objFPCondition) {
		CustomerpricegroupCondition objCPGCondition = new CustomerpricegroupCondition();
		
		objCPGCondition.setCocode(objFPCondition.getCocode());
		objCPGCondition.setEecode(objFPCondition.getEecode());
		objCPGCondition.setEpstartdate(objFPCondition.getEpstartdate());
		objCPGCondition.setEpstartdate2(objFPCondition.getEpstartdate());
		objCPGCondition.setPkcode(objFPCondition.getPkcode());
		objCPGCondition.setPscode(IExpressPriceBasicData.PRICE_STATUS_RELEASE);
		
		return objCPGCondition;
	}
	
	public static CustomerpricegroupCondition transferByIncidental(IncidentalpriceCondition objIPCondition) {
		CustomerpricegroupCondition objCPGCondition = new CustomerpricegroupCondition();
		
		objCPGCondition.setCocode(objIPCondition.getCocode());
		objCPGCondition.setEecode(objIPCondition.getEecode());
		objCPGCondition.setEpstartdate(objIPCondition.getEpstartdate());
		objCPGCondition.setEpstartdate2(objIPCondition.getEpstartdate());
		objCPGCondition.setPkcode(objIPCondition.getPkcode());
		objCPGCondition.setPscode(IExpressPriceBasicData.PRICE_STATUS_RELEASE);
		
		return objCPGCondition;
	}
	
	public static CustomerpricegroupCondition transferByCurrency(CurrencyCondition objCurrencyCondition) {
		CustomerpricegroupCondition objCPGCondition = new CustomerpricegroupCondition();
		
		objCPGCondition.setCocode(objCurrencyCondition.getCocode());
		objCPGCondition.setEecode(objCurrencyCondition.getEecode());
		objCPGCondition.setEpstartdate(objCurrencyCondition.getEpstartdate());
		objCPGCondition.setEpstartdate2(objCurrencyCondition.getEpstartdate());
		objCPGCondition.setPkcode(objCurrencyCondition.getPkcode());
		objCPGCondition.setPscode(IExpressPriceBasicData.PRICE_STATUS_RELEASE);
		
		return objCPGCondition;
	}	
	
}
