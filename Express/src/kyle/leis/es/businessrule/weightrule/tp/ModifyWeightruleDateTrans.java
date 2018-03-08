package kyle.leis.es.businessrule.weightrule.tp;

import net.sf.hibernate.Session;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.businessrule.businessrules.tp.AModifyBRDateTrans;
import kyle.leis.es.businessrule.weightrule.dax.LoadWeighruleResult;
import kyle.leis.es.businessrule.weightrule.dax.WeightRuleDemand;
import kyle.leis.es.ruledate.RuleCheckReturn;

public class ModifyWeightruleDateTrans extends AModifyBRDateTrans {

	protected void createNewRule(RuleCheckReturn objRuleCheckReturn, 
			Session objSession) throws Exception {
		String strBrid = objRuleCheckReturn.getRulecode();
		if (StringUtility.isNull(strBrid)) return;
		
		LoadWeighruleResult objLoadWResult = WeightRuleDemand.loadResults(strBrid);
		SaveWeightRuleTrans objSaveWeightRuleTrans = new SaveWeightRuleTrans();
		objSaveWeightRuleTrans.setConflictParam(objLoadWResult, 
				"0", 
				objRuleCheckReturn.getNewStartDate(),
				objRuleCheckReturn.getNewEndDate(),
				false);
		objSaveWeightRuleTrans.transaction(objSession);
	}

}
