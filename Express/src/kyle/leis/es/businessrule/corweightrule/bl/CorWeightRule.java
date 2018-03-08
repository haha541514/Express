package kyle.leis.es.businessrule.corweightrule.bl;

import java.util.ArrayList;

import kyle.leis.es.businessrule.businessrules.bl.ABusinessrule;
import kyle.leis.es.businessrule.businessrules.tp.AModifyBusinessStatusTrans;
import kyle.leis.es.businessrule.corweightrule.da.CorweightruleColumns;
import kyle.leis.es.businessrule.corweightrule.tp.ModifyCWRuleStatusTrans;
import kyle.leis.es.businessrule.corweightrule.tp.SaveCorWeightRuleTrans;
import kyle.leis.es.price.expressprice.dax.SavedResult;
import kyle.leis.es.ruledate.RuleCheckReturn;

public class CorWeightRule extends ABusinessrule {
	public SavedResult save(CorweightruleColumns objCorweightruleColumns,
			String strOperId,
			boolean isIgnoreNotice) throws Exception {
		SaveCorWeightRuleTrans objSaveCWRTrans = new SaveCorWeightRuleTrans();
		ArrayList<RuleCheckReturn> listRuleCheckReturn = objSaveCWRTrans.setParam(objCorweightruleColumns, 
				strOperId, 
				true);
		if (listRuleCheckReturn == null || listRuleCheckReturn.size() < 1 || isIgnoreNotice)
			objSaveCWRTrans.execute();
		// ·µ»ØÖµ
		Long lBrid = objSaveCWRTrans.getNewBrid();
		SavedResult objSavedResult = new SavedResult();
		objSavedResult.setRulecode(String.valueOf(lBrid));
		objSavedResult.setRuleCheckList(listRuleCheckReturn);
		return objSavedResult;		
	}
	
	
	protected AModifyBusinessStatusTrans getModifyBusinessStatusTrans() {
		return new ModifyCWRuleStatusTrans();
	}
}
