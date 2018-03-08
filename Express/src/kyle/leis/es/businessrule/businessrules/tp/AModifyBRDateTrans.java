package kyle.leis.es.businessrule.businessrules.tp;

import net.sf.hibernate.Session;
import kyle.leis.es.businessrule.businessrules.dax.IBusinessruleBasicData;
import kyle.leis.es.ruledate.AModifyRuleDateTrans;
import kyle.leis.es.ruledate.RuleCheckReturn;

public abstract class AModifyBRDateTrans extends AModifyRuleDateTrans {
	protected void eliminate(RuleCheckReturn objRuleCheckReturn, 
			Session objSession) throws Exception {
		ModifyBusinessruleTrans objModifyBRTrans = new ModifyBusinessruleTrans();
		objModifyBRTrans.setModifyStatusParam(objRuleCheckReturn.getRulecode(), 
				IBusinessruleBasicData.SIMPLESTATUS_OFF, 
				"0");
		objModifyBRTrans.transaction(objSession);
	}

	protected void modifyDate(RuleCheckReturn objRuleCheckReturn,
			Session objSession) throws Exception {
		ModifyBusinessruleTrans objModifyBRTrans = new ModifyBusinessruleTrans();
		objModifyBRTrans.setModifyDateParam(objRuleCheckReturn.getRulecode(), 
				objRuleCheckReturn.getNewStartDate(), 
				objRuleCheckReturn.getNewEndDate(), 
				"0");
		objModifyBRTrans.transaction(objSession);		
	}
}
