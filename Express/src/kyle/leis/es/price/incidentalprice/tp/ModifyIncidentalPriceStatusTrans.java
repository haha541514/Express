package kyle.leis.es.price.incidentalprice.tp;

import java.util.ArrayList;

import kyle.leis.es.price.expressprice.tp.AModifyPriceStatusTrans;
import kyle.leis.es.price.incidentalprice.da.IncidentalpriceColumns;
import kyle.leis.es.price.incidentalprice.dax.IncidentalPriceDate;
import kyle.leis.es.price.incidentalprice.dax.IncidentalPriceDemand;
import kyle.leis.es.ruledate.ARuleDate;
import kyle.leis.es.ruledate.RuleCheckReturn;

public class ModifyIncidentalPriceStatusTrans extends AModifyPriceStatusTrans{

	public ArrayList<RuleCheckReturn> checkPriceDateConflict(String strEpcode, 
			String strPscode) throws Exception {
		ARuleDate objRuleDate = new IncidentalPriceDate();
		IncidentalpriceColumns objIPriceColumns = IncidentalPriceDemand.loadIncidentalprice(strEpcode);
		objIPriceColumns.setPspscode(strPscode);
		ArrayList<RuleCheckReturn> alRuleCheckReturn = objRuleDate.checkRuleDate(objIPriceColumns);
		if (alRuleCheckReturn != null && alRuleCheckReturn.size() > 0) {
			m_objMPDateTrans = new ModifyIncidentalPriceDateTrans();
			m_objMPDateTrans.setParam(alRuleCheckReturn);
		}
		return alRuleCheckReturn;
	}

}
