package kyle.leis.eo.billing.calculate.incidental.dax;

import kyle.leis.eo.billing.calculate.feecalculate.dax.IFeeCalculateBasicData;
import kyle.leis.es.price.freightprice.da.FreightpriceCondition;
import kyle.leis.es.price.incidentalprice.da.IncidentalpriceCondition;

public class IncidentalDemand {
	public static IncidentalpriceCondition transferToCondition(FreightpriceCondition objFPCondition) {
		IncidentalpriceCondition objIPCondition = new IncidentalpriceCondition();
		
		objIPCondition.setChncode(objFPCondition.getChncode());
		objIPCondition.setCocode(objFPCondition.getCocode());
		objIPCondition.setDtcode(objFPCondition.getDtcode());
		objIPCondition.setEecode(objFPCondition.getEecode());
		objIPCondition.setEpstartdate(objFPCondition.getEpstartdate());
		objIPCondition.setEpstartdate2(objFPCondition.getEpstartdate2());
		objIPCondition.setPdcode(objFPCondition.getPdcode());
		objIPCondition.setPkcode(objFPCondition.getPkcode());
		objIPCondition.setPscode(IFeeCalculateBasicData.PRICESTATUS_RELEASE);
		
		return objIPCondition;
	}
}
