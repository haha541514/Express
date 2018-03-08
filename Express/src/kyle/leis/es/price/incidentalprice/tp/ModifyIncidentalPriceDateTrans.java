package kyle.leis.es.price.incidentalprice.tp;

import net.sf.hibernate.Session;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.price.expressprice.tp.AModifyPriceDateTrans;
import kyle.leis.es.price.incidentalprice.dax.IncidentalPriceDemand;
import kyle.leis.es.price.incidentalprice.dax.LoadIncidentalResult;
import kyle.leis.es.ruledate.RuleCheckReturn;

public class ModifyIncidentalPriceDateTrans extends AModifyPriceDateTrans {

	protected void createNewRule(RuleCheckReturn objRuleCheckReturn, 
			Session objSession) throws Exception {
		String strEpcode = objRuleCheckReturn.getRulecode();
		if (StringUtility.isNull(strEpcode)) return;
		
		LoadIncidentalResult objLIResult = IncidentalPriceDemand.load(strEpcode);
		SaveIncidentalTrans objSaveIncidentalTrans = new SaveIncidentalTrans();
		objSaveIncidentalTrans.setConflictParam(objLIResult, 
				"0",
				objRuleCheckReturn.getNewStartDate(),
				objRuleCheckReturn.getNewEndDate(),
				false);
		objSaveIncidentalTrans.transaction(objSession);		
	}

}
