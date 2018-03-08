package kyle.leis.eo.billing.calculate.adjustive.dax;

import kyle.leis.eo.billing.calculate.feecalculate.dax.IFeeCalculateBasicData;
import kyle.leis.es.price.adjustiveprice.da.AdjustivepriceCondition;
import kyle.leis.es.price.freightprice.da.FreightpriceCondition;

public class AdjustiveDemand {
	public static AdjustivepriceCondition transferToCondition(FreightpriceCondition objFPCondition) {
		AdjustivepriceCondition objAPCondition = new AdjustivepriceCondition();
		
		objAPCondition.setChncode(objFPCondition.getChncode());
		objAPCondition.setEecode(objFPCondition.getEecode());
		objAPCondition.setEpstartdate(objFPCondition.getEpstartdate());
		objAPCondition.setEpstartdate2(objFPCondition.getEpstartdate2());
		objAPCondition.setPscode(IFeeCalculateBasicData.PRICESTATUS_RELEASE);
		
		return objAPCondition;
	}
	
}
