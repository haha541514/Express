package kyle.leis.es.businessrule.productrule.tp;

import net.sf.hibernate.Session;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.businessrule.businessrules.tp.AModifyBRDateTrans;
import kyle.leis.es.businessrule.productrule.dax.LoadProductruleResult;
import kyle.leis.es.businessrule.productrule.dax.ProductruleDemand;
import kyle.leis.es.ruledate.RuleCheckReturn;

public class ModifyProductruleDateTrans extends AModifyBRDateTrans {

	protected void createNewRule(RuleCheckReturn objRuleCheckReturn, 
			Session objSession) throws Exception {
		String strBrid = objRuleCheckReturn.getRulecode();
		if (StringUtility.isNull(strBrid)) return;
		
		LoadProductruleResult objLoadPResult = ProductruleDemand.loadResults(strBrid);
		SaveProductruleTrans objSPRTrans = new SaveProductruleTrans();
		objSPRTrans.setConflictParam(objLoadPResult, 
				"0", 
				objRuleCheckReturn.getNewStartDate(),
				objRuleCheckReturn.getNewEndDate(),
				false);
		objSPRTrans.transaction(objSession);		
	}

}
